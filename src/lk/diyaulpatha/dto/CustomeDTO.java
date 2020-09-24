package lk.diyaulpatha.dto;

public class CustomeDTO {
    private String roomID;
    private String description;
    private double price;
    private String available;
    private String startDate;
    private String endDate;
    private String endTime;
    private double totAmount;

    public CustomeDTO() {
    }

    public CustomeDTO(String available,String endTime) {
        this.available = available;
        this.endTime = endTime;
    }

    public CustomeDTO(String roomID, String description, double price, String available, String endTime) {
        this.roomID = roomID;
        this.description = description;
        this.price = price;
        this.available = available;
        this.endTime = endTime;
    }

    public CustomeDTO(String roomID, String description, String startDate, String endDate, double totAmount) {
        this.roomID = roomID;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totAmount = totAmount;
    }

    public CustomeDTO(String roomID, String description, double price, String available, String startDate, String endDate, String endTime, double totAmount) {
        this.roomID = roomID;
        this.description = description;
        this.price = price;
        this.available = available;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
        this.totAmount = totAmount;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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

    public double getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(double totAmount) {
        this.totAmount = totAmount;
    }
}
