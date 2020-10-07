package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee, String> {
    int getEmployeeRowCount() throws ClassNotFoundException, SQLException;

    boolean updateStatus(String id) throws ClassNotFoundException, SQLException;
}
