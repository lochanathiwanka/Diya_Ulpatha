package lk.diyaulpatha.dao;

import lk.diyaulpatha.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case BOOKING:
                return new BookingDAOImpl();
            case BOOKINGDETAIL:
                return new BookingDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case USER:
                return new UserDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, ROOM, BOOKING, BOOKINGDETAIL, QUERY, USER, EMPLOYEE;
    }
}
