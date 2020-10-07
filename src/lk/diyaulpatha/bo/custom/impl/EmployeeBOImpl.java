package lk.diyaulpatha.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.custom.EmployeeBO;
import lk.diyaulpatha.dao.DAOFactory;
import lk.diyaulpatha.dao.custom.EmployeeDAO;
import lk.diyaulpatha.dao.custom.QueryDAO;
import lk.diyaulpatha.dao.custom.UserDAO;
import lk.diyaulpatha.db.DBConnection;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.EmployeeDTO;
import lk.diyaulpatha.dto.UserDTO;
import lk.diyaulpatha.entity.Custome;
import lk.diyaulpatha.entity.Employee;
import lk.diyaulpatha.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public int getEmployeeRowCount() throws ClassNotFoundException, SQLException {
        return employeeDAO.getEmployeeRowCount();
    }

    @Override
    public int getUserRowCount() throws ClassNotFoundException, SQLException {
        return userDAO.getUserRowCount();
    }

    @Override
    public boolean addEmployee(String choise, EmployeeDTO e) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            if (choise.equalsIgnoreCase("yes")) {
                boolean isAddedEmployee = employeeDAO.add(new Employee(e.getEmployeeID(), e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(),
                        e.getContact(), e.getEmail(), e.getGender(), e.getRole(), e.getStatus()));

                if (isAddedEmployee) {
                    UserDTO u = e.getUserDetail();
                    boolean isAddedUser = userDAO.add(new User(u.getUserID(), u.getEmployeeID(), u.getUserName(), u.getPassword()));
                    if (isAddedUser) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            }
            if (choise.equalsIgnoreCase("no")) {
                boolean isAddedEmployee = employeeDAO.add(new Employee(e.getEmployeeID(), e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(),
                        e.getContact(), e.getEmail(), e.getGender(), e.getRole(), e.getStatus()));

                if (isAddedEmployee) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException ex) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public ObservableList<EmployeeDTO> getAllEmployeeDetails() throws ClassNotFoundException, SQLException {
        ObservableList<Employee> all = employeeDAO.getAll();
        ObservableList<EmployeeDTO> list = FXCollections.observableArrayList();
        for (Employee e : all) {
            list.add(new EmployeeDTO(e.getEmployeeID(), e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(), e.getContact(), e.getEmail(), e.getGender(), e.getRole(), e.getStatus()));
        }
        return list;
    }

    @Override
    public CustomeDTO getValuesFromEmployeeName(String employeeName) throws ClassNotFoundException, SQLException {
        Custome e = queryDAO.getValuesFromEmployeeName(employeeName);
        return new CustomeDTO(e.getEmployeeName(), e.getEmployeeNic(), e.getEmployeeAddress(), e.getEmployeeDob(), e.getEmployeeContact(),
                e.getEmployeeEmail(), e.getEmployeeGender(), e.getEmployeeRole(), e.getUserName(), e.getPassword());
    }

    @Override
    public boolean updateEmployee(String choise, EmployeeDTO e) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            if (choise.equalsIgnoreCase("yes")) {
                boolean isUpdatedEmployee = employeeDAO.update(new Employee(e.getEmployeeID(), e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(),
                        e.getContact(), e.getEmail(), e.getGender(), e.getRole(), e.getStatus()));
                if (isUpdatedEmployee) {
                    User isSearched = userDAO.search(e.getEmployeeID());
                    UserDTO u = e.getUserDetail();
                    if (isSearched != null) {
                        boolean isUpdatedUser = userDAO.update(new User(isSearched.getUserID(), isSearched.getEmployeeID(), u.getUserName(), u.getPassword()));
                        if (isUpdatedUser) {
                            connection.commit();
                            return true;
                        } else {
                            connection.rollback();
                            return false;
                        }
                    } else {
                        boolean isAddedUser = userDAO.add(new User(e.getUserDetail().getUserID(), e.getEmployeeID(), u.getUserName(), u.getPassword()));
                        if (isAddedUser) {
                            connection.commit();
                            return true;
                        } else {
                            connection.rollback();
                            return false;
                        }
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            }
            if (choise.equalsIgnoreCase("no")) {
                boolean isUpdatedEmployee = employeeDAO.update(new Employee(e.getEmployeeID(), e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(),
                        e.getContact(), e.getEmail(), e.getGender(), e.getRole(), e.getStatus()));
                if (isUpdatedEmployee) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean removeEmployee(String id) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isUpdatedStatus = employeeDAO.updateStatus(id);
            if (isUpdatedStatus) {
                boolean isDeletedUser = userDAO.delete(id);
                if (isDeletedUser) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException ex) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public CustomeDTO getValuesFromEmployeeNIC(String nic) throws ClassNotFoundException, SQLException {
        Custome e = queryDAO.getValuesFromEmployeeNIC(nic);
        return new CustomeDTO(e.getEmployeeName(), e.getEmployeeNic(), e.getEmployeeAddress(), e.getEmployeeDob(), e.getEmployeeContact(),
                e.getEmployeeEmail(), e.getEmployeeGender(), e.getEmployeeRole(), e.getUserName(), e.getPassword());
    }
}
