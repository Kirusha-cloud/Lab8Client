package Lab7Client;

import java.util.HashMap;

public class CommandProvider {
    public CommandProvider() {
        this.initializeMap();
    }

    private Command helpCommand = new Command();
    private Command infoCommand = new Command();
    private Command showCommand = new Command();
    private Command addCommand = new Command();
    private Command updateCommand = new Command();
    private Command removeByIdCommand = new Command();
    private Command clearCommand = new Command();
    private Command executeScriptCommand = new Command();
    private Command exitCommand = new Command();
    private Command addIfMaxCommand = new Command();
    private Command removeGreaterCommand = new Command();
    private Command historyCommand = new Command();
    private Command loginCommand = new Command();
    private Command showMyObj = new Command();


    private Command minByFurnitureCommand = new Command();
    private Command groupCountingByTransportCommand = new Command();
    private Command printFieldDescendingNumberOfRoomsCommand = new Command();
    private HashMap<String, Command> commandHashMap = new HashMap<>();

    private void initializeMap() {
        helpCommand.setCommandName("help");
        infoCommand.setCommandName("info");
        showCommand.setCommandName("show");
        addCommand.setCommandName("add");
        updateCommand.setCommandName("update_id");
        removeByIdCommand.setCommandName("remove_by_id");
        clearCommand.setCommandName("clear");
        executeScriptCommand.setCommandName("execute_script");
        exitCommand.setCommandName("exit");
        addIfMaxCommand.setCommandName("add_if_max");
        removeGreaterCommand.setCommandName("remove_greater");
        historyCommand.setCommandName("history");
        minByFurnitureCommand.setCommandName("min_by_furniture");
        groupCountingByTransportCommand.setCommandName("group_counting_by_transport");
        printFieldDescendingNumberOfRoomsCommand.setCommandName("print_field_descending_number_of_rooms");
        loginCommand.setCommandName("login");
        showMyObj.setCommandName("show_my_obj");
        commandHashMap.put(helpCommand.getCommandName(), helpCommand);
        commandHashMap.put(showMyObj.getCommandName(), showMyObj);
        commandHashMap.put(infoCommand.getCommandName(), infoCommand);
        commandHashMap.put(showCommand.getCommandName(), showCommand);
        commandHashMap.put(addCommand.getCommandName(), addCommand);
        commandHashMap.put(updateCommand.getCommandName(), updateCommand);
        commandHashMap.put(removeByIdCommand.getCommandName(), removeByIdCommand);
        commandHashMap.put(clearCommand.getCommandName(), clearCommand);
        commandHashMap.put(executeScriptCommand.getCommandName(), executeScriptCommand);
        commandHashMap.put(exitCommand.getCommandName(), exitCommand);
        commandHashMap.put(addIfMaxCommand.getCommandName(), addIfMaxCommand);
        commandHashMap.put(removeGreaterCommand.getCommandName(), removeGreaterCommand);
        commandHashMap.put(historyCommand.getCommandName(), historyCommand);
        commandHashMap.put(minByFurnitureCommand.getCommandName(), minByFurnitureCommand);
        commandHashMap.put(groupCountingByTransportCommand.getCommandName(), groupCountingByTransportCommand);
        commandHashMap.put(printFieldDescendingNumberOfRoomsCommand.getCommandName(), printFieldDescendingNumberOfRoomsCommand);
        commandHashMap.put(loginCommand.getCommandName(), loginCommand);
    }

    public HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }
}

