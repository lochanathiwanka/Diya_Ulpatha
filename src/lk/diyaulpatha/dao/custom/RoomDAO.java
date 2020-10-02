package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.entity.Room;

import java.sql.SQLException;

public interface RoomDAO extends CrudDAO<Room, String> {
    boolean updateRoomAvailable(String id, String av) throws ClassNotFoundException, SQLException;

    int getRoomCount() throws ClassNotFoundException, SQLException;

    int getRemovedRoomCount() throws ClassNotFoundException, SQLException;

    boolean deleteRoom(Room r) throws ClassNotFoundException, SQLException;

    String getRoomImage(String code) throws ClassNotFoundException, SQLException;
}
