package command;

import dao.HomeworkDAO;
import entity.Homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.sql.SQLException;

public class UpdateHomeworkCommand implements ICommand {
    private static final String HOMEWORK = "homework";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeworkDAO homeworkDAO = new HomeworkDAO();


        return "/mainpage";
    }
}
