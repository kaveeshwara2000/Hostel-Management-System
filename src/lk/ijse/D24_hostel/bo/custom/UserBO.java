package lk.ijse.D24_hostel.bo.custom;

import lk.ijse.D24_hostel.bo.SuperBO;
import lk.ijse.D24_hostel.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException, IOException;

    List<UserDTO> loadAllUsers() throws SQLException, ClassNotFoundException, IOException;
}
