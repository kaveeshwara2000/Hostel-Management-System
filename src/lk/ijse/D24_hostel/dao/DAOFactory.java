package lk.ijse.D24_hostel.dao;

import lk.ijse.D24_hostel.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    //singleton
    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    //public final static integer values
    public enum DAOTypes {
        STUDENT,ROOM,RESERVATION,REGISTERDETAILS,USER
    }

    //method for hide the object creation logic and return what client wants
    public lk.ijse.D24_hostel.dao.Supper getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case REGISTERDETAILS:
                return new RegisterDetailsDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }

}