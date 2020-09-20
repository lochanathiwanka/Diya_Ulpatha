package lk.diyaulpatha.dto;

public class CustomerDTO {
    private String customerID;
    private String name;
    private String nic;
    private String address;
    private String contact;
    private String gender;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerID, String name, String nic, String address, String contact, String gender) {
        this.customerID = customerID;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
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
}
