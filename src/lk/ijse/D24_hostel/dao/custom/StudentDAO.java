package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.SuperDAO;
import lk.ijse.D24_hostel.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends SuperDAO<Student,String> {
    List setRoomIDs() throws SQLException, ClassNotFoundException, IOException;

    String lastStudentID()throws IOException;
}
