package lk.diyaulpatha.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.dao.CrudUtil;
import lk.diyaulpatha.dao.custom.QueryDAO;
import lk.diyaulpatha.entity.Custome;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Custome getRoomAvailability(String id) throws ClassNotFoundException, SQLException {
        String SQL = "select available,endTime from Room left join BookingDetail on Room.roomID = BookingDetail.roomID where Room.roomID = ?";
        ResultSet rst = CrudUtil.executeQuery(SQL,id);
        if (rst.next()){
            return new Custome(rst.getString("available"),rst.getString("endTime"));
        }
        return null;
    }
}
