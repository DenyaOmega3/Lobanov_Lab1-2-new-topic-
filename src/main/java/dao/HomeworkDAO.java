package dao;

import entity.Homework;
import runner.DBUtil;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CHECKED

public class HomeworkDAO implements DAO<Homework> {
    private static final String HOMEWORK_ID = "homework_id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PDF_FILE = "pdf_file";
    private static final String DEADLINE = "deadline";
    private static final String USER_ID = "user_id";
    private static final String GROUP_ID = "kpi_group_id";

    private static final String TABLE = "homework";

    UserDAO userDAO = new UserDAO();
    GroupDAO groupDAO = new GroupDAO();

    @Override
    public void add(Homework homework) throws SQLException {
        String sqlCommand = "INSERT INTO " + TABLE + " (" + NAME + "," + DESCRIPTION + "," + PDF_FILE + "," +
                DEADLINE + "," + USER_ID + "," + GROUP_ID + ") VALUES (?,?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setString(1, homework.getName());
            preparedStatement.setString(2, homework.getDescription());
            preparedStatement.setBinaryStream(3,homework.getPdfFile());
            preparedStatement.setDate(4,java.sql.Date.valueOf(homework.getDeadline()));
            preparedStatement.setInt(5, homework.getUserID().getId());
            preparedStatement.setInt(6, homework.getGroupID().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Homework> getAll() throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE;
        List<Homework> homeworkList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                Homework homework = new Homework();
                homework.setId(resultSet.getInt(HOMEWORK_ID));
                homework.setName(resultSet.getString(NAME));
                homework.setDescription(resultSet.getString(DESCRIPTION));
                homework.setPdfFile(resultSet.getBinaryStream(PDF_FILE));
                homework.setDeadline(resultSet.getDate(DEADLINE).toLocalDate());
                homework.setUserID(userDAO.getByID(resultSet.getInt(USER_ID)));
                homework.setGroupID(groupDAO.getByID(resultSet.getInt(GROUP_ID)));

                homeworkList.add(homework);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeworkList;
    }

    @Override
    public Homework getByID(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + HOMEWORK_ID + " = ?";
        Homework homework = new Homework();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                homework.setId(resultSet.getInt(HOMEWORK_ID));
                homework.setName(resultSet.getString(NAME));
                homework.setDescription(resultSet.getString(DESCRIPTION));
                homework.setPdfFile(resultSet.getBinaryStream(PDF_FILE));
                homework.setDeadline(resultSet.getDate(DEADLINE).toLocalDate());
                homework.setUserID(userDAO.getByID(resultSet.getInt(USER_ID)));
                homework.setGroupID(groupDAO.getByID(resultSet.getInt(GROUP_ID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homework;
    }

    @Override
    public void update(Homework homework) throws SQLException {
        String sqlCommand = "UPDATE " + TABLE + " SET " + NAME + "= ?, " + DESCRIPTION + "= ?, " +
                PDF_FILE + "= ?, " + DEADLINE + "= ?, " + GROUP_ID + "= ? WHERE " + HOMEWORK_ID + "= ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){
            preparedStatement.setString(1, homework.getName());
            preparedStatement.setString(2, homework.getDescription());
            preparedStatement.setBinaryStream(3, homework.getPdfFile());
            preparedStatement.setDate(4, java.sql.Date.valueOf(homework.getDeadline()));
            preparedStatement.setInt(5, homework.getGroupID().getId());
            preparedStatement.setInt(6, homework.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String sql = "DELETE FROM " + TABLE + " WHERE " + HOMEWORK_ID + " = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql); ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Homework> getFromTeacher(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ?";
        List<Homework> homeworkList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Homework homework = new Homework();
                homework.setId(resultSet.getInt(HOMEWORK_ID));
                homework.setName(resultSet.getString(NAME));
                homework.setDescription(resultSet.getString(DESCRIPTION));
                homework.setPdfFile(resultSet.getBinaryStream(PDF_FILE));
                homework.setDeadline(resultSet.getDate(DEADLINE).toLocalDate());
                homework.setUserID(userDAO.getByID(resultSet.getInt(USER_ID)));
                homework.setGroupID(groupDAO.getByID(resultSet.getInt(GROUP_ID)));

                homeworkList.add(homework);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeworkList;
    }

    public List<Homework> getByGroupID(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + GROUP_ID + " = ?";
        List<Homework> homeworkList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Homework homework = new Homework();
                homework.setId(resultSet.getInt(HOMEWORK_ID));
                homework.setName(resultSet.getString(NAME));
                homework.setDescription(resultSet.getString(DESCRIPTION));
                homework.setPdfFile(resultSet.getBinaryStream(PDF_FILE));
                homework.setDeadline(resultSet.getDate(DEADLINE).toLocalDate());
                homework.setUserID(userDAO.getByID(resultSet.getInt(USER_ID)));
                homework.setGroupID(groupDAO.getByID(resultSet.getInt(GROUP_ID)));

                homeworkList.add(homework);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeworkList;
    }

    public InputStream getPDFByID(int id) throws SQLException {
        String sqlCommand = "SELECT " + PDF_FILE + " FROM " + TABLE + " WHERE " + HOMEWORK_ID + " = ?";
        InputStream inputStream = null;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                inputStream = resultSet.getBinaryStream(PDF_FILE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
