package lk.diyaulpatha.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.custom.CustomerBO;
import lk.diyaulpatha.dao.DAOFactory;
import lk.diyaulpatha.dao.custom.CustomerDAO;
import lk.diyaulpatha.dto.CustomerDTO;
import lk.diyaulpatha.entity.Customer;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO custDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO c) throws ClassNotFoundException, SQLException {
        return custDAO.add(new Customer(c.getCustomerID(),c.getName(),c.getNic(),c.getAddress(),
                c.getContact(),c.getGender()));
    }

    @Override
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return custDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO c) throws ClassNotFoundException, SQLException {
        return custDAO.update(new Customer(c.getCustomerID(),c.getName(),c.getNic(),c.getAddress(),
                c.getContact(),c.getGender()));
    }

    @Override
    public CustomerDTO searchCustomer(String value) throws ClassNotFoundException, SQLException {
        Customer c = custDAO.search(value);
        return new CustomerDTO(c.getCustomerID(), c.getName(), c.getNic(), c.getAddress(),
                c.getContact(), c.getGender());
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomer() throws ClassNotFoundException, SQLException {
        ObservableList<Customer> customers = custDAO.getAll();
        ObservableList<CustomerDTO> list = FXCollections.observableArrayList();
        for (Customer c : customers) {
            list.add(new CustomerDTO(c.getCustomerID(),c.getName(),c.getNic(),c.getAddress(),
                    c.getContact(),c.getGender()));

        }
        return list;
    }

    @Override
    public int getCustomerRowCount() throws ClassNotFoundException, SQLException {
        return custDAO.getCustomerRowCount();
    }

    @Override
    public CustomerDTO getValuesFromNIC(String NIC) throws ClassNotFoundException, SQLException {
        Customer c = custDAO.getValuesFromNIC(NIC);
        return new CustomerDTO(c.getCustomerID(), c.getName(), c.getNic(), c.getAddress(),
                c.getContact(), c.getGender());
    }

    @Override
    public CustomerDTO getValuesFromBookingID(String id) throws ClassNotFoundException, SQLException {
        Customer cust = custDAO.getValuesFromBookingID(id);
        return new CustomerDTO(cust.getCustomerID(), cust.getName(), cust.getNic(), cust.getAddress(), cust.getContact(), cust.getGender());
    }

    @Override
    public ObservableList<CustomerDTO> getCustomerDetailsBetweenTwoDays(String start, String end) throws ClassNotFoundException, SQLException {
        ObservableList<Customer> all = custDAO.getCustomerDetailsBetweenTwoDays(start, end);
        ObservableList<CustomerDTO> list = FXCollections.observableArrayList();
        for (Customer c : all) {
            list.add(new CustomerDTO(c.getCustomerID(), c.getName(), c.getNic(), c.getAddress(), c.getContact(), c.getGender()));
        }
        return list;
    }
}
