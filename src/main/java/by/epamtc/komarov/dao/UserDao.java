package by.epamtc.komarov.dao;

import by.epamtc.komarov.bean.Authorization;
import by.epamtc.komarov.bean.Registration;
import by.epamtc.komarov.bean.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean registration(Registration registration) throws SQLException, IOException, ClassNotFoundException {

        boolean isRegistration = false;

        ConnectionProvider connectionProvider;
        Connection connection;
        PreparedStatement preparedStatement;

        connectionProvider = ConnectionProvider.getInstance();
        connection = connectionProvider.getConnection();
        // maybe set global like String
        preparedStatement = connection.prepareStatement("INSERT INTO users (user_email, user_login, user_phone,  user_password, user_role) " +
                "VALUES (?, ?, ?, ?, 1, 1)");

        preparedStatement.setString(1, registration.getEmail());
        preparedStatement.setString(2, registration.getLogin());
        preparedStatement.setString(3, registration.getPhone());
        preparedStatement.setString(4, registration.getPassword());

        if (preparedStatement.executeUpdate() == 1) {
            isRegistration = true;
        }

        return isRegistration;
    }

    public User authorization(Authorization authorization) {
        User user = null;

        ConnectionProvider connectionProvider;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connectionProvider = ConnectionProvider.getInstance();
            connection = connectionProvider.getConnection();
            // maybe set global like String
            preparedStatement = connection.prepareStatement("SELECT u.user_id, u.user_login, rol.user_role" +
                    "FROM users u, user_roles rol" +
                    "WHERE u.user_login=? and u.user_password=? and u.user_role=rol.user_role_id");

            preparedStatement.setString(1, authorization.getLogin());
            preparedStatement.setString(2, authorization.getPassword());

            System.out.println("authorizationData.getLogin() - " + authorization.getLogin());
            System.out.println("authorizationData.getPassword() - " + authorization.getPassword());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String id = resultSet.getString("user_id");
                String login = resultSet.getString("user_login");
                String role = resultSet.getString("user_role");

                user = new User();
                user.setId(id);
                user.setLogin(login);
                user.setRole(role);

            } else {
                System.out.println("nothing");
            }
            return user;
            // separate exception and add some functional
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
