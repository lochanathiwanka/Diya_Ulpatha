package lk.diyaulpatha.entity;

public class Food {
    private String foodID;
    private String description;
    private double price;
    private String status;

    public Food() {
    }

    public Food(String foodID, String description, double price, String status) {
        this.foodID = foodID;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
