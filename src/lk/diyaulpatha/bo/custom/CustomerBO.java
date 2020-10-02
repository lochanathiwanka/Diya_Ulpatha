package lk.diyaulpatha.bo.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(CustomerDTO customer) throws ClassNotFoundException, SQLException;

    boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException;

    boolean updateCustomer(CustomerDTO customer) throws ClassNotFoundException, SQLException;

    CustomerDTO searchCustomer(String value) throws ClassNotFoundException, SQLException;

    ObservableList<CustomerDTO> getAllCustomer() throws ClassNotFoundException, SQLException;

    int getCustomerRowCount() throws ClassNotFoundException, SQLException;

    CustomerDTO getValuesFromNIC(String NIC) throws ClassNotFoundException, SQLException;

    CustomerDTO getValuesFromBookingID(String id) throws ClassNotFoundException, SQLException;
}
