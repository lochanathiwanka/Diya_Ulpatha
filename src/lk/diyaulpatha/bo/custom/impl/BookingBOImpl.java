package lk.diyaulpatha.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.diyaulpatha.bo.custom.BookingBO;
import lk.diyaulpatha.dao.DAOFactory;
import lk.diyaulpatha.dao.custom.BookingDAO;
import lk.diyaulpatha.dao.custom.BookingDetailDAO;
import lk.diyaulpatha.dao.custom.CustomerDAO;
import lk.diyaulpatha.dao.custom.RoomDAO;
import lk.diyaulpatha.db.DBConnection;
import lk.diyaulpatha.dto.BookingDTO;
import lk.diyaulpatha.dto.BookingDetailDTO;
import lk.diyaulpatha.dto.CustomerDTO;
import lk.diyaulpatha.dto.RoomDTO;
import lk.diyaulpatha.entity.Booking;
import lk.diyaulpatha.entity.BookingDetail;
import lk.diyaulpatha.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public class BookingBOImpl implements BookingBO {
    CustomerDAO custDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    BookingDAO bookingDAO = (BookingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOKING);
    BookingDetailDAO bdetailsDAO = (BookingDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOKINGDETAIL);


    @Override
    public boolean makeBooking(BookingDTO b) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CustomerDTO c = b.getCustomerDTO();
        Customer customer = new Customer(c.getCustomerID(),c.getName(),c.getNic(),c.getAddress(),c.getContact(),c.getGender());
        Customer custSearch = custDAO.search(c.getCustomerID());
        try {
            if (custSearch!=null) {
                boolean isAddedBooking = bookingDAO.add(new Booking(b.getBookingID(), b.getCustomerID(), b.getDate(), b.getTime(), b.getPayment()));
                if (isAddedBooking) {
                    boolean isAddedBookingDetail = false;
                    for (BookingDetailDTO bd : b.getBookingDetailList()) {
                        isAddedBookingDetail = bdetailsDAO.add(new BookingDetail(bd.getBookingID(), bd.getRoomID(), bd.getStartDate(),
                                bd.getEndDate(), bd.getEndTime(), bd.getTotalAmount()));

                        if (!isAddedBookingDetail) {
                            connection.rollback();
                            return false;
                        }

                    }
                    if (isAddedBookingDetail) {
                        for (RoomDTO r : b.getRoomList()) {
                            boolean isUpdatedRoom = roomDAO.updateRoomAvailable(r.getRoomID(), r.getAvailable());
                            if (!isUpdatedRoom) {
                                connection.rollback();
                                return false;
                            }
                        }
                        connection.commit();
                        return true;
                    }
                }

            }else {
                boolean isAddedCustomer = custDAO.add(customer);
                if (isAddedCustomer) {
                    boolean isAddedBooking = bookingDAO.add(new Booking(b.getBookingID(), b.getCustomerID(), b.getDate(), b.getTime(), b.getPayment()));
                    if (isAddedBooking) {
                        boolean isAddedBookingDetail = false;
                        for (BookingDetailDTO bd : b.getBookingDetailList()) {
                            isAddedBookingDetail = bdetailsDAO.add(new BookingDetail(bd.getBookingID(), bd.getRoomID(), bd.getStartDate(),
                                    bd.getEndDate(), bd.getEndTime(), bd.getTotalAmount()));

                            if (!isAddedBookingDetail) {
                                connection.rollback();
                                return false;
                            }

                        }
                        if (isAddedBookingDetail) {
                            for (RoomDTO r : b.getRoomList()) {
                                boolean isUpdatedRoom = roomDAO.updateRoomAvailable(r.getRoomID(), r.getAvailable());
                                if (!isUpdatedRoom) {
                                    connection.rollback();
                                    return false;
                                }
                            }
                            connection.commit();
                            return true;
                        }
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            }
        }catch (SQLException ex){
            connection.rollback();
            return false;
        }
        finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public int getBookingRowCount() throws ClassNotFoundException, SQLException {
        return bookingDAO.getBookingRowCount();
    }

    @Override
    public String getLastBookingID(String NIC, String name, String contact) throws ClassNotFoundException, SQLException {
        return bookingDAO.getLastBookingID(NIC, name, contact);
    }

    @Override
    public ObservableList<BookingDTO> getAllBookingIDOnOneCustomer(String name) throws ClassNotFoundException, SQLException {
        ObservableList<Booking> list = bookingDAO.getAllBookingIDOnOneCustomer(name);
        ObservableList<BookingDTO> bookingIDList = FXCollections.observableArrayList();
        for (Booking b : list) {
            bookingIDList.add(new BookingDTO(b.getBookingID()));
        }
        return bookingIDList;
    }

    @Override
    public BookingDTO search(String bookingID) throws ClassNotFoundException, SQLException {
        Booking booking = bookingDAO.search(bookingID);
        return new BookingDTO(booking.getDate(), booking.getTime(), booking.getPayment());
    }

    @Override
    public BookingDTO getBookingIDOnDate(String value, String name) throws ClassNotFoundException, SQLException {
        Booking booking = bookingDAO.getBookingIDOnDate(value, name);
        return new BookingDTO(booking.getBookingID());
    }

    @Override
    public ObservableList<BookingDTO> getAll() throws ClassNotFoundException, SQLException {
        ObservableList<Booking> all = bookingDAO.getAll();
        ObservableList<BookingDTO> list = FXCollections.observableArrayList();
        for (Booking b : all) {
            list.add(new BookingDTO(b.getBookingID(), b.getDate(), b.getTime(), b.getPayment()));
        }
        return list;
    }
}
