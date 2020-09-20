package lk.diyaulpatha.dao;

import lk.diyaulpatha.dao.custom.impl.BookingDAOImpl;
import lk.diyaulpatha.dao.custom.impl.BookingDetailDAOImpl;
import lk.diyaulpatha.dao.custom.impl.CustomerDAOImpl;
import lk.diyaulpatha.dao.custom.impl.RoomDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER, ROOM, BOOKING, BOOKINGDETAIL;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER: return new CustomerDAOImpl();
            case ROOM: return new RoomDAOImpl();
            case BOOKING: return new BookingDAOImpl();
            case BOOKINGDETAIL: return new BookingDetailDAOImpl();
            default: return null;
        }
    }
}
