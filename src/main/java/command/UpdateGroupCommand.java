package command;

import dao.GroupStudentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateGroupCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupStudentDAO groupStudentDAO = new GroupStudentDAO();

        try {
            groupStudentDAO.updateGroupById(Integer.parseInt(request.getParameter("user_id")), Integer.parseInt(request.getParameter("group")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "/mainpage";
    }
}
