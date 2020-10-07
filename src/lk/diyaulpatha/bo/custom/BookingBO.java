package lk.diyaulpatha.bo.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.BookingDTO;

import java.sql.SQLException;

public interface BookingBO extends SuperBO {
    boolean makeBooking(BookingDTO bookingDTO) throws ClassNotFoundException, SQLException;

    int getBookingRowCount() throws ClassNotFoundException, SQLException;

    ObservableList<String> getAllToBePayedBookingIDOnOneCustomer(String NIC, String name, String contact) throws ClassNotFoundException, SQLException;

    ObservableList<BookingDTO> getAllBookingIDOnOneCustomer(String name) throws ClassNotFoundException, SQLException;

    BookingDTO search(String bookingID) throws ClassNotFoundException, SQLException;

    ObservableList<BookingDTO> getBookingIDOnDate(String value, String name) throws ClassNotFoundException, SQLException;

    ObservableList<BookingDTO> getAll() throws ClassNotFoundException, SQLException;

    ObservableList<BookingDTO> getBookingIDBetweenTwoDays(String start, String end) throws ClassNotFoundException, SQLException;

    ObservableList<BookingDTO> getAllBookingIDOnRoomCode(String code) throws ClassNotFoundException, SQLException;

    ObservableList<BookingDTO> getAllBoookingDetailBetweenTwoDaysOnRoomCode(String code, String start, String end) throws ClassNotFoundException, SQLException;
}
