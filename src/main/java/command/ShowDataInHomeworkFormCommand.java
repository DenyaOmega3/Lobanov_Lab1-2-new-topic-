package command;

import dao.GroupDAO;
import dao.HomeworkDAO;
import entity.Homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ShowDataInHomeworkFormCommand implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeworkDAO homeworkDAO = new HomeworkDAO();
        GroupDAO groupDAO = new GroupDAO();
        System.out.println(Integer.parseInt(request.getParameter("homework")));
        try {
            Homework homework = homeworkDAO.getByID(Integer.parseInt(request.getParameter("homework")));
            System.out.println(homework);
            HttpSession session = request.getSession();
            session.setAttribute("name", homework.getName());
            session.setAttribute("description", homework.getDescription());
            session.setAttribute("deadline", homework.getDeadline());
            session.setAttribute("group_codename", homework.getGroupID().getCodename());
            session.setAttribute("group_number", homework.getGroupID().getNumber());
            session.setAttribute("groups", groupDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "/updatehomework";
    }
}
