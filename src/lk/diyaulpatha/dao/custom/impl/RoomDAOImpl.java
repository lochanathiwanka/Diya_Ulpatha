package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.RoomDAO;
import lk.diyaulpatha.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean updateRoomAvailable(String id,String av) throws ClassNotFoundException, SQLException {
        String SQL = "UPDATE Room SET available=? WHERE roomID=?";
        return CrudUtil.executeUpdate(SQL,av,id);
    }

    @Override
    public boolean add(Room r) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Room (roomID, description, price) VALUES (?,?,?)";
        return CrudUtil.executeUpdate(SQL,r.getRoomID(),r.getDescription(),r.getPrice());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Room room) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Room search(String id) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Room WHERE roomID=?";
        ResultSet rst = CrudUtil.executeQuery(SQL,id);
        if (rst.next()){
            return new Room(rst.getString("roomID"),rst.getString("description"),
                    rst.getDouble("price"),rst.getString("available"),
                    rst.getString("status"));
        }
        return null;
    }

    @Override
    public ObservableList<Room> getAll() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Room";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        ObservableList<Room> list = FXCollections.observableArrayList();
        while (rst.next()){
            list.add(new Room(rst.getString("roomID"),rst.getString("description"),
                    rst.getDouble("price"),rst.getString("available"),
                    rst.getString("status")));
        }
        return list;
    }
}
