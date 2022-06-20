package command;

import dao.GroupDAO;
import dao.GroupStudentDAO;
import entity.Group;
import entity.GroupStudent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetAllStudentWithoutGroupsCommand implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
        GroupDAO groupDAO = new GroupDAO();

        try {
            List<GroupStudent> groupStudentList = groupStudentDAO.getStudentsWithoutGroup();
            List<Group> groupList = groupDAO.getAll();
            System.out.println(groupStudentList);
            request.setAttribute("studentsWithoutGroup", groupStudentList);
            request.setAttribute("groups", groupList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
}
