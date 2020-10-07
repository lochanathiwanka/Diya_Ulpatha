package lk.diyaulpatha.dao.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.Booking;

import java.sql.SQLException;

public interface BookingDAO extends CrudDAO<Booking, String> {
    int getBookingRowCount() throws ClassNotFoundException, SQLException;

    ObservableList<String> getAllToBePayedBookingIDOnOneCustomer(String NIC, String name, String contact) throws ClassNotFoundException, SQLException;

    ObservableList<Booking> getAllBookingIDOnOneCustomer(String value) throws ClassNotFoundException, SQLException;

    ObservableList<Booking> getBookingIDOnDate(String value, String name) throws ClassNotFoundException, SQLException;

    ObservableList<Booking> getBookingIDBetweenTwoDays(String start, String end) throws ClassNotFoundException, SQLException;

    ObservableList<Booking> getAllBookingIDOnRoomCode(String code) throws ClassNotFoundException, SQLException;

    ObservableList<Booking> getAllBoookingDetailBetweenTwoDaysOnRoomCode(String code, String start, String end) throws ClassNotFoundException, SQLException;
}
