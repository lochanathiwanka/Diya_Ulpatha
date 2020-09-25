package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.dao.SuperDAO;
import lk.diyaulpatha.entity.Room;

import java.sql.SQLException;

public interface RoomDAO extends CrudDAO<Room,String> {
    boolean updateRoomAvailable(String id, String av) throws ClassNotFoundException, SQLException;
}
