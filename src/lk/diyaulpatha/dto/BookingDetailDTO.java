package lk.diyaulpatha.dto;

public class BookingDetailDTO {
    private String bookingID;
    private String roomID;
    private String startDate;
    private String endDate;
    private String endTime;
    private String clearedDate;
    private double totalAmount;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(String bookingID, String endTime, String clearedDate) {
        this.bookingID = bookingID;
        this.endTime = endTime;
        this.clearedDate = clearedDate;
    }

    public BookingDetailDTO(String bookingID, String roomID, String startDate, String endDate, double totalAmount) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    public BookingDetailDTO(String bookingID, String roomID, String startDate, String endDate, String endTime, double totalAmount) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
        this.totalAmount = totalAmount;
    }

    public BookingDetailDTO(String bookingID, String roomID, String startDate, String endDate, String endTime, String clearedDate, double totalAmount) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
        this.clearedDate = clearedDate;
        this.totalAmount = totalAmount;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getClearedDate() {
        return clearedDate;
    }

    public void setClearedDate(String clearedDate) {
        this.clearedDate = clearedDate;
    }
}
