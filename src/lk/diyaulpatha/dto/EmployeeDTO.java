package lk.diyaulpatha.dto;

public class EmployeeDTO {
    private String employeeID;
    private String employeeName;
    private String nic;
    private String address;
    private String dob;
    private String contact;
    private String email;
    private String gender;
    private String role;
    private String status;
    private UserDTO userDetail;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeName, String nic, String address, String dob, String contact, String email, String gender, String role) {
        this.employeeName = employeeName;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    public EmployeeDTO(String employeeID, String employeeName, String nic, String address, String dob, String contact, String email, String gender, String role, String status) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.status = status;
    }

    public EmployeeDTO(String employeeName, String nic, String address, String dob, String contact, String email, String gender, String role, String status, UserDTO userDetail) {
        this.employeeName = employeeName;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.userDetail = userDetail;
    }

    public EmployeeDTO(String employeeID, String employeeName, String nic, String address, String dob, String contact, String email, String gender, String role, String status, UserDTO userDetail) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.userDetail = userDetail;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDTO userDetail) {
        this.userDetail = userDetail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
