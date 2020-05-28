package Lab7Client;

import Flat.Flat;

import java.util.Scanner;

public class InputModule {
    private ConnectModule connectModule = new ConnectModule();
    private ValidModule validModule = new ValidModule();
    private CommandProvider commandProvider = new CommandProvider();

    InputModule() {
        connectModule.connect();
        connectModule.loginWithPass();
    }

    public void run() {
        try {
            System.out.println("Введите команду: ");
            Scanner cin = new Scanner(System.in);
            while (cin.hasNext()) {
                String[] args = cin.nextLine().split(" ");
                Command element;
                switch (args[0]) {
                    case "help":
                        element = commandProvider.getCommandHashMap().get("help");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "info":
                        element = commandProvider.getCommandHashMap().get("info");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "show":
                        element = commandProvider.getCommandHashMap().get("show");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "add":
                        Flat addFlat = validModule.constructFlat();
                        element = commandProvider.getCommandHashMap().get("add");
                        element.setElement(addFlat);
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "update_id":
                        int id = Integer.parseInt(args[1]);
                        Flat updateFlat = validModule.constructFlat();
                        element = commandProvider.getCommandHashMap().get("update_id");
                        element.setElement(updateFlat);
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        element.setId(id);
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "remove_by_id":
                        element = commandProvider.getCommandHashMap().get("remove_by_id");
                        element.setId(Integer.parseInt(args[1]));
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "clear":
                        element = commandProvider.getCommandHashMap().get("clear");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "execute_script":
                        element = commandProvider.getCommandHashMap().get("execute_script");
                        element.setFilename(args[1]);
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "exit":
                        System.out.println("Завершение программы.");
                        System.exit(0);
                        break;
                    case "add_if_max":
                        Flat addIfMaxFlat = validModule.constructFlat();
                        element = commandProvider.getCommandHashMap().get("add_if_max");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        element.setElement(addIfMaxFlat);
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "remove_greater":
                        Flat removeGreaterFlat = validModule.constructFlat();
                        element = commandProvider.getCommandHashMap().get("remove_greater");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        element.setElement(removeGreaterFlat);
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "history":
                        element = commandProvider.getCommandHashMap().get("history");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "min_by_furniture":
                        element = commandProvider.getCommandHashMap().get("min_by_furniture");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        break;
                    case "group_counting_by_transport":
                        element = commandProvider.getCommandHashMap().get("group_counting_by_transport");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "print_field_descending_number_of_rooms":
                        element = commandProvider.getCommandHashMap().get("print_field_descending_number_of_rooms");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                        break;
                    case "show_my_obj":
                        element = commandProvider.getCommandHashMap().get("show_my_obj");
                        element.setAddress(connectModule.getSocket().getLocalSocketAddress().toString());
                        connectModule.transferObj(connectModule.getSocket(), element);
                        connectModule.loginWithPass();
                        System.out.println("Введите команду: ");
                    case "":
                        break;
                    default:
                        System.out.println("Такой команды не существует. Список доступных команд вы можете увидеть с помощью команды help.");
                        System.out.println("Введите команду: ");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Недопустимое число аргументов.");
            run();
        }
    }
}
