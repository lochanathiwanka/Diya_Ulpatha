package lk.diyaulpatha.dto;

import javafx.collections.ObservableList;

public class BookingDTO {
    private String bookingID;
    private String customerID;
    private String date;
    private String time;
    private String payment;
    private CustomerDTO customerDTO;
    private ObservableList<BookingDetailDTO> bookingDetailList;
    private ObservableList<RoomDTO> roomList;

    public BookingDTO() {
    }

    public BookingDTO(String bookingID, String customerID, String date, String time, String payment, CustomerDTO customerDTO, ObservableList<BookingDetailDTO> bookingDetailList, ObservableList<RoomDTO> roomList) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.date = date;
        this.time = time;
        this.payment = payment;
        this.customerDTO = customerDTO;
        this.bookingDetailList = bookingDetailList;
        this.roomList = roomList;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public ObservableList<BookingDetailDTO> getBookingDetailList() {
        return bookingDetailList;
    }

    public void setBookingDetailList(ObservableList<BookingDetailDTO> bookingDetailList) {
        this.bookingDetailList = bookingDetailList;
    }

    public ObservableList<RoomDTO> getRoomList() {
        return roomList;
    }

    public void setRoomList(ObservableList<RoomDTO> roomList) {
        this.roomList = roomList;
    }
}
