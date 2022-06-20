package dao;

import entity.Group;
import runner.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CHECKED

public class GroupDAO implements DAO<Group> {
    private static final String GROUP_ID = "kpi_group_id";
    private static final String CODENAME = "codename";
    private static final String NUMBER = "number";

    private static final String TABLE = "kpi_group";

    @Override
    public void add(Group group) throws SQLException {
        String sqlCommand = "INSERT INTO " + TABLE + " (" + CODENAME + "," + NUMBER + ") VALUES (?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setString(1, group.getCodename());
            preparedStatement.setInt(2, group.getNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Group> getAll() throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE;
        List<Group> groupList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(GROUP_ID));
                group.setCodename(resultSet.getString(CODENAME));
                group.setNumber(resultSet.getInt(NUMBER));

                groupList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupList;
    }

    @Override
    public Group getByID(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + GROUP_ID + " = ?";
        Group group = new Group();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                group.setId(resultSet.getInt(GROUP_ID));
                group.setCodename(resultSet.getString(CODENAME));
                group.setNumber(resultSet.getInt(NUMBER));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    @Override
    public void update(Group group) throws SQLException {
        String sqlCommand = "UPDATE " + TABLE + " SET " + CODENAME + "= ?, " + NUMBER + "= ?, " +
                " = ? WHERE " + GROUP_ID + "= ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){

            preparedStatement.setString(1,group.getCodename());
            preparedStatement.setInt(2,group.getNumber());
            preparedStatement.setInt(3,group.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String sql = "DELETE FROM" + TABLE + " WHERE " + GROUP_ID + " = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql); ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
