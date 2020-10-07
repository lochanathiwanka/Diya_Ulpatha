package lk.diyaulpatha.entity;

public class User {
    private String userID;
    private String employeeID;
    private String userName;
    private String password;

    public User() {
    }

    public User(String userID, String employeeID, String userName, String password) {
        this.userID = userID;
        this.employeeID = employeeID;
        this.userName = userName;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
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
