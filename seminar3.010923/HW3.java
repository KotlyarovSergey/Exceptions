// Задача 3: Мини-калькулятор (ООП, исключения, коллекции) - по желанию

// Создайте класс MiniCalculator, который поддерживает следующие операции:

// Сложение
// Вычитание
// Умножение
// Деление
// Возведение в степень
// Операции должны выполняться с использованием ООП принципов (например, каждая операция 
// может быть отдельным классом). Класс должен поддерживать историю операций, которая хранится в 
// коллекции. Класс также должен выбрасывать исключения при недопустимых операциях (например, 
// деление на ноль).

import java.util.EmptyStackException;
import java.util.Stack;

public class HW3 {
    public static void main(String[] args) {
        MiniCalculator miniCalculator = new MiniCalculator();
        miniCalculator.summValues(12.6, -5.3);
        miniCalculator.substractionValues(35.445, 64.1);
        miniCalculator.multiplyValues(43.6, 2.5);
        miniCalculator.divideValues(12.6, 3.0);
        miniCalculator.divideValues(12.6, 0.0);

        System.out.println();
        miniCalculator.showLastOperation();
        miniCalculator.showLastOperation();
        miniCalculator.showLastOperation();
        miniCalculator.showLastOperation();
        miniCalculator.showLastOperation();
        miniCalculator.showLastOperation();
    }
}

class OperationData {
    private double valueA;
    private double valueB;
    private Operation operation;

    public OperationData(double valueA, double valueB, Operation operation) {
        this.valueA = valueA;
        this.valueB = valueB;
        this.operation = operation;
    }

    public double getValueA() {
        return valueA;
    }

    public double getValueB() {
        return valueB;
    }

    public Operation getOperation() {
        return operation;
    }
}

class MiniCalculator {
    Stack<OperationData> operations;

    public MiniCalculator() {
        operations = new Stack<>();
    }

    public void summValues(double valueA, double valueB) {
        Operation operation = new Summary(valueA, valueB);
        double result = operation.calculate();
        System.out.printf("%s + %s = %s\n", valueA, valueB, result);
        operations.add(new OperationData(valueA, valueB, operation));
    }

    public void substractionValues(double valueA, double valueB) {
        Operation operation = new Subtraction(valueA, valueB);
        double result = operation.calculate();
        System.out.printf("%s - %s = %s\n", valueA, valueB, result);
        operations.add(new OperationData(valueA, valueB, operation));
    }

    public void multiplyValues(double valueA, double valueB) {
        Operation operation = new Multiply(valueA, valueB);
        double result = operation.calculate();
        System.out.printf("%s * %s = %s\n", valueA, valueB, result);
        operations.add(new OperationData(valueA, valueB, operation));
    }

    public void divideValues(double valueA, double valueB) {
        Operation operation = new Division(valueA, valueB);
        try {
            double result = operation.calculate();
            System.out.printf("%s / %s = %s\n", valueA, valueB, result);
            operations.add(new OperationData(valueA, valueB, operation));
        } catch (ArithmeticException exception) {
            System.out.println("Ошибка! Нельзя делить на ноль.");
        }
    }

    public void showLastOperation(){
        try{
            OperationData opData = operations.pop();
            System.out.printf("%s %s %s\n", opData.getValueA(), opData.getOperation().description(), opData.getValueB());
        } catch (EmptyStackException exception){
            System.out.println("Очередь операций пуста!");
        }
        
    }

}

interface Operation {
    double calculate();
    String description();
}

class Summary implements Operation {
    private double valueA;
    private double valueB;

    public Summary(double valueA, double valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    @Override
    public double calculate() {
        return this.valueA + this.valueB;
    }

    @Override
    public String description() {
        return "+";
    }
}

class Subtraction implements Operation {
    private double valueA;
    private double valueB;

    public Subtraction(double valueA, double valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    @Override
    public double calculate() {
        return this.valueA - this.valueB;
    }

    @Override
    public String description() {
        return "-";
    }
}

class Multiply implements Operation {
    private double valueA;
    private double valueB;

    public Multiply(double valueA, double valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    @Override
    public double calculate() {
        return this.valueA * this.valueB;
    }

    @Override
    public String description() {
        return "*";
    }
}

class Division implements Operation {
    private double valueA;
    private double valueB;

    public Division(double valueA, double valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    @Override
    public double calculate() {
        if (valueB == 0)
            throw new ArithmeticException();
        return this.valueA / this.valueB;
    }

    @Override
    public String description() {
        return "/";
    }
}

