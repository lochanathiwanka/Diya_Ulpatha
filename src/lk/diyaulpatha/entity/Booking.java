package lk.diyaulpatha.entity;

public class Booking {
    private String bookingID;
    private String customerID;
    private String date;
    private String time;
    private String payment;

    public Booking() {
    }

    public Booking(String bookingID, String customerID, String date, String time, String payment) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.date = date;
        this.time = time;
        this.payment = payment;
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
}
