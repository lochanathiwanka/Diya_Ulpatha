package lk.diyaulpatha.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.custom.BookingDetailBO;
import lk.diyaulpatha.dao.DAOFactory;
import lk.diyaulpatha.dao.custom.QueryDAO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.entity.Custome;

import java.sql.SQLException;

public class BookingDetailBOImpl implements BookingDetailBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ObservableList<CustomeDTO> getAllBookingDetailsOnOneBookingID(String id) throws ClassNotFoundException, SQLException {
        ObservableList<Custome> list = queryDAO.getAllBookingDetailsOnOneBookingID(id);
        ObservableList<CustomeDTO> bookingDetailList = FXCollections.observableArrayList();
        for (Custome c : list) {
            bookingDetailList.add(new CustomeDTO(c.getCode(), c.getStartDate(), c.getEndDate(), c.getEndTime(), c.getTotAmount()));
        }
        return bookingDetailList;
    }
}
