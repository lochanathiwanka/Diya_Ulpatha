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

    private String employeeID;
    private String employeeName;
    private String employeeNic;
    private String employeeAddress;
    private String employeeDob;
    private String employeeContact;
    private String employeeEmail;
    private String employeeGender;
    private String employeeRole;

    private String userID;
    private String userName;
    private String password;


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

    public Custome(String bookingID, String date, String time, String payment, String startDate, String endDate, String roomID, String code, String description, double totAmount) {
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

    public Custome(String employeeName, String employeeNic, String employeeAddress, String employeeDob, String employeeContact, String employeeEmail, String employeeGender, String employeeRole, String userName, String password) {
        this.employeeName = employeeName;
        this.employeeNic = employeeNic;
        this.employeeAddress = employeeAddress;
        this.employeeDob = employeeDob;
        this.employeeContact = employeeContact;
        this.employeeEmail = employeeEmail;
        this.employeeGender = employeeGender;
        this.employeeRole = employeeRole;
        this.userName = userName;
        this.password = password;
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

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNic() {
        return employeeNic;
    }

    public void setEmployeeNic(String employeeNic) {
        this.employeeNic = employeeNic;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(String employeeDob) {
        this.employeeDob = employeeDob;
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
