package lk.ijse.D24_hostel.bo.custom;
import lk.ijse.D24_hostel.bo.SuperBO;
import lk.ijse.D24_hostel.entity.Custom;
import lk.ijse.D24_hostel.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RegisterDetailsBO extends SuperBO {
    List loadRoomIDs() throws SQLException, ClassNotFoundException, IOException;
    Room setRoomData(String id) throws SQLException, ClassNotFoundException, IOException;
    List<Custom> loadAllStudentData(String id) throws SQLException, ClassNotFoundException, IOException;

}
