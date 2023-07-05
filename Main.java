import java.io.FileWriter;
import java.util.Scanner;

/**
 * Main
 */
public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] abonent = ReadString();
        while (!abonent[0].equals("Q")) {
            try {
                CheckInput(abonent);
                WriteFile(abonent);
            } catch (MyErrorData e) {
                System.out.println(e.getMessage());
            } catch (MyErrorType e) {
                System.out.println(e.getMessage());
            }
            abonent = ReadString();
        }
        sc.close();
        System.out.println("Программа завершила работу.");

    }

    public static String[] ReadString() {
        String[] inputStr;
        try {
            System.out.println("Введите фамилию имя отчество телефон, через пробел.");
            System.out.println("Для выхода введите Q.");
            inputStr = sc.nextLine().split(" ");
        } catch (Exception e) {
            inputStr = new String[] { "" };
            System.out.printf("Ошибка ввода: %s", e.getMessage());
        }
        return inputStr;
    }

    public static void CheckInput(String[] str) throws MyErrorData, MyErrorType {
        if (str.length < 4) {
            // System.out.println("Недостаточно данных");
            throw new MyErrorData("Недостаточно данных");
        } else if (str.length > 4) {
            // System.out.println("Лишние данные");
            throw new MyErrorData("Лишние данные");
        }
        try {
            Integer.parseInt(str[3]);
        } catch (Exception e) {
            throw new MyErrorType("Номер телефна не корректный");
        }
    }

    public static void WriteFile(String[] output) {
        String fileName = output[0] + ".txt";
        try (FileWriter wr = new FileWriter(fileName, true)) {
            wr.write(output[0] + " " + output[1] + " " + output[2] + " " + output[3] + "\n");
            System.out.println("Данные записаны в файл: " + output[0] + ".txt");
        } catch (Exception e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }

    }
}

class MyErrorData extends Exception {
    public MyErrorData(String message) {
        super(message);
    }
}

class MyErrorType extends Exception {
    public MyErrorType(String message) {
        super(message);
    }
}