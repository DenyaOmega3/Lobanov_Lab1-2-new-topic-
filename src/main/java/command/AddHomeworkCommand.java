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
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddHomeworkCommand implements ICommand {
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "date";
    private static final String FILE_UPLOAD = "file_upload";
    private static final String USER_ID = "user_id";
    private static final String GROUP_ID = "group_id";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Part file_upload = request.getPart(FILE_UPLOAD);
            InputStream inputStream = file_upload.getInputStream();
            UserDAO userDAO = new UserDAO();
            GroupDAO groupDAO = new GroupDAO();
            User user = userDAO.getByID(Integer.parseInt(request.getParameter(USER_ID)));
            Group group = groupDAO.getByID(Integer.parseInt(request.getParameter(GROUP_ID)));
            System.out.println(user);

            Homework homework = new Homework(request.getParameter(NAME),request.getParameter(DESCRIPTION),
                    inputStream,LocalDate.parse(request.getParameter(DATE)),user, group);
            System.out.println(homework);
            HomeworkDAO homeworkDAO = new HomeworkDAO();
            homeworkDAO.add(homework);
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return "/mainpage";
    }
}
