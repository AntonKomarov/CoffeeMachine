package by.epamtc.komarov.service;

import by.epamtc.komarov.bean.Authorization;
import by.epamtc.komarov.bean.Registration;
import by.epamtc.komarov.bean.User;
import by.epamtc.komarov.dao.DaoProvider;
import by.epamtc.komarov.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {

    public boolean registration(Registration registrationForm) throws SQLException, IOException, ClassNotFoundException {

        boolean registration;

        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDAO = daoProvider.getUserDao();

        registration = userDAO.registration(registrationForm);

        return registration;

    }

    public User authorization(Authorization authorization) {
        User user;

        DaoProvider daoProvider = DaoProvider.getInstance();
        UserDao userDAO = daoProvider.getUserDao();

        // add Exception
        user = userDAO.authorization(authorization);

        return user;
    }

}
