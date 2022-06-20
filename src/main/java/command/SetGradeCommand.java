package command;

import dao.SubmitDAO;
import entity.Submit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SetGradeCommand implements ICommand {
    private static final String SUBMIT_ID = "submit_id";
    private static final String GRADE = "grade";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubmitDAO submitDAO = new SubmitDAO();
        try {
            submitDAO.setGradeByID(Integer.parseInt(request.getParameter(GRADE)), Integer.parseInt(request.getParameter(SUBMIT_ID)));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "/mainpage";
    }
}
