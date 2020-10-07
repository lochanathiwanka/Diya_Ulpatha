package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User, String> {
    int getUserRowCount() throws ClassNotFoundException, SQLException;
}
