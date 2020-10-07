package lk.diyaulpatha.bo.custom;

import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.SuperBO;
import lk.diyaulpatha.dto.BookingDetailDTO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.RoomDTO;

import java.sql.SQLException;

public interface ReturnRoomBO extends SuperBO {
    ObservableList<CustomeDTO> getRoomAndBookingDetails(String id) throws ClassNotFoundException, SQLException;

    boolean returnRoom(ObservableList<RoomDTO> room, ObservableList<BookingDetailDTO> bd) throws ClassNotFoundException, SQLException;
}
