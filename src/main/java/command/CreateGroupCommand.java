package command;

import dao.GroupDAO;
import entity.Group;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CreateGroupCommand implements ICommand {
    private static final String GROUP_NUMBER = "group_number";
    private static final String GROUP_CODE = "group_code";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Group group = new Group(
                request.getParameter(GROUP_CODE),
                Integer.parseInt(request.getParameter(GROUP_NUMBER))
        );

        System.out.println(group);

        GroupDAO groupDAO = new GroupDAO();
        try {
            groupDAO.add(group);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return "/mainpage";
    }
}
