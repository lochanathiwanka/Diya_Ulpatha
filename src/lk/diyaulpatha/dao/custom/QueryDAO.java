package lk.diyaulpatha.dao.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.SuperDAO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.entity.Custome;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    Custome getRoomAvailability(String id) throws ClassNotFoundException, SQLException;
    ObservableList<Custome> getCustomerAndRoomBookingDetails(String NIC) throws ClassNotFoundException, SQLException;
}
