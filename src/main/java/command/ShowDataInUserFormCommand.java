package command;

import dao.GroupDAO;
import entity.Group;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ShowDataInUserFormCommand implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("id", request.getParameter("id"));
        session.setAttribute("first_name", request.getParameter("first_name"));
        session.setAttribute("last_name", request.getParameter("last_name"));
        session.setAttribute("email", request.getParameter("email"));
        session.setAttribute("password", request.getParameter("password"));
        session.setAttribute("typo", request.getParameter("typo"));
        return "/updateuser";
    }
}
