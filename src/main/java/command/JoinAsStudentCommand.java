package command;

import dao.GroupStudentDAO;
import dao.UserDAO;
import entity.GroupStudent;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class JoinAsStudentCommand implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
        UserDAO dao = new UserDAO();

        try {
            User student = dao.getLatestEntry();
            GroupStudent groupStudent = new GroupStudent(student);
            groupStudentDAO.addAsStudent(groupStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "/mainpage";
    }

}
