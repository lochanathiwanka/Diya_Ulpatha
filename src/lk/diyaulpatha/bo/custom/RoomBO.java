package lk.diyaulpatha.bo.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.RoomDTO;

import java.sql.SQLException;

public interface RoomBO extends SuperBO {
    boolean addRoom(RoomDTO room) throws ClassNotFoundException, SQLException;
    boolean deleteRoom(RoomDTO roomDTO) throws ClassNotFoundException,SQLException;
    boolean updateRoom(RoomDTO room) throws ClassNotFoundException,SQLException;
    RoomDTO searchRoom(String id) throws ClassNotFoundException,SQLException;
    ObservableList<RoomDTO> getAllRoom() throws ClassNotFoundException,SQLException;
    CustomeDTO getRoomAvailability(String id) throws ClassNotFoundException, SQLException, NullPointerException;
    int getRoomCount() throws ClassNotFoundException, SQLException;
    int getRemovedRoomCount() throws ClassNotFoundException, SQLException;
}
