package command;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("first_name"));
        System.out.println(request.getParameter("last_name"));
        System.out.println(request.getParameter("email"));
        System.out.println(request.getParameter("password"));
        System.out.println("typo");
        User user = new User(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("first_name"),
                request.getParameter("last_name"),
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("typo")
        );
        System.out.println(user);
        try {
            userDAO.update(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return "/mainpage";
    }
}
