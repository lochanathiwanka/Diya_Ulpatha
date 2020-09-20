package lk.diyaulpatha.dto;

public class CustomeDTO {
    private String roomID;
    private String description;
    private String startDate;
    private String endDate;
    private double totAmount;

    public CustomeDTO() {
    }

    public CustomeDTO(String roomID, String description, String startDate, String endDate, double totAmount) {
        this.roomID = roomID;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public double getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(double totAmount) {
        this.totAmount = totAmount;
    }
}
