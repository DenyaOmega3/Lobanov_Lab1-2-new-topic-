package command;

import dao.GroupStudentDAO;
import entity.GroupStudent;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AddStudentsToGroupCommand implements ICommand {
    private static final String STUDENTS = "student";
    private static final String GROUP = "group";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] students = request.getParameterValues(STUDENTS);
        String group = request.getParameter(GROUP);
        System.out.println(group);

        GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
        try {
            for (String student : students) {
                groupStudentDAO.updateGroupById(Integer.parseInt(student), Integer.parseInt(group));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "/mainpage";
    }
}
