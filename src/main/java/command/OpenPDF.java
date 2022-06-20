package command;

import dao.HomeworkDAO;
import dao.SubmitDAO;
import entity.Homework;
import entity.Submit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class OpenPDF implements ICommand {
    private static final String FILE_UPLOAD = "file_upload";
    private static final String HOMEWORK_ID = "id";


    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (request.getParameter("from").equals("submit")) {
                System.out.println("good");
                SubmitDAO submitDAO = new SubmitDAO();
                Submit submit = submitDAO.getByID(Integer.parseInt(request.getParameter(HOMEWORK_ID)));
                System.out.println("good");
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline");
                System.out.println(submit);
                byte[] filedata = submit.getPdf_homework().readAllBytes();
                System.out.println("good");
                response.setContentLength(filedata.length);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(filedata);
                outputStream.flush();
            }
            else {
                System.out.println("Hey");
                HomeworkDAO homeworkDAO = new HomeworkDAO();
                Homework homework = homeworkDAO.getByID(Integer.parseInt(request.getParameter(HOMEWORK_ID)));
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline");
                byte[] filedata = homework.getPdfFile().readAllBytes();
                response.setContentLength(filedata.length);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(filedata);
                outputStream.flush();
            }
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
