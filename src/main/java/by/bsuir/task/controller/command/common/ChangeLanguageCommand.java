package by.bsuir.task.controller.command.common;

import by.bsuir.task.controller.command.Command;
import by.bsuir.task.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    private static final String START_PAGE = "startPage";
    private static final String LANGUAGE = "language";
    private static final String REDIRECT_COMMAND = "controller?command=";
    private static final String LANG = "lang";
    private static final Integer COMMAND_INDEX = 46;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = request.getParameter(LANG);
        String query = request.getQueryString();
        session.setAttribute(LANGUAGE, language);
        if (query.length() > COMMAND_INDEX) {
            String page = query.substring(COMMAND_INDEX);
            return CommandResult.redirect(REDIRECT_COMMAND + page);
        } else {
            return CommandResult.redirect(REDIRECT_COMMAND + START_PAGE);
        }
    }
}
