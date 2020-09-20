package lk.diyaulpatha.entity;

public class Room {
    private String roomID;
    private String description;
    private double price;
    private String available;
    private String status;

    public Room() {
    }

    public Room(String roomID, String description, double price, String available, String status) {
        this.roomID = roomID;
        this.description = description;
        this.price = price;
        this.available = available;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
