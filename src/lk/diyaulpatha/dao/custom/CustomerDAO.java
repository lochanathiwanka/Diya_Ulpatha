package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    int getCustomerRowCount() throws ClassNotFoundException, SQLException;

    Customer getValuesFromNIC(String NIC) throws ClassNotFoundException, SQLException;

    Customer getValuesFromBookingID(String id) throws ClassNotFoundException, SQLException;
}
