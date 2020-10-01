package by.epamtc.komarov.controller.command.impl;

import by.epamtc.komarov.bean.Authorization;
import by.epamtc.komarov.bean.User;
import by.epamtc.komarov.controller.command.Command;
import by.epamtc.komarov.service.ServiceProvider;
import by.epamtc.komarov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Authorization authorization = new Authorization();
        authorization.setLogin(login);
        authorization.setPassword(password);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        HttpSession session = req.getSession();

        // add try catch
        User user = userService.authorization(authorization);
        System.out.println(user);
        session.setAttribute("user", user);
        resp.sendRedirect("controller?command=go_to_user_page");
    }
}
