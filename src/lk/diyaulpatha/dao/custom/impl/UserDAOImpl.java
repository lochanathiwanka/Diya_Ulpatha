package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.UserDAO;
import lk.diyaulpatha.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public int getUserRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(userID) FROM User";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return -1;
    }

    @Override
    public boolean add(User u) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO User VALUES (?,?,?,?)";
        return CrudUtil.executeUpdate(SQL, u.getUserID(), u.getEmployeeID(), u.getUserName(), u.getPassword());
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        String SQL = "DELETE FROM User WHERE employeeID=?";
        return CrudUtil.executeUpdate(SQL, id);
    }

    @Override
    public boolean update(User u) throws ClassNotFoundException, SQLException {
        String SQL = "UPDATE User SET employeeID=?, userName=?, password=? WHERE userID=?";
        return CrudUtil.executeUpdate(SQL, u.getEmployeeID(), u.getUserName(), u.getPassword(), u.getUserID());
    }

    @Override
    public User search(String id) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * from User WHERE employeeID=?";
        ResultSet rst = CrudUtil.executeQuery(SQL, id);
        if (rst.next()) {
            return new User(rst.getString("userID"), rst.getString("employeeID"),
                    rst.getString("userName"), rst.getString("Password"));
        }
        return null;
    }

    @Override
    public ObservableList<User> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
