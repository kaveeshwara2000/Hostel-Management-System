package lk.ijse.D24_hostel.view.tm;

import java.time.LocalDate;

public class RegisterDetailsTM {
    private String registerID;
    private LocalDate date;
    private String studentId;
    private String name;
    private String contactNo;

    public RegisterDetailsTM() {
    }

    public RegisterDetailsTM(String registerID, LocalDate date, String studentId, String name, String contactNo) {
        this.registerID = registerID;
        this.date = date;
        this.studentId = studentId;
        this.name = name;
        this.contactNo = contactNo;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "RegisterDetailsTM{" +
                "registerID='" + registerID + '\'' +
                ", date=" + date +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
