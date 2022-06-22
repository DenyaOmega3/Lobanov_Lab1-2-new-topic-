package controller;

import command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

//TODO: add enum for commands.put

public class ControllerHelper {
    private static final String COMMAND = "command";
    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private ControllerHelper() {
        commands.put("signIn", new SignInCommand());
        commands.put("addHomework", new AddHomeworkCommand());
        commands.put("init", new InitializeCommand());
        commands.put("openPDF", new OpenPDF());
        commands.put("deleteHomework", new DeleteHomeworkCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("createGroup", new CreateGroupCommand());
        commands.put("userAsStudent", new JoinAsStudentCommand());
        commands.put("studentWithoutGroup", new GetAllStudentWithoutGroupsCommand());
        commands.put("addStudents", new AddStudentsToGroupCommand());
        commands.put("addSubmit", new AddSubmitCommand());
        commands.put("setGrade", new SetGradeCommand());
        commands.put("signOut", new SignOutCommand());
        commands.put("updateHomework", new UpdateHomeworkCommand());
        commands.put("showDataInHomeworkForm", new ShowDataInHomeworkFormCommand());
        commands.put("deleteStudent", new DeleteStudentCommand());
        commands.put("showDataInUserForm", new ShowDataInUserFormCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("updateGroup", new UpdateGroupCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter(COMMAND));
        System.out.println(request.getParameter(COMMAND));
        if (command == null) {
            System.out.println(":(");
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }


}
