package command;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SignInCommand implements ICommand {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        UserDAO userDAO = new UserDAO();
        try {
            User user = userDAO.getByEMail(email);
            if (user == null) {
                return "/signin?user_not_found=true";
            }
            if (!user.getPassword().equals(password)) {
                return "/signin?wrong_password=true";
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60*60);
            return "/mainpage";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
