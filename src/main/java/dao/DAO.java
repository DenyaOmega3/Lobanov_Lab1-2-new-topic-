package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DAO<Entity> {
    //create
    void add(Entity user) throws SQLException, IOException;

    //read
    List<Entity> getAll() throws SQLException, IOException;
    Entity getByID(int id) throws SQLException;

    //update
    void update(Entity user) throws SQLException;

    //delete
    void remove(int id) throws SQLException;
}
