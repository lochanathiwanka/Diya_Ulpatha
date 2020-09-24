package lk.diyaulpatha.entity;

public class Custome {
    private String roomID;
    private String description;
    private double price;
    private String available;
    private String endTime;

    public Custome() {
    }

    public Custome(String available,String endTime) {
        this.available = available;
        this.endTime = endTime;
    }

    public Custome(String roomID, String description, double price, String available, String endTime) {
        this.roomID = roomID;
        this.description = description;
        this.price = price;
        this.available = available;
        this.endTime = endTime;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
