package by.epamtc.komarov.controller.command.impl;

import by.epamtc.komarov.bean.Registration;
import by.epamtc.komarov.controller.command.Command;
import by.epamtc.komarov.service.ServiceProvider;
import by.epamtc.komarov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        Registration registration = new Registration();
        registration.setEmail(req.getParameter("email"));
        registration.setPhone(req.getParameter("phone"));
        registration.setLogin(req.getParameter("login"));
        registration.setPassword(req.getParameter("password"));

        HttpSession session = req.getSession();

        // registration message in global
        try {
            if (userService.registration(registration)) {
                session.setAttribute("Registration message", "Registration success");
            } else {
                session.setAttribute("Registration message", "Registration error");
            }
        } catch (ClassNotFoundException e) {
            session.setAttribute("Registration message", "User already exists");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("controller?command=go_to_registration_page");
    }
}

