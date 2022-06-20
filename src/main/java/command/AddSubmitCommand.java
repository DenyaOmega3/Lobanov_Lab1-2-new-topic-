package command;

import dao.HomeworkDAO;
import dao.SubmitDAO;
import entity.Homework;
import entity.Submit;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class AddSubmitCommand implements ICommand {
    private static final String FILE_UPLOAD = "file_upload";
    private static final String HOMEWORK = "homework";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file_upload = request.getPart(FILE_UPLOAD);
        InputStream inputStream = file_upload.getInputStream();

        HttpSession session = request.getSession();
        User user_id = (User)session.getAttribute("user");
        int homework_id = Integer.parseInt(request.getParameter(HOMEWORK));
        System.out.println(homework_id);

        SubmitDAO submitDAO = new SubmitDAO();
        HomeworkDAO homeworkDAO = new HomeworkDAO();

        try {
            Submit submit = new Submit(inputStream,user_id,homeworkDAO.getByID(homework_id),-1);
            System.out.println(submit);
            submitDAO.add(submit);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        return "/mainpage";
    }
}
