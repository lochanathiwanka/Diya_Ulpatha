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
    public ObservableList<Custome> getRoomAndBookingDetails(String id) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT b.bookingID,date,time,payment,startDate,endDate,r.roomID,code,description,bd.totAmount\n" +
                "FROM Booking b, BookingDetail bd, Room r\n" +
                "WHERE (b.bookingID=bd.bookingID && r.roomID=bd.roomID) AND (bd.bookingID=? && available=? && status=?) ORDER BY (b.date)";

        ResultSet rst = CrudUtil.executeQuery(SQL, id, "Booked", "Exists");
        ObservableList<Custome> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Custome(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4),
                    rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8),
                    rst.getString(9), Double.parseDouble(rst.getString(10))));
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

    @Override
    public Custome getValuesFromEmployeeName(String employeeName) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT employeeName,nic,address,contact,dob,email,gender,role,userName,password FROM Employee e LEFT JOIN User u " +
                "on e.employeeID = u.employeeID WHERE employeeName=?";

        ResultSet rst = CrudUtil.executeQuery(SQL, employeeName);
        if (rst.next()) {
            return new Custome(rst.getString("employeeName"), rst.getString("nic"), rst.getString("address"),
                    rst.getString("dob"), rst.getString("contact"), rst.getString("email"),
                    rst.getString("gender"), rst.getString("role"), rst.getString("userName"), rst.getString("password"));
        }
        return null;
    }

    @Override
    public Custome getValuesFromEmployeeNIC(String nic) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT employeeName,nic,address,contact,dob,email,gender,role,userName,password FROM Employee e LEFT JOIN User u " +
                "on e.employeeID = u.employeeID WHERE nic=?";

        ResultSet rst = CrudUtil.executeQuery(SQL, nic);
        if (rst.next()) {
            return new Custome(rst.getString("employeeName"), rst.getString("nic"), rst.getString("address"),
                    rst.getString("dob"), rst.getString("contact"), rst.getString("email"),
                    rst.getString("gender"), rst.getString("role"), rst.getString("userName"), rst.getString("password"));
        }
        return null;
    }
}
