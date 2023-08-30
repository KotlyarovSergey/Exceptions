// Напишите программу, которая запрашивает у пользователя три числа и выполняет следующие проверки:

// Если первое число больше 100, выбросить исключение NumberOutOfRangeException с сообщением 
// "Первое число вне допустимого диапазона".

// Если второе число меньше 0, выбросить исключение NumberOutOfRangeException с сообщением 
// "Второе число вне допустимого диапазона".

// Если сумма первого и второго чисел меньше 10, выбросить исключение NumberSumException с сообщением 
// "Сумма первого и второго чисел слишком мала".

// Если третье число равно 0, выбросить исключение DivisionByZeroException с сообщением 
// "Деление на ноль недопустимо".

// В противном случае, программа должна выводить сообщение "Проверка пройдена успешно".
// - необходимо создать 3 класса собвстенных исключений

import java.util.Scanner;

public class Hw3 {
    public static void main(String[] args) {
        ThreeNumbers numbers = getData();
        checkNumbers(numbers);
    }

    private static ThreeNumbers getData(){
        Scanner scanner = new Scanner(System.in);
        ThreeNumbers result;
        while (true) {
            System.out.print("Введите первое число: ");
            String input1 = scanner.nextLine();
            System.out.print("Введите второе число: ");
            String input2 = scanner.nextLine();
            System.out.print("Введите третье число: ");
            String input3 = scanner.nextLine();
            try {
                result = new ThreeNumbers(Double.parseDouble(input1), 
                                        Double.parseDouble(input2), 
                                        Double.parseDouble(input3));
                scanner.close();
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Ошибка ввода!. Повторите.");
            }
        }
        return result;
    }

    private static void checkNumbers(ThreeNumbers numbers){
        if(numbers.getNumber1() > 100)
            throw new NumberOutOfRangeException("Первое число вне допустимого диапазона");
        if(numbers.getNumber2() < 0)
            throw new NumberOutOfRangeException("Второе число вне допустимого диапазона");
        if(numbers.getNumber1() + numbers.getNumber2() < 10)
            throw new NumberSumException("Сумма первого и второго чисел слишком мала");
        if(numbers.getNumber3() == 0)
            throw new DivisionByZeroException("Деление на ноль недопустимо");
        
        System.out.println("Проверка пройдена успешно");
    }
}

class ThreeNumbers {
    private double number1;
    private double number2;
    private double number3;

    public ThreeNumbers(double number1, double number2, double number3) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }

    public double getNumber3() {
        return number3;
    }
}

class NumberOutOfRangeException extends RuntimeException {
    public NumberOutOfRangeException(String message) {
        super(message);
    }
}

class NumberSumException extends RuntimeException {
    public NumberSumException(String message) {
        super(message);
    }
}

class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
