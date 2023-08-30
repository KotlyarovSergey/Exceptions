import java.util.Scanner;

public class Hw2 {
    public static void main(String[] args) {
        double number1 = getNumber();
        double number2 = getNumber();
        try {
            if (checkNumber(number2))
                System.out.println("Результат деления: " + number1 / number2);
        } catch (DivisionByZeroException exception) {
            System.out.println("Ошибка!");
        }
    }

    private static double getNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        String input;
        double result;
        while (true) {
            input = scanner.nextLine();
            try {
                result = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Некорректный ввод! Повторите: ");
            }
        }
        return result;
    }

    private static boolean checkNumber(double nuber) throws DivisionByZeroException {
        if (nuber == 0)
            throw new DivisionByZeroException("Деление на ноль недопустимо");
        return true;
    }
}

class DivisionByZeroException extends Exception {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
