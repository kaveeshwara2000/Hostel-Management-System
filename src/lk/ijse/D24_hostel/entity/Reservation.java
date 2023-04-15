package lk.ijse.D24_hostel.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/*@AllArgsConstructor
@NoArgsConstructor
@Data*/
@Entity
public class Reservation {
    @Id
    private String registerID;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private lk.ijse.D24_hostel.entity.Room room;

    private String status;

    public Reservation(String registerID, LocalDate date, Student student, lk.ijse.D24_hostel.entity.Room room, String status) {
        this.registerID = registerID;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public Reservation() {

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public lk.ijse.D24_hostel.entity.Room getRoom() {
        return room;
    }

    public void setRoom(lk.ijse.D24_hostel.entity.Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "registerID='" + registerID + '\'' +
                ", date=" + date +
                ", student=" + student +
                ", room=" + room +
                ", status='" + status + '\'' +
                '}';
    }
}
