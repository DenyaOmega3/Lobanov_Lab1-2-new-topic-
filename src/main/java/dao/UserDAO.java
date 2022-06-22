package dao;

import entity.User;
import runner.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CHECKED

public class UserDAO implements DAO<User> {
    private static final String USER_ID = "user_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TYPO = "typo";

    private static final String TABLE = "user";

    @Override
    public void add(User user) throws SQLException {
        String sqlCommand = "INSERT INTO " + TABLE + " (" + FIRST_NAME + "," + LAST_NAME + "," + EMAIL + "," + PASSWORD + "," + TYPO + ") VALUES (?,?,?,?,?)";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getTypo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE;
        List<User> userList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(USER_ID));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setTypo(resultSet.getString(TYPO));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getByID(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ?";
        User user = new User();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(USER_ID));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setTypo(resultSet.getString(TYPO));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        String sqlCommand = "UPDATE " + TABLE + " SET " + FIRST_NAME + "= ?, " + LAST_NAME + "= ?, " +
                EMAIL + "= ?, " + PASSWORD + "= ?, " + TYPO + "= ? WHERE " + USER_ID + "= ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getTypo());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String sqlCommand = "DELETE FROM " + TABLE + " WHERE " + USER_ID + " = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getByEMail(String email) throws SQLException {
        String sqlCommand = "SELECT *FROM " + TABLE + " WHERE " + EMAIL + " = ? ";
        User user = null;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(USER_ID));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setTypo(resultSet.getString(TYPO));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getLatestEntry() throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = (SELECT max(" + USER_ID + ") FROM " + TABLE + " )";
        User user = new User();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(USER_ID));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setTypo(resultSet.getString(TYPO));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
