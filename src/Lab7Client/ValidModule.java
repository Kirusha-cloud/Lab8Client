package Lab7Client;


import Flat.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, отвечаюший за проверку вводимых данных и создание экземпляра Flat.
 * Все методы fill* - отвечают за ввод с клавиатуры поля *.
 */
class ValidModule {
    /**
     * Метод отвечающий за создание экземпляра Flat.
     *
     * @return new Flat
     */
    Flat constructFlat() {
        Scanner cin = new Scanner(System.in);
        Flat flat = new Flat();
        flat.setName(fillName(cin));
        flat.setId(0);
        flat.setCoordinates(fillCoordinates(cin));
        flat.setArea(fillArea(cin));
        flat.setNumberOfRooms(fillNumberOfRooms(cin));
        flat.setFurniture(fillFurniture(cin));
        flat.setView(fillView(cin));
        flat.setTransport(fillTransport(cin));
        flat.setHouse(fillHouse(cin));
        return flat;
    }

    private String fillName(Scanner cin) {
        System.out.println("Введите название квартиры: ");
        String name = cin.nextLine();
        try {
            if (name == null || name.equals(""))
                throw new InputMismatchException();
        } catch (InputMismatchException inputExceptions) {
            System.out.println("Некорректный ввод, введите название квартиры заново. Строка не может быть пустой.");
            fillName(cin);
        }
        return name;
    }

    private int fillCoordinateX(Scanner cin) {
        try {
            System.out.println("Введите координату X квартиры: " + "(Координата  должна быть больше -897 и не равна 0)");
            int x = cin.nextInt();
            if (x <= -897 || Integer.valueOf(x).equals(-0)) {
                throw new IOException();
            }
            return x;
        } catch (IOException | InputMismatchException e) {
            System.out.println("Некорректный ввод. Введите координату X заново!");
            cin.nextLine();
            return fillCoordinateX(cin);
        }
    }

    private double fillCoordinateY(Scanner cin) {
        try {
            System.out.println("Введите координату Y квартиры: ");
            return cin.nextDouble();
        } catch (Exception e) {
            System.out.println("Некорректный ввод. Введите координату Y заново!");
            return fillCoordinateY(cin);
        }
    }

    private Coordinates fillCoordinates(Scanner cin) throws InputMismatchException {
        return new Coordinates(fillCoordinateX(cin), fillCoordinateY(cin));
    }

    private long fillArea(Scanner cin) {
        System.out.println("Введите area квартиры: (area должна быть больше 0)");
        try {
            long area = cin.nextLong();
            if (area <= 0)
                throw new IOException();
            return area;
        } catch (IOException e) {
            System.out.println("Некорректный ввод, введите заново!");
            return fillArea(cin);
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод, введите заново!");
            cin.nextLine();
            return fillArea(cin);
        }
    }

    private Integer fillNumberOfRooms(Scanner cin) {
        System.out.println("Сколько комнат в квартире: (Число комнат должно быть больше 0)");
        try {
            int nof = cin.nextInt();
            if (nof <= 0)
                throw new IOException();
            return nof;
        } catch (IOException e) {
            System.out.println("Некорректный ввод, введите число комнат заново!");
            return fillNumberOfRooms(cin);
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод, введите число комнат заново!");
            cin.nextLine();
            return fillNumberOfRooms(cin);
        }
    }

    private boolean fillFurniture(Scanner cin) {
        try {
            System.out.println("Укажите, находится ли мебель в квартире: (True/False)");
            return cin.nextBoolean();
        } catch (InputMismatchException e) {
            System.out.println("Неверный ввод, введите значение поля мебель заново!");
            cin.nextLine();
            return fillFurniture(cin);
        }
    }

    private View fillView(Scanner cin) {
        System.out.println("Введите вид, который открывается из окон квартиры: ");
        System.out.println("Список доступных вариантов: ");
        for (View s : View.values()) {
            System.out.println(s);
        }
        try {
            return View.valueOf(cin.next());
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Некорректный ввод, введите заново!");
            cin.nextLine();
            return fillView(cin);
        }
    }

    private Transport fillTransport(Scanner cin) {
        System.out.println("Введите один из вариантов количества транспорта, который проезжает мимо квратиры: ");
        System.out.println("Список доступных вариантов: ");
        for (Transport s : Transport.values()) {
            System.out.println(s);
        }
        try {
            return Transport.valueOf(cin.next());
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Некорректный ввод, введите заново!");
            cin.nextLine();
            return fillTransport(cin);
        }
    }

    private String fillHouseName(Scanner cin) {
        String name = cin.nextLine();
        try {
            if (name == null || name.equals("")) {
                cin.skip("");
                System.out.println("Введите название дома, в котором находится квартира: ");
                fillHouseName(cin);
            }
        } catch (InputMismatchException inputExceptions) {
            System.out.println("Некорректный ввод, введите название дома заново. Строка не может быть пустой.");
            fillName(cin);
        }
        return name;
    }

    private Integer fillHouseYear(Scanner cin) {
        try {

            System.out.println("Введите год, в котором дом был построен: (год должен быть больше 0)");
            int year = cin.nextInt();
            if (year <= -0) {
                System.out.println("Некорректный ввод, введите год заново!");
                return fillHouseYear(cin);
            } else return year;
        } catch (IllegalArgumentException | InputMismatchException e) {
            return fillHouseYear(cin);
        }
    }

    private Long fillHouseLifts(Scanner cin) {
        try {
            System.out.println("Введите число лифтов, находящихся в доме: (число лифтов должно быть больше 0");
            long lifts = cin.nextLong();
            if (lifts <= -0) {
                System.out.println("Некорректный ввод, введите число лифтов заново!");
                return fillHouseLifts(cin);
            } else return lifts;
        } catch (InputMismatchException e) {
            cin.nextLine();
            return fillHouseLifts(cin);
        }
    }

    private House fillHouse(Scanner cin) {
        return new House(fillHouseName(cin), fillHouseYear(cin), fillHouseLifts(cin));
    }
}
