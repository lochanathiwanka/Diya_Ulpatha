package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.BookingDAO;
import lk.diyaulpatha.entity.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public int getBookingRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(bookingID) FROM Booking";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()){
            return rst.getInt(1);
        }
        return -1;
    }

    @Override
    public String getLastBookingID(String NIC, String name, String contact) throws ClassNotFoundException, SQLException {
        String SQL = "select bookingID from Booking b,Customer c where (b.customerID=c.customerID) and (nic=? or name=? or contact=?) order by bookingID desc limit 1";
        ResultSet rst = CrudUtil.executeQuery(SQL, NIC, name, contact);
        if (rst.next()) {
            return rst.getString("bookingID");
        }
        return null;
    }

    @Override
    public ObservableList<Booking> getAllBookingIDOnOneCustomer(String value) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT bookingID FROM Booking b, Customer c WHERE (b.customerID=c.customerID) and c.name = ? ";
        ResultSet rst = CrudUtil.executeQuery(SQL, value);
        ObservableList<Booking> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Booking(rst.getString("bookingID")));
        }
        return list;
    }

    @Override
    public Booking getBookingIDOnDate(String value, String name) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT bookingID FROM Booking b, Customer c WHERE (b.customerID=c.customerID) and (b.date = ? && c.name = ?) ";
        ResultSet rst = CrudUtil.executeQuery(SQL, value, name);
        if (rst.next()) {
            return new Booking(rst.getString("bookingID"));
        }
        return null;
    }

    @Override
    public boolean add(Booking b) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Booking VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL, b.getBookingID(), b.getCustomerID(), b.getDate(), b.getTime(), b.getPayment());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Booking booking) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Booking search(String id) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Booking WHERE bookingID = ?";
        ResultSet rst = CrudUtil.executeQuery(SQL, id);
        if (rst.next()) {
            return new Booking(rst.getString("bookingID"), rst.getString("customerID"), rst.getString("date"),
                    rst.getString("time"), rst.getString("payment"));
        }
        return null;
    }

    @Override
    public ObservableList<Booking> getAll() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Booking";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        ObservableList<Booking> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Booking(rst.getString("bookingID"), rst.getString("customerID"), rst.getString("date"),
                    rst.getString("time"), rst.getString("payment")));
        }
        return list;
    }
}
