package by.bsuir.task.controller;

import by.bsuir.task.controller.command.Command;
import by.bsuir.task.controller.command.CommandFactory;
import by.bsuir.task.controller.command.CommandResult;
import by.bsuir.task.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";
    private static final String ERROR_PAGE = "/WEB-INF/pages/error/Error500.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter(COMMAND);
        Command command = CommandFactory.getInstance().getCommand(parameter);

        try {
            CommandResult commandResult = command.execute(req, resp);
            String page = commandResult.getPage();
            if (commandResult.isRedirect()) {
                resp.sendRedirect(page);
            } else {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
                requestDispatcher.forward(req, resp);
            }
        } catch (ServiceException e) {
            CommandResult.forward(ERROR_PAGE);
        }
    }
}
