package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.EmployeeDAO;
import lk.diyaulpatha.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public int getEmployeeRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(employeeID) FROM Employee";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return -1;
    }

    @Override
    public boolean updateStatus(String id) throws ClassNotFoundException, SQLException {
        String SQL = "UPDATE Employee set status=? WHERE employeeID=?";
        return CrudUtil.executeUpdate(SQL, "Removed", id);
    }

    @Override
    public boolean add(Employee e) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Employee (employeeID,employeeName,nic,address,dob,contact,email,gender,role) VALUES (?,?,?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL, e.getEmployeeID(), e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(),
                e.getContact(), e.getEmail(), e.getGender(), e.getRole());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String SQL = "DELETE FROM Employee WHERE employeeID=?";
        return CrudUtil.executeUpdate(SQL, id);
    }

    @Override
    public boolean update(Employee e) throws ClassNotFoundException, SQLException {
        String SQL = "UPDATE Employee set employeeName=?, nic=?, address=?, dob=?, contact=?, email=?, gender=?, role=? WHERE employeeID=?";
        return CrudUtil.executeUpdate(SQL, e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(),
                e.getContact(), e.getEmail(), e.getGender(), e.getRole(), e.getEmployeeID());
    }

    @Override
    public Employee search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Employee> getAll() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Employee WHERE status=?";
        ResultSet rst = CrudUtil.executeQuery(SQL, "Exists");
        ObservableList<Employee> list = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new Employee(rst.getString("employeeID"), rst.getString("employeeName"), rst.getString("nic"),
                    rst.getString("address"), rst.getString("dob"), rst.getString("contact"), rst.getString("email"),
                    rst.getString("gender"), rst.getString("role"), rst.getString("status")));
        }
        return list;
    }
}
