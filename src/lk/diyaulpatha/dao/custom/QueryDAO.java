package lk.diyaulpatha.dao.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.SuperDAO;
import lk.diyaulpatha.entity.Custome;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    Custome getRoomAvailability(String id) throws ClassNotFoundException, SQLException, NullPointerException;

    ObservableList<Custome> getRoomAndBookingDetails(String id) throws ClassNotFoundException, SQLException;

    ObservableList<Custome> getAllBookingDetailsOnOneBookingID(String id) throws ClassNotFoundException, SQLException;

    Custome getValuesFromEmployeeName(String employeeName) throws ClassNotFoundException, SQLException;

    Custome getValuesFromEmployeeNIC(String nic) throws ClassNotFoundException, SQLException;
}
