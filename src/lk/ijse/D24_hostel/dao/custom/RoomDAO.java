package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.SuperDAO;
import lk.ijse.D24_hostel.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RoomDAO extends SuperDAO<Room,String> {
    List setRoomIDs() throws SQLException, ClassNotFoundException, IOException;
}
