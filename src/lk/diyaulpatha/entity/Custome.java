package lk.diyaulpatha.entity;

public class Custome {
    private String customerID;
    private String name;
    private String nic;
    private String address;
    private String contact;
    private String gender;

    private String bookingID;
    private String date;
    private String time;
    private String payment;

    private String roomID;
    private String code;
    private String description;
    private double price;
    private String available;
    private String startDate;
    private String endDate;
    private String endTime;
    private String clearedDate;
    private double totAmount;

    public Custome() {
    }

    public Custome(String available, String endTime, String clearedDate) {
        this.available = available;
        this.endTime = endTime;
        this.clearedDate = clearedDate;
    }

    public Custome(String code, String startDate, String endDate, String endTime, double totAmount) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
        this.totAmount = totAmount;
    }

    public Custome(String roomID, String code, String description, double price, String available, String endTime) {
        this.roomID = roomID;
        this.code = code;
        this.description = description;
        this.price = price;
        this.available = available;
        this.endTime = endTime;
    }

    public Custome(String customerID, String name, String nic, String address, String contact, String gender, String bookingID, String date, String time, String payment, String startDate, String endDate, String roomID, String code, String description, double totAmount) {
        this.customerID = customerID;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.bookingID = bookingID;
        this.date = date;
        this.time = time;
        this.payment = payment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomID = roomID;
        this.code = code;
        this.description = description;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClearedDate() {
        return clearedDate;
    }

    public void setClearedDate(String clearedDate) {
        this.clearedDate = clearedDate;
    }
}
