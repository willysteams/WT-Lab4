package by.bsuir.task.controller.command.common;

import by.bsuir.task.controller.command.Command;
import by.bsuir.task.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

    private static final String LOGIN_PAGE = "/WEB-INF/pages/login.jsp";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String ROLE = "role";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(ID);
        session.removeAttribute(USERNAME);
        session.removeAttribute(ROLE);
        return CommandResult.forward(LOGIN_PAGE);
    }
}
