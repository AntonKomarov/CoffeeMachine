package by.epamtc.komarov.controller.impl;

import by.epamtc.komarov.bean.Registration;
import by.epamtc.komarov.controller.command.Command;
import by.epamtc.komarov.service.ServiceProvider;
import by.epamtc.komarov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_PHONE = "phone";

    private static final String REGISTRATION_PAGE = "Controller?=command=go_to_registration";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {

        String email = request.getParameter(PARAMETER_EMAIL);
        String phone = request.getParameter(PARAMETER_PHONE);
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        Registration registration = new Registration();
        registration.setLogin(login);
        registration.setPassword(password);
        registration.setEmail(email);
        registration.setPhone(phone);

        if(userService.registration(registration)){
            request.getServletContext().setAttribute("registration_message", "registration success");
        } else {
            request.getServletContext().setAttribute("registration_message", "registration error");
        }

        response.sendRedirect(REGISTRATION_PAGE);
    }

}
