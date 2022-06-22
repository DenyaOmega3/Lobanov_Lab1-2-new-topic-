package command;

import dao.GroupStudentDAO;
import dao.UserDAO;
import entity.Group;
import entity.GroupStudent;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpCommand implements ICommand {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TYPO = "typo";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String typo = request.getParameter(TYPO);
        if (!isMailValid(email)) {
            return "/signup?not_valid=true";
        }

        User newUser = new User(firstName,lastName,email,password,typo);
        UserDAO userDAO = new UserDAO();

        try {
            User checkIfExists = userDAO.getByEMail(email);
            System.out.println(checkIfExists);
            if (checkIfExists != null) {
                return "/signup?account_exists=true";
            }
            userDAO.add(newUser);
            System.out.println(typo);
            if (typo.equals("student")) {
                return "/base?command=userAsStudent";
            }

            HttpSession session = request.getSession();

            System.out.println(isMailValid(email));

            return "/mainpage";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isMailValid(String input) {
        String emailReget = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailReget,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(input);
        return matcher.find();
    }
}
