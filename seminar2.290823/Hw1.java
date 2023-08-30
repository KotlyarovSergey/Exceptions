import java.util.Scanner;

public class Hw1 {
    public static void main(String[] args) {
        double number = getNumber();
        try{
            checkNumber(number);
        }catch(InvalidNumberException exception){
            System.out.println("Ошибка!");
        }
    }

    private static double getNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите полжительное число: ");
        String input;
        double result;
        while(true){
            input = scanner.nextLine();
            try {
                result = Double.parseDouble(input);
                break;
            }catch(NumberFormatException e){
               System.out.print("Некорректный ввод! Повторите: ");
            }
        }
        scanner.close();
        return result;
    }

    private static void checkNumber(double number) throws InvalidNumberException {
        if (number <= 0)
            throw new InvalidNumberException("Некорректное число");

        System.out.println("Число корректно");
    }

}

class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}