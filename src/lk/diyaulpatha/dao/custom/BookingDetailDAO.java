package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.BookingDetail;

import java.sql.SQLException;

public interface BookingDetailDAO extends CrudDAO <BookingDetail,String> {
    boolean setEndTime(String endTime,String bookingID) throws ClassNotFoundException, SQLException;
}
