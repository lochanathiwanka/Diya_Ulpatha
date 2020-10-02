package lk.diyaulpatha.bo.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.CustomeDTO;

import java.sql.SQLException;

public interface BookingDetailBO extends SuperBO {
    ObservableList<CustomeDTO> getAllBookingDetailsOnOneBookingID(String id) throws ClassNotFoundException, SQLException;
}
