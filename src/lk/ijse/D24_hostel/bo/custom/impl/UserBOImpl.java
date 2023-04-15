package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.UserBO;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.custom.impl.UserDAOImpl;
import lk.ijse.D24_hostel.dto.UserDTO;
import lk.ijse.D24_hostel.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.update(new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword()
        ));
    }

    @Override
    public List<UserDTO> loadAllUsers() throws SQLException, ClassNotFoundException, IOException {
        List<User> users = userDAO.loadAll();
        ArrayList<UserDTO> arrayList = new ArrayList<>();
        for (User user : users) {
            arrayList.add(new UserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword()
            ));
        }
        return arrayList;
    }

}
