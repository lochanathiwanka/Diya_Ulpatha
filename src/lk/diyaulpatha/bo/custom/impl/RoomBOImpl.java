package lk.diyaulpatha.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.custom.RoomBO;
import lk.diyaulpatha.dao.DAOFactory;
import lk.diyaulpatha.dao.custom.RoomDAO;
import lk.diyaulpatha.dto.RoomDTO;
import lk.diyaulpatha.entity.Room;

import java.sql.SQLException;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean addRoom(RoomDTO r) throws ClassNotFoundException, SQLException {
        return roomDAO.add(new Room(r.getRoomID(),r.getDescription(),r.getPrice(),r.getAvailable(),r.getStatus()));
    }

    @Override
    public boolean deleteRoom(String id) throws ClassNotFoundException, SQLException {
        return roomDAO.delete(id);
    }

    @Override
    public boolean updateRoom(RoomDTO r) throws ClassNotFoundException, SQLException {
        return roomDAO.add(new Room(r.getRoomID(),r.getDescription(),r.getPrice(),r.getAvailable(),r.getStatus()));
    }

    @Override
    public RoomDTO searchRoom(String id) throws ClassNotFoundException, SQLException {
        Room room = roomDAO.search(id);
        return new RoomDTO(room.getRoomID(),room.getDescription(),room.getPrice(),
                room.getAvailable(),room.getStatus());

    }

    @Override
    public ObservableList<RoomDTO> getAllRoom() throws ClassNotFoundException, SQLException {
        ObservableList<Room> rooms = roomDAO.getAll();
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();
        for (Room r : rooms) {
            list.add(new RoomDTO(r.getRoomID(),r.getDescription(),r.getPrice(),
                    r.getAvailable(),r.getStatus()));
        }
        return list;
    }
}
