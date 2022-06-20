package command;

import dao.GroupStudentDAO;
import dao.HomeworkDAO;
import dao.SubmitDAO;
import entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InitializeCommand implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HomeworkDAO homeworkDAO = new HomeworkDAO();

        GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
        SubmitDAO submitDAO = new SubmitDAO();

        try {
            HttpSession session = request.getSession();
            User teacher = (User)session.getAttribute("user");
            if (teacher == null) {
                System.out.println("??????");
                return "";
            }

            if (teacher.getTypo().equals("teacher")) {
                List<Homework> homeworkListFromTeacher = homeworkDAO.getFromTeacher(teacher.getId());
                request.setAttribute("homeworkList", homeworkListFromTeacher);


                //Take all submits with corresponding homework_id
                List<Submit> submitList = new ArrayList<>();
                for (Homework hw : homeworkListFromTeacher) {
                    List<Submit> fetchedSubmit = submitDAO.getByHomeworkID(hw.getId());
                    for (Submit submit : fetchedSubmit) {
                        System.out.println(submit);
                        submitList.add(submit);
                    }
                }
                request.setAttribute("submitList", submitList);

            }
            else if (teacher.getTypo().equals("student")){
                GroupStudent student = groupStudentDAO.getByID(teacher.getId());

                List<Homework> groupHomeworkList = homeworkDAO.getByGroupID(student.getGroup().getId());
                List<Submit> submitList = submitDAO.getByUserID(teacher.getId());
                for (Submit s : submitList) {
                    System.out.println(s);
                }
                request.setAttribute("groupHomeworkList", groupHomeworkList);
                request.setAttribute("submitList", submitList);
            }
            else {
                System.out.println("???");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
}
