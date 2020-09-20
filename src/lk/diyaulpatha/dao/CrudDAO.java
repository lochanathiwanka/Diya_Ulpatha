package lk.diyaulpatha.dao;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CrudDAO <T,ID> extends SuperDAO {
    boolean add(T t) throws ClassNotFoundException, SQLException;
    boolean delete(ID id) throws ClassNotFoundException,SQLException;
    boolean update(T t) throws ClassNotFoundException,SQLException;
    T search(ID id) throws ClassNotFoundException,SQLException;
    ObservableList<T> getAll() throws ClassNotFoundException,SQLException;
}
