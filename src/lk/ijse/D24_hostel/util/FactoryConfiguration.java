package lk.ijse.D24_hostel.util;

import lk.ijse.D24_hostel.entity.Reservation;
import lk.ijse.D24_hostel.entity.Room;
import lk.ijse.D24_hostel.entity.Student;
import lk.ijse.D24_hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();

        Properties p = new Properties();

        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.Properties"));
        configuration.setProperties(p);

        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(Reservation.class);
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
