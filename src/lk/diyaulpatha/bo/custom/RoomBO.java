package lk.diyaulpatha.bo.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.CustomerDTO;
import lk.diyaulpatha.dto.RoomDTO;

import java.sql.SQLException;

public interface RoomBO extends SuperBO {
    boolean addRoom(RoomDTO room) throws ClassNotFoundException, SQLException;
    boolean deleteRoom(String id) throws ClassNotFoundException,SQLException;
    boolean updateRoom(RoomDTO room) throws ClassNotFoundException,SQLException;
    RoomDTO searchRoom(String id) throws ClassNotFoundException,SQLException;
    ObservableList<RoomDTO> getAllRoom() throws ClassNotFoundException,SQLException;
}
