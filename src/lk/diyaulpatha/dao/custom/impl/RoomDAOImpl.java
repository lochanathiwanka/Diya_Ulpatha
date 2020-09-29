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
    public int getRoomCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(roomID) FROM Room WHERE status='Exists'";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()){
            return rst.getInt(1);
        }
        return -1;
    }

    @Override
    public int getRemovedRoomCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(roomID) FROM Room WHERE status='Removed'";
        ResultSet rst = CrudUtil.executeQuery(SQL);
        if (rst.next()){
            return rst.getInt(1);
        }
        return -1;
    }

    @Override
    public boolean add(Room r) throws ClassNotFoundException, SQLException {
        String SQL = "INSERT INTO Room (roomID, code, description, price,image) VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(SQL,r.getRoomID(),r.getCode(),r.getDescription(),r.getPrice(),r.getImage());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean deleteRoom(Room r) throws ClassNotFoundException, SQLException {
        int removedRoomCount = getRemovedRoomCount();
        String id = "";
        if (removedRoomCount == 0) {
            id = "X001";
        }
        if (removedRoomCount > 0 && removedRoomCount < 9) {
            id = "X00" + (removedRoomCount + 1);
        }
        if (removedRoomCount >= 9 && removedRoomCount < 99) {
            id = "X0" + (removedRoomCount + 1);
        }
        if (removedRoomCount >= 99) {
            id = "X" + (removedRoomCount + 1);
        }
        String SQL = "UPDATE Room SET roomID=?, code=?, description=?, price=?, status=?, image=? WHERE code=?";
        return CrudUtil.executeUpdate(SQL,id,r.getCode(),r.getDescription(),r.getPrice(),r.getStatus(),r.getImage(),r.getCode());
    }

    @Override
    public boolean update(Room r) throws ClassNotFoundException, SQLException {
        String SQL = "UPDATE Room SET code=?, description=?, price=?, status=?, image=? WHERE code=?";
        return CrudUtil.executeUpdate(SQL,r.getCode(),r.getDescription(), r.getPrice(), r.getStatus(), r.getImage(), r.getCode());
    }

    @Override
    public Room search(String code) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Room WHERE (code=? && status=?)";
        ResultSet rst = CrudUtil.executeQuery(SQL,code,"Exists");
        if (rst.next()){
            return new Room(rst.getString("roomID"),rst.getString("code"),rst.getString("description"),
                    rst.getDouble("price"),rst.getString("available"),
                    rst.getString("status"),rst.getString("image"));
        }
        return null;
    }

    @Override
    public ObservableList<Room> getAll() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Room WHERE status=?";
        ResultSet rst = CrudUtil.executeQuery(SQL,"Exists");
        ObservableList<Room> list = FXCollections.observableArrayList();
        while (rst.next()){
            list.add(new Room(rst.getString("roomID"),rst.getString("code"),rst.getString("description"),
                    rst.getDouble("price"),rst.getString("available"),
                    rst.getString("status"),rst.getString("image")));
        }
        return list;
    }
}
