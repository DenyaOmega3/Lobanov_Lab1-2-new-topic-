package servlet;

import command.ICommand;
import controller.ControllerHelper;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "BaseServler", value = "/BaseServlet")
@MultipartConfig
public class BaseServlet extends HttpServlet {
    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ICommand command = controllerHelper.getCommand(request);
        try {
            String page = command.execute(request, response);
            if (!page.equals("")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                response.sendRedirect(page);
            }

        }
        catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ICommand command = controllerHelper.getCommand(request);
        try {
            String page = command.execute(request, response);
            if (!page.equals("")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                response.sendRedirect(page);
            }
        }
        catch (ServletException e) {
            e.printStackTrace();
        }
    }
}