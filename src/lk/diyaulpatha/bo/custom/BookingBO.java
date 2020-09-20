package lk.diyaulpatha.bo.custom;

import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.BookingDTO;

import java.sql.SQLException;

public interface BookingBO extends SuperBO {
    boolean makeBooking(BookingDTO bookingDTO) throws ClassNotFoundException, SQLException;
    int getBookingRowCount() throws ClassNotFoundException, SQLException;
}
