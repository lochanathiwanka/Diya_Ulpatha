package lk.diyaulpatha.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.custom.RoomBO;
import lk.diyaulpatha.dao.DAOFactory;
import lk.diyaulpatha.dao.custom.QueryDAO;
import lk.diyaulpatha.dao.custom.RoomDAO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.RoomDTO;
import lk.diyaulpatha.entity.Custome;
import lk.diyaulpatha.entity.Room;

import java.sql.SQLException;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public boolean addRoom(RoomDTO r) throws ClassNotFoundException, SQLException {
        return roomDAO.add(new Room(r.getRoomID(),r.getCode(),r.getDescription(),r.getPrice(),r.getAvailable(),r.getStatus(),r.getImage()));
    }

    @Override
    public boolean deleteRoom(RoomDTO r ) throws ClassNotFoundException, SQLException {
        return roomDAO.deleteRoom(new Room(r.getRoomID(),r.getCode(),r.getDescription(),r.getPrice(),r.getAvailable(),r.getStatus(),r.getImage()));
    }

    @Override
    public boolean updateRoom(RoomDTO r) throws ClassNotFoundException, SQLException {
        return roomDAO.update(new Room(r.getRoomID(),r.getCode(),r.getDescription(),r.getPrice(),r.getAvailable(),r.getStatus(),r.getImage()));
    }

    @Override
    public RoomDTO searchRoom(String id) throws ClassNotFoundException, SQLException {
        Room room = roomDAO.search(id);
        return new RoomDTO(room.getRoomID(),room.getCode(),room.getDescription(),room.getPrice(),
                room.getAvailable(),room.getStatus(),room.getImage());

    }

    @Override
    public ObservableList<RoomDTO> getAllRoom() throws ClassNotFoundException, SQLException {
        ObservableList<Room> rooms = roomDAO.getAll();
        ObservableList<RoomDTO> list = FXCollections.observableArrayList();
        for (Room r : rooms) {
            list.add(new RoomDTO(r.getRoomID(),r.getCode(),r.getDescription(),r.getPrice(),
                    r.getAvailable(),r.getStatus(),r.getImage()));
        }
        return list;
    }

    @Override
    public CustomeDTO getRoomAvailability(String id) throws ClassNotFoundException, SQLException,NullPointerException {
        Custome custome = queryDAO.getRoomAvailability(id);
        return new CustomeDTO(custome.getAvailable(), custome.getEndTime(), custome.getClearedDate());
    }

    @Override
    public int getRoomCount() throws ClassNotFoundException, SQLException {
        return roomDAO.getRoomCount();
    }

    @Override
    public int getRemovedRoomCount() throws ClassNotFoundException, SQLException {
        return roomDAO.getRemovedRoomCount();
    }

    @Override
    public String getRoomImage(String code) throws ClassNotFoundException, SQLException {
        return roomDAO.getRoomImage(code);
    }

    @Override
    public String getRoomImageFromBookingID(String id) throws ClassNotFoundException, SQLException {
        return roomDAO.getRoomImageFromBookingID(id);
    }
}
