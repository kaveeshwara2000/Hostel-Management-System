package lk.ijse.D24_hostel.dao;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SuperDAO<Entity,ID> extends Supper{

    public boolean add(Entity entity) throws SQLException, ClassNotFoundException, IOException;

    public boolean update(Entity entity) throws SQLException, ClassNotFoundException, IOException;

    public boolean delete(ID id) throws SQLException, ClassNotFoundException, IOException;

    public Entity find(ID id) throws SQLException, ClassNotFoundException, IOException;

    public List<Entity> loadAll() throws SQLException, ClassNotFoundException, IOException;
}
