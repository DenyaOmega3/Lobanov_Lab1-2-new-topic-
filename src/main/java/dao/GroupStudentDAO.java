package dao;

import entity.GroupStudent;
import entity.Homework;
import runner.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CHECKED

public class GroupStudentDAO implements DAO<GroupStudent> {
    private final static String USER_ID = "user_id";
    private final static String GROUP_ID = "kpi_group_id";

    private final static String TABLE = "student_in_group";

    UserDAO userDAO = new UserDAO();
    GroupDAO groupDAO = new GroupDAO();

    @Override
    public void add(GroupStudent student) throws SQLException {
    }

    public void addAsStudent(GroupStudent student) throws SQLException {
        String sqlCommand = "INSERT INTO " + TABLE + " (" + USER_ID + ") VALUES (?)";

        try (Connection connection = DBUtil.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, student.getStudent().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GroupStudent> getAll() throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE;
        List<GroupStudent> groupStudentList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                GroupStudent groupStudent = new GroupStudent();
                groupStudent.setStudent(userDAO.getByID(resultSet.getInt(USER_ID)));
                groupStudent.setGroup(groupDAO.getByID(resultSet.getInt(GROUP_ID)));

                groupStudentList.add(groupStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupStudentList;
    }

    @Override
    public GroupStudent getByID(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " =?";
        GroupStudent groupStudent = new GroupStudent();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groupStudent.setStudent(userDAO.getByID(resultSet.getInt(USER_ID)));
                groupStudent.setGroup(groupDAO.getByID(resultSet.getInt(GROUP_ID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupStudent;
    }

    @Override
    public void update(GroupStudent student) throws SQLException {
        return;
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

    public List<GroupStudent> getStudentsWithoutGroup() throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + GROUP_ID + " is NULL";
        List<GroupStudent> groupStudentList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                GroupStudent groupStudent = new GroupStudent();
                groupStudent.setStudent(userDAO.getByID((resultSet.getInt(USER_ID))));
                groupStudent.setGroup(groupDAO.getByID((resultSet.getInt(GROUP_ID))));

                groupStudentList.add(groupStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupStudentList;
    }

    public void updateGroupById(int student_id, int group_id) throws SQLException {
        String sqlCommand = "UPDATE " + TABLE + " SET " + GROUP_ID + " = ? WHERE " + USER_ID + " =?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){
            preparedStatement.setInt(1, group_id);
            preparedStatement.setInt(2, student_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
