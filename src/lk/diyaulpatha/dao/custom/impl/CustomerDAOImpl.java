package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.CustomerDAO;
import lk.diyaulpatha.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Customer VALUES (?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,customer.getCustomerID(),customer.getName(),customer.getNic(),
                customer.getAddress(),customer.getContact(),customer.getGender());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String SQL = "DELETE FROM Customer WHERE customerID = ?";
        return CrudUtil.executeUpdate(SQL,id);
    }

    @Override
    public boolean update(Customer customer) throws ClassNotFoundException, SQLException {
        String SQL = "UPDATE Customer SET name=?, nic=?, address=?, contact=?, gender=? WHERE customerID = ?";
        return CrudUtil.executeUpdate(SQL,customer.getName(),customer.getNic(),
                customer.getAddress(),customer.getContact(),customer.getGender(),customer.getCustomerID());
    }

    @Override
    public Customer search(String value) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Customer WHERE (customerID = ? or name = ? or nic = ?)";
        ResultSet rst = CrudUtil.executeQuery(SQL, value, value, value);
        if (rst.next()) {
            return new Customer(rst.getString("customerID"), rst.getString("name"), rst.getString("nic"),
                    rst.getString("address"), rst.getString("contact"), rst.getString("gender"));
        }
        return null;
    }

    @Override
    public ObservableList<Customer> getAll() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Customer";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (rst.next()){
            list.add(new Customer(rst.getString("customerID"),rst.getString("name"),rst.getString("nic"),
                   rst.getString("address"),rst.getString("contact"),rst.getString("gender")));
        }
        return list;
    }

    @Override
    public int getCustomerRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(customerID) FROM Customer";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()){
            return rst.getInt(1);
        }
        return -1;
    }

    @Override
    public Customer getValuesFromNIC(String NIC) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Customer WHERE nic = ?";
        ResultSet rst = CrudUtil.executeQuery(SQL, NIC);
        if (rst.next()) {
            return new Customer(rst.getString("customerID"), rst.getString("name"), rst.getString("nic"),
                    rst.getString("address"), rst.getString("contact"), rst.getString("gender"));
        }
        return null;
    }

    @Override
    public Customer getValuesFromBookingID(String id) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT c.customerID,name,nic,address,contact,gender FROM Customer c, Booking b WHERE (c.customerID=b.customerID) and b.bookingID = ?";
        ResultSet rst = CrudUtil.executeQuery(SQL, id);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getString(5), rst.getString(6));
        }
        return null;
    }

    @Override
    public ObservableList<Customer> getCustomerDetailsBetweenTwoDays(String start, String end) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT c.customerID,name,nic,address,contact,gender FROM Customer c, Booking b WHERE " +
                "(c.customerID=b.customerID) and b.date between ? and ? ";

        ResultSet rst = CrudUtil.executeQuery(SQL, start, end);
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Customer(rst.getString("customerID"), rst.getString("name"), rst.getString("nic"),
                    rst.getString("address"), rst.getString("contact"), rst.getString("gender")));
        }
        return list;
    }
}
