package by.epamtc.komarov.dao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

    private static final ConnectionProvider instanse = new ConnectionProvider();

    private Connection connection;

    public static ConnectionProvider getInstance(){
        return instanse;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {

        Properties properties = new Properties();
        Class.forName("com.mysql.cj.jdnc.Driver");

        InputStream in = Files.newInputStream(Paths.get("databaseconnection.properties"));
        properties.load(in);

        // add try catch

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }


}
