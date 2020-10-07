package lk.diyaulpatha.bo;

import lk.diyaulpatha.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case BOOKING:
                return new BookingBOImpl();
            case RETURNROOM:
                return new ReturnRoomBOImpl();
            case BOOKINGDETAIL:
                return new BookingDetailBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        CUSTOMER, ROOM, BOOKING, RETURNROOM, BOOKINGDETAIL, EMPLOYEE
    }
}
