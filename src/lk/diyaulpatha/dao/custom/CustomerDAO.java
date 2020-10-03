package lk.diyaulpatha.dao.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    int getCustomerRowCount() throws ClassNotFoundException, SQLException;

    Customer getValuesFromNIC(String NIC) throws ClassNotFoundException, SQLException;

    Customer getValuesFromBookingID(String id) throws ClassNotFoundException, SQLException;

    ObservableList<Customer> getCustomerDetailsBetweenTwoDays(String start, String end) throws ClassNotFoundException, SQLException;
}
