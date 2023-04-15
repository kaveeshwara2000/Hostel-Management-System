package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.bo.SuperBO;
import lk.ijse.D24_hostel.dto.ReservationDTO;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {
    List setStudentIDs() throws SQLException, ClassNotFoundException, IOException;

    List setRoomIDs() throws SQLException, ClassNotFoundException, IOException;

    Student setStudentsData(String id) throws SQLException, ClassNotFoundException, IOException;

    Room setRoomsData(String id) throws SQLException, ClassNotFoundException, IOException;

    String generateNewRegisterId() throws SQLException, ClassNotFoundException, IOException;

    boolean registerStudent(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException, IOException;

    List<ReservationDTO> reservedRoomByTD(String id) throws SQLException, ClassNotFoundException, IOException;

    String lastStudentID()throws SQLException, ClassNotFoundException, IOException;
}
