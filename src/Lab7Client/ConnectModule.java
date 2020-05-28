package Lab7Client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Класс, реализующий взаимодействие с сервером.
 * Содержит методы connect, read, write.
 * Осуществляет
 */
public class ConnectModule {
    private static final int PORT = 13001;
    private static final String ADDRESS = "127.0.0.1";
    private Socket socket = new Socket();
    private CommandProvider commandProvider = new CommandProvider();

    void connect() {
        try {
            socket.connect(new InetSocketAddress(ADDRESS, PORT));
            System.out.println("Здравствуйте, подключение " + socket.getRemoteSocketAddress() + " прошло успешно.");
        } catch (IOException e) {
            System.out.println("Сервер временно недоступен, повторите попытку позднее.");
            System.exit(0);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void read(Socket socket) {
        try {
            byte[] buffer = new byte[100 * 100];
            InputStream is = socket.getInputStream();
            int numRead = is.read(buffer);
            if (numRead != 0) {
                StringBuilder s = new StringBuilder();
                Scanner cin = new Scanner(new String(buffer));
                while (cin.hasNextLine()) {
                    s.append(cin.nextLine());
                    if (s.toString().contains("Неверный логин или пароль.") || s.toString().contains("Произошла ошибка, введите логин и пароль ещё раз.")) {
                        System.out.println(s.toString());
                        loginWithPass();
                    } else {
                        if (s.charAt(0) == ' ' || s.charAt(0) == '[') {
                            s.deleteCharAt(0);
                        } else {
                            System.out.println(s.toString().replace("]", "").replace(",", "").trim().replace("\n", ""));
                        }
                        s.delete(0, s.length());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Сервер временно недоступен. Повторите попытку позднее.");
            System.exit(0);
        }
    }

    public void write(Socket socket, Command command) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            bos.write(castToByteArray(command), 0, castToByteArray(command).length);
            bos.flush();
        } catch (IOException e) {
            System.out.println("Сервер временно недоступен. Повторите попытку позднее.");
            System.exit(0);
        }
    }

    /**
     * Метод для упаковки команды в массив байт.
     *
     * @param command - принимает команду
     * @return команда в виде массива байтов.
     * @throws IOException I/O exception, который будет пойман в методе write, если будет выброшен
     */
    private byte[] castToByteArray(Command command) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        return baos.toByteArray();
    }

    void transferObj(Socket socket, Command command) {
        write(socket, command);
        read(socket);
    }

    void loginWithPass() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            System.out.println("Введите логин: ");
            Scanner cin = new Scanner(System.in);
            String login = cin.nextLine();
            System.out.println("Введите пароль: ");
            String pepper = "*Q$#@#9123";
            String password = cin.nextLine();
            Command command = commandProvider.getCommandHashMap().get("login");
            command.setLogin(login);
            command.setHashbytes(md.digest((pepper + password).getBytes(StandardCharsets.UTF_8)));
            command.setAddress(getSocket().getLocalSocketAddress().toString());
            transferObj(getSocket(), command);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
