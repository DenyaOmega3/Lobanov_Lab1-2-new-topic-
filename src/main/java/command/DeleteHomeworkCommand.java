package command;

import dao.HomeworkDAO;
import dao.SubmitDAO;
import entity.Submit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteHomeworkCommand implements ICommand {
    private static final String HOMEWORK_ID = "id";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HomeworkDAO homeworkDAO = new HomeworkDAO();
        SubmitDAO submitDAO = new SubmitDAO();
        try {
            submitDAO.removeByHomeworkID(Integer.parseInt(request.getParameter(HOMEWORK_ID)));
            homeworkDAO.remove(Integer.parseInt(request.getParameter(HOMEWORK_ID)));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "/mainpage";
    }
}
