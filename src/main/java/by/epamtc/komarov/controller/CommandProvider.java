package by.epamtc.komarov.controller;

import by.epamtc.komarov.controller.command.Command;
import by.epamtc.komarov.controller.impl.RegistrationCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(ParameterName.REGISTRATION, new RegistrationCommand());
    }

    public Command getCommand(String commandName){
        Command command;
        ParameterName parameterName;

        commandName = commandName.toUpperCase();
        parameterName = ParameterName.valueOf(commandName);
        command = commands.get(parameterName);

        return command;
    }
}
