package lk.diyaulpatha.bo;

import lk.diyaulpatha.bo.custom.impl.BookingBOImpl;
import lk.diyaulpatha.bo.custom.impl.CustomerBOImpl;
import lk.diyaulpatha.bo.custom.impl.RoomBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        if (boFactory==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        CUSTOMER, ROOM, BOOKING;
    }

    public SuperBO getBO (BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER: return new CustomerBOImpl();
            case ROOM: return new RoomBOImpl();
            case BOOKING: return new BookingBOImpl();
            default: return null;
        }
    }
}
