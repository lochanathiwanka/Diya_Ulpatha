package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.QueryDAO;
import lk.diyaulpatha.entity.Custome;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Custome getRoomAvailability(String id) throws ClassNotFoundException, SQLException, NullPointerException {
        String SQL = "SELECT available,endTime,clearedDate FROM Room LEFT JOIN BookingDetail ON Room.roomID = BookingDetail.roomID WHERE Room.roomID = ? order by BookingDetail.bookingID desc limit 1";
        ResultSet rst = CrudUtil.executeQuery(SQL, id);
        if (rst.next()) {
            return new Custome(rst.getString("available"), rst.getString("endTime"), rst.getString("clearedDate"));
        }
        return null;
    }

    @Override
    public ObservableList<Custome> getCustomerAndRoomBookingDetails(String id) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT c.customerID,name,nic,address,contact,gender,b.bookingID,date,time,payment,startDate,endDate,r.roomID,code,description,bd.totAmount\n" +
                "FROM Customer c, Booking b, BookingDetail bd, Room r\n" +
                "WHERE (c.customerID=b.customerID && b.bookingID=bd.bookingID && r.roomID=bd.roomID) AND (bd.bookingID=? && available=? && status=?) ORDER BY (b.date)";

        ResultSet rst = CrudUtil.executeQuery(SQL, id, "Booked", "Exists");
        ObservableList<Custome> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Custome(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4),
                    rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8),
                    rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12),
                    rst.getString(13), rst.getString(14), rst.getString(15), Double.parseDouble(rst.getString(16))));
        }
        return list;
    }

    @Override
    public ObservableList<Custome> getAllBookingDetailsOnOneBookingID(String id) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT r.code,bd.startDate,bd.endDate,bd.endTime,bd.totAmount FROM Room r, Booking b, BookingDetail bd\n" +
                "WHERE (b.bookingID=bd.bookingID && r.roomID=bd.roomID) AND bd.bookingID = ?";

        ResultSet rst = CrudUtil.executeQuery(SQL, id);
        ObservableList<Custome> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Custome(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4),
                    Double.parseDouble(rst.getString(5))));
        }
        return list;
    }
}
