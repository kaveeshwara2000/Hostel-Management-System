package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.RoomBO;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24_hostel.dto.RoomDTO;
import lk.ijse.D24_hostel.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RoomBOImpl implements RoomBO {

    RoomDAOImpl roomDAO = (RoomDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean add(RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.add(
                new Room(
                        roomDTO.getRoomId(),
                        roomDTO.getType(),
                        roomDTO.getKeyMoney(),
                        roomDTO.getQty()
                ));
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.update(
                new Room(
                        roomDTO.getRoomId(),
                        roomDTO.getType(),
                        roomDTO.getKeyMoney(),
                        roomDTO.getQty()
                ));
    }

    @Override
    public boolean delete(String id) throws IOException {
        return roomDAO.delete(id);
    }

    @Override
    public List<RoomDTO> loadAllRooms() throws SQLException, ClassNotFoundException, IOException {
        List<Room> rooms = roomDAO.loadAll();
        ArrayList<RoomDTO> arrayList = new ArrayList<>();
        for (Room room : rooms) {
           arrayList.add(new RoomDTO(
                 room.getRoomId(),
                 room.getType(),
                 room.getKeyMoney(),
                 room.getQty()
           ));
        }
        return arrayList;
    }

    @Override
    public Room getRoom(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.find(id);
    }
}
