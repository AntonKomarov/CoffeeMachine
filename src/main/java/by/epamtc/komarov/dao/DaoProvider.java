package by.epamtc.komarov.dao;

public class DaoProvider {

    private static final DaoProvider instance = new DaoProvider();

    private final UserDao userDao = new UserDao();

    public static DaoProvider getInstance(){
        return instance;
    }

    public UserDao getUserDao(){
        return userDao;
    }


}
