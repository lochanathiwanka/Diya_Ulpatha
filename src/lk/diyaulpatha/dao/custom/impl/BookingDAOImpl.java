package lk.diyaulpatha.dao.custom.impl;

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
    public boolean add(Booking b) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Booking VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,b.getBookingID(),b.getCustomerID(),b.getDate(),b.getTime(),b.getPayment());
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
    public Booking search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Booking> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
