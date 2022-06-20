package dao;
import entity.Submit;
import runner.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CHECKED

public class SubmitDAO implements DAO<Submit> {
    private static final String SUBMIT_ID = "submit_id";
    private static final String PDF_HOMEWORK = "pdf_homework";
    private static final String USER_ID = "user_id";
    private static final String HOMEWORK_ID = "homework_id";
    private static final String GRADE = "grade";

    private static final String TABLE = "submit";

    UserDAO userDAO = new UserDAO();
    HomeworkDAO homeworkDAO = new HomeworkDAO();


    @Override
    public void add(Submit submit) throws SQLException {
        String sqlCommand = "INSERT INTO " + TABLE + " (" + PDF_HOMEWORK + "," + USER_ID + "," + HOMEWORK_ID + "," + GRADE + ") VALUES (?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setBinaryStream(1,submit.getPdf_homework());
            preparedStatement.setInt(2, submit.getUser_id().getId());
            preparedStatement.setInt(3, submit.getHomework_id().getId());
            preparedStatement.setInt(4, submit.getGrade());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //order ascending
    @Override
    public List<Submit> getAll() throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " ORDER BY " + HOMEWORK_ID + " ASC ";
        List<Submit> submitList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                Submit submit = new Submit();
                submit.setId(resultSet.getInt(SUBMIT_ID));
                submit.setPdf_homework(resultSet.getBinaryStream(PDF_HOMEWORK));
                submit.setUser_id(userDAO.getByID(resultSet.getInt(USER_ID)));
                submit.setHomework_id(homeworkDAO.getByID(resultSet.getInt(HOMEWORK_ID)));
                submit.setGrade(resultSet.getInt(GRADE));

                submitList.add(submit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submitList;
    }

    @Override
    public Submit getByID(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + SUBMIT_ID + " = ?";
        Submit submit = new Submit();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                submit.setId(resultSet.getInt(SUBMIT_ID));
                submit.setPdf_homework(resultSet.getBinaryStream(PDF_HOMEWORK));
                submit.setUser_id(userDAO.getByID(resultSet.getInt(USER_ID)));
                submit.setHomework_id(homeworkDAO.getByID(resultSet.getInt(HOMEWORK_ID)));
                submit.setGrade(resultSet.getInt(GRADE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submit;
    }

    @Override
    public void update(Submit submit) throws SQLException {
        String sqlCommand = "UPDATE " + TABLE + " SET " + PDF_HOMEWORK + "= ?, " + USER_ID + "= ?, " +
                HOMEWORK_ID + "= ?, " + GRADE + " = ? WHERE " + SUBMIT_ID + "= ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){
            preparedStatement.setBinaryStream(1, submit.getPdf_homework());
            preparedStatement.setInt(2,submit.getUser_id().getId());
            preparedStatement.setInt(3, submit.getHomework_id().getId());
            preparedStatement.setInt(4, submit.getGrade());
            preparedStatement.setInt(5, submit.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String sqlCommand = "DELETE FROM " + TABLE + " WHERE " + SUBMIT_ID + " = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Submit> getByHomeworkID(int id) throws SQLException {
        String sqlCommand = "SELECT * FROM " + TABLE + " WHERE " + HOMEWORK_ID + " = ?";
        List<Submit> submitList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Submit submit = new Submit();
                submit.setId(resultSet.getInt(SUBMIT_ID));
                submit.setPdf_homework(resultSet.getBinaryStream(PDF_HOMEWORK));
                submit.setUser_id(userDAO.getByID(resultSet.getInt(USER_ID)));
                submit.setHomework_id(homeworkDAO.getByID(resultSet.getInt(HOMEWORK_ID)));
                submit.setGrade(resultSet.getInt(GRADE));

                submitList.add(submit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submitList;
    }

    public List<Submit> getByUserID(int id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = ?";
        List<Submit> submitList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Submit submit = new Submit();
                submit.setId(resultSet.getInt(SUBMIT_ID));
                submit.setPdf_homework(resultSet.getBinaryStream(PDF_HOMEWORK));
                submit.setUser_id(userDAO.getByID(resultSet.getInt(USER_ID)));
                submit.setHomework_id(homeworkDAO.getByID(resultSet.getInt(HOMEWORK_ID)));
                submit.setGrade(resultSet.getInt(GRADE));

                submitList.add(submit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submitList;
    }

    public void setGradeByID(int grade, int id) throws SQLException {
        String sqlCommand = "UPDATE " + TABLE + " SET " + GRADE + " = ? WHERE " + SUBMIT_ID + " = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
            preparedStatement.setInt(1, grade);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeByHomeworkID(int id) throws SQLException {
        String sqlCommand = "DELETE FROM " + TABLE + " WHERE " + HOMEWORK_ID + " = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand); ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
