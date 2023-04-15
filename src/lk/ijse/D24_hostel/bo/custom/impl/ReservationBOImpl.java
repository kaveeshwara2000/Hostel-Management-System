package lk.ijse.D24_hostel.bo.custom.impl;

import lk.ijse.D24_hostel.bo.custom.ReservationBO;
import lk.ijse.D24_hostel.dao.DAOFactory;
import lk.ijse.D24_hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.D24_hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.D24_hostel.dto.ReservationDTO;
import lk.ijse.D24_hostel.entity.Reservation;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    RoomDAOImpl roomDAO = (RoomDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    ReservationDAOImpl reservationDAO = (ReservationDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public List setStudentIDs() throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.setRoomIDs();
    }

    @Override
    public List setRoomIDs() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.setRoomIDs();
    }

    @Override
    public Student setStudentsData(String id) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.find(id);
    }

    @Override
    public Room setRoomsData(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.find(id);
    }


    @Override
    public String generateNewRegisterId() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.generateNewId();
    }

    @Override
    public boolean registerStudent(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.add(new Reservation(
                reservationDTO.getRegisterID(),
                reservationDTO.getDate(),
                new Student(reservationDTO.getStudentID()),
                new Room(reservationDTO.getRoomID()),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public List<ReservationDTO> reservedRoomByTD(String id) throws SQLException, ClassNotFoundException, IOException {
            List<Reservation> reserves = reservationDAO.searchReservedRoomById(id);

            List<ReservationDTO> reserveDTOS=new ArrayList<>();

            for (Reservation reserve : reserves) {
                reserveDTOS.add(new ReservationDTO(
                        reserve.getRegisterID(),
                        reserve.getDate(),
                        reserve.getStudent().getStudentId(),
                        reserve.getRoom().getRoomId(),
                        reserve.getStatus()
                ));

            }
            return reserveDTOS;
        }

    @Override
    public String lastStudentID() throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.lastStudentID();
    }

}
