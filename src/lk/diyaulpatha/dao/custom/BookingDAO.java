package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.Booking;

import java.sql.SQLException;

public interface BookingDAO extends CrudDAO <Booking,String> {
    int getBookingRowCount() throws ClassNotFoundException, SQLException;
}
