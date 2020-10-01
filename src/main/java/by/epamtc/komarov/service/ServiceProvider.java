package by.epamtc.komarov.service;

public class ServiceProvider {
    private static final ServiceProvider instanse = new ServiceProvider();

    private final UserService userService = new UserService();

    public static ServiceProvider getInstance(){
        return instanse;
    }

    public UserService getUserService(){
        return userService;
    }
}
