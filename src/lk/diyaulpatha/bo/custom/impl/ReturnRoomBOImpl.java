package lk.diyaulpatha.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.custom.ReturnRoomBO;
import lk.diyaulpatha.dao.DAOFactory;
import lk.diyaulpatha.dao.custom.BookingDetailDAO;
import lk.diyaulpatha.dao.custom.QueryDAO;
import lk.diyaulpatha.dao.custom.RoomDAO;
import lk.diyaulpatha.db.DBConnection;
import lk.diyaulpatha.dto.BookingDetailDTO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.RoomDTO;
import lk.diyaulpatha.entity.Custome;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnRoomBOImpl implements ReturnRoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    BookingDetailDAO bdDAO = (BookingDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOKINGDETAIL);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ObservableList<CustomeDTO> getCustomerAndRoomBookingDetails(String id) throws ClassNotFoundException, SQLException {
        ObservableList<Custome> list = queryDAO.getCustomerAndRoomBookingDetails(id);
        ObservableList<CustomeDTO> detailList = FXCollections.observableArrayList();
        for (Custome c : list) {
            detailList.add(new CustomeDTO(c.getCustomerID(),c.getName(),c.getNic(),c.getAddress(),c.getContact(),c.getGender(),
                    c.getBookingID(),c.getDate(),c.getTime(),c.getPayment(),c.getStartDate(),c.getEndDate(),
                    c.getRoomID(),c.getDescription(),c.getTotAmount()));
        }
        return detailList;
    }

    @Override
    public boolean returnRoom(ObservableList<RoomDTO> room ,ObservableList<BookingDetailDTO> bd) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isUpdatedRoomAvailable = false;
        try {
            for (RoomDTO r : room) {
                isUpdatedRoomAvailable = roomDAO.updateRoomAvailable(r.getRoomID(), r.getAvailable());
                if (!isUpdatedRoomAvailable) {
                    connection.rollback();
                    return false;
                }
            }
            if (isUpdatedRoomAvailable) {
                for (BookingDetailDTO bdetail : bd) {
                    boolean isUpdatedEndTime = bdDAO.setEndTime(bdetail.getEndTime(), bdetail.getBookingID());
                    if (!isUpdatedEndTime) {
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
                return true;

            }
            connection.rollback();
            return false;

        }finally {
            connection.setAutoCommit(true);
        }
    }
}
