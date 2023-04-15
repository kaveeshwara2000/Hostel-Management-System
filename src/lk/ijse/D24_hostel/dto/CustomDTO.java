package lk.ijse.D24_hostel.dto;

import java.time.LocalDate;

public class CustomDTO {
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;

    private String roomId;
    private String type;
    private double keyMoney;
    private int qty;

    private String registerID;
    private LocalDate date;
    private String status;

    public CustomDTO() {
    }

    public CustomDTO(String studentId, String name, String address, String contactNo, LocalDate dob, String gender, String roomId, String type, double keyMoney, int qty, String registerID, LocalDate date, String status) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
        this.roomId = roomId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
        this.registerID = registerID;
        this.date = date;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney=" + keyMoney +
                ", qty=" + qty +
                ", registerID='" + registerID + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
