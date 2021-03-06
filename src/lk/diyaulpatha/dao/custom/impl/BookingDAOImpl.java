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
    public ObservableList<String> getAllToBePayedBookingIDOnOneCustomer(String NIC, String name, String contact) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT DISTINCT b.bookingID FROM Booking b, BookingDetail bd, Customer c WHERE (b.bookingID=bd.bookingID && b.customerID=c.customerID) " +
                "AND (nic=? OR name=? OR contact=?) AND bd.endTime=? ORDER BY bookingID";
        ResultSet rst = CrudUtil.executeQuery(SQL, NIC, name, contact, "empty");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(rst.getString("bookingID"));
        }
        return list;
    }

    @Override
    public ObservableList<Booking> getAllBookingIDOnOneCustomer(String value) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT bookingID,b.customerID,date,time,payment FROM Booking b, Customer c WHERE (b.customerID=c.customerID) AND c.name = ? ";
        ResultSet rst = CrudUtil.executeQuery(SQL, value);
        ObservableList<Booking> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Booking(rst.getString("bookingID"), rst.getString("customerID"), rst.getString("date"),
                    rst.getString("time"), rst.getString("payment")));
        }
        return list;
    }

    @Override
    public ObservableList<Booking> getBookingIDOnDate(String value, String name) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT bookingID,b.customerID,date,time,payment FROM Booking b, Customer c WHERE (b.customerID=c.customerID) AND (b.date = ? && c.name = ?) ";
        ResultSet rst = CrudUtil.executeQuery(SQL, value, name);
        ObservableList<Booking> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Booking(rst.getString("bookingID"), rst.getString("customerID"), rst.getString("date"),
                    rst.getString("time"), rst.getString("payment")));
        }
        return list;
    }

    @Override
    public ObservableList<Booking> getBookingIDBetweenTwoDays(String start, String end) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT bookingID,customerID,date,time,payment FROM Booking WHERE date BETWEEN ? AND ? ";
        ResultSet rst = CrudUtil.executeQuery(SQL, start, end);
        ObservableList<Booking> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Booking(rst.getString("bookingID"), rst.getString("customerID"), rst.getString("date"),
                    rst.getString("time"), rst.getString("payment")));
        }
        return list;
    }

    @Override
    public ObservableList<Booking> getAllBookingIDOnRoomCode(String code) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT b.bookingID,customerID,date,time,payment FROM Booking b, BookingDetail bd, Room r WHERE (b.bookingID=bd.bookingID && r.roomID=bd.roomID) AND r.code=?";
        ResultSet rst = CrudUtil.executeQuery(SQL, code);
        ObservableList<Booking> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Booking(rst.getString(1), rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getString(5)));
        }
        return list;
    }

    @Override
    public ObservableList<Booking> getAllBoookingDetailBetweenTwoDaysOnRoomCode(String code, String start, String end) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT b.bookingID,customerID,date,time,payment from Booking b, BookingDetail bd, Room r WHERE (b.bookingID=bd.bookingID && r.roomID=bd.roomID) AND (r.code=? && date BETWEEN ? AND ?) ";
        ResultSet rst = CrudUtil.executeQuery(SQL, code, start, end);
        ObservableList<Booking> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Booking(rst.getString("bookingID"), rst.getString("customerID"), rst.getString("date"),
                    rst.getString("time"), rst.getString("payment")));
        }
        return list;
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
