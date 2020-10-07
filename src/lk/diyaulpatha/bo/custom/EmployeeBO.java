package lk.diyaulpatha.bo.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeBO extends SuperBO {
    int getEmployeeRowCount() throws ClassNotFoundException, SQLException;

    int getUserRowCount() throws ClassNotFoundException, SQLException;

    boolean addEmployee(String choise, EmployeeDTO employeeDTO) throws ClassNotFoundException, SQLException;

    ObservableList<EmployeeDTO> getAllEmployeeDetails() throws ClassNotFoundException, SQLException;

    CustomeDTO getValuesFromEmployeeName(String employeeName) throws ClassNotFoundException, SQLException;

    boolean updateEmployee(String choise, EmployeeDTO employeeDTO) throws ClassNotFoundException, SQLException;

    boolean removeEmployee(String id) throws ClassNotFoundException, SQLException;

    CustomeDTO getValuesFromEmployeeNIC(String nic) throws ClassNotFoundException, SQLException;
}
