package command;

import dao.GroupDAO;
import dao.HomeworkDAO;
import dao.UserDAO;
import entity.Group;
import entity.Homework;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateHomeworkCommand implements ICommand {
    private static final String HOMEWORK = "homework";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeworkDAO homeworkDAO = new HomeworkDAO();
        UserDAO userDAO = new UserDAO();
        GroupDAO groupDAO = new GroupDAO();

        try {
            User user = userDAO.getByID(Integer.parseInt(request.getParameter("user_id")));
            Group group = groupDAO.getByID(Integer.parseInt(request.getParameter("group_id")));

            InputStream inputStream = null;
            Part file_upload = request.getPart("file_upload");
            System.out.println("reupload");
            if (file_upload.getInputStream().readAllBytes().length != 0) {
                inputStream = file_upload.getInputStream();
                System.out.println("reupload");
            }
            else {
                inputStream = homeworkDAO.getPDFByID(Integer.parseInt(request.getParameter("homework_id")));
            }

            Homework homework = new Homework(
                    Integer.parseInt(request.getParameter("homework_id")),
                    request.getParameter("name"),
                    request.getParameter("description"),
                    inputStream,
                    LocalDate.parse(request.getParameter("date")),
                    user, group
            );
            System.out.println(homework);
            homeworkDAO.update(homework);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "/mainpage";
    }
}
