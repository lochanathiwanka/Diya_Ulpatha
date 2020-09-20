package lk.diyaulpatha.dto;

public class ReturnRoomDTO {
    private String bookingID;
    private String roomID;
    private String returnDate;
    private String returnTime;

    public ReturnRoomDTO() {
    }

    public ReturnRoomDTO(String bookingID, String roomID, String returnDate, String returnTime) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
