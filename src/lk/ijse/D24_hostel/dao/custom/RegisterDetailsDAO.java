package lk.ijse.D24_hostel.dao.custom;

import lk.ijse.D24_hostel.dao.Supper;
import lk.ijse.D24_hostel.entity.Custom;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RegisterDetailsDAO extends Supper {
    List<Custom> loadAllStudentData(String id) throws SQLException, ClassNotFoundException, IOException;
}
