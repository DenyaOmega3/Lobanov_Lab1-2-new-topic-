package command;

import dao.GroupDAO;
import dao.HomeworkDAO;
import entity.Group;
import entity.Homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ShowDataInHomeworkFormCommand implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("homework_id", request.getParameter("homework_id"));
        session.setAttribute("name", request.getParameter("name"));
        session.setAttribute("description", request.getParameter("description"));
        session.setAttribute("pdf_file", request.getParameter("pdf_file"));
        session.setAttribute("deadline", request.getParameter("deadline"));
        session.setAttribute("user_id", request.getParameter("user_id"));
        session.setAttribute("group_id", request.getParameter("group_id"));

        GroupDAO groupDAO = new GroupDAO();
        try {
            Group chosenGroup = groupDAO.getByID(Integer.parseInt(request.getParameter("group_id")));
            session.setAttribute("groups", groupDAO.getAll());
            session.setAttribute("group_codename", chosenGroup.getCodename());
            session.setAttribute("group_number", chosenGroup.getNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "/updatehomework";
    }
}
