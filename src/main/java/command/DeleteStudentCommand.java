package command;

import dao.GroupStudentDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteStudentCommand implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("here");

        GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
        UserDAO userDAO = new UserDAO();

        try {
            groupStudentDAO.remove(id);
            userDAO.remove(id);
            System.out.println("here");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("here");
        return "/mainpage";
    }
}
