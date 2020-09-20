package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.BookingDetailDAO;
import lk.diyaulpatha.entity.BookingDetail;

import java.sql.SQLException;

public class BookingDetailDAOImpl implements BookingDetailDAO {
    @Override
    public boolean add(BookingDetail bd) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO BookingDetail (bookingID,roomID,startDate,endDate,endTime,totAmount) VALUES (?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,bd.getBookingID(),bd.getRoomID(),bd.getStartDate(),bd.getEndDate(),bd.getEndTime(),bd.getTotalAmount());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(BookingDetail bookingDetail) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public BookingDetail search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<BookingDetail> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
