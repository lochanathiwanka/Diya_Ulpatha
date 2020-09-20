package lk.diyaulpatha.dao.custom;

import lk.diyaulpatha.dao.CrudDAO;
import lk.diyaulpatha.dao.SuperDAO;
import lk.diyaulpatha.entity.Room;

import java.sql.SQLException;

public interface RoomDAO extends CrudDAO<Room,String> {
    boolean updateRoomStatus(String id, String av) throws ClassNotFoundException, SQLException;
}
