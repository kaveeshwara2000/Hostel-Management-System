package lk.ijse.D24_hostel.dto;

import java.time.LocalDate;

public class ReservationDTO {
    private String registerID;
    private LocalDate date;
    private String studentID;
    private String roomID;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(String registerID, LocalDate date, String studentID, String roomID, String status) {
        this.registerID = registerID;
        this.date = date;
        this.studentID = studentID;
        this.roomID = roomID;
        this.status = status;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "registerID='" + registerID + '\'' +
                ", date=" + date +
                ", studentID='" + studentID + '\'' +
                ", roomID='" + roomID + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
