// Описание задачи:
// Вы разрабатываете многопоточную программу для обработки банковских транзакций. 
// Вам необходимо реализовать класс "BankAccount", который представляет счет в банке и содержит 
// баланс пользователя. Класс "BankAccount" должен поддерживать две операции: пополнение счета и 
// снятие денег. Однако, у каждого счета есть ограничение по максимальному балансу, и попытка 
// пополнения или снятия средств, превышающих это ограничение, должна вызывать соответствующее исключение.
// __
// Вам также необходимо реализовать класс "Bank" для управления счетами и обработки транзакций. 
// Класс "Bank" должен содержать методы для создания новых счетов, пополнения и снятия денег с счетов.
// __
// Для обработки ошибок исключений, вы должны создать два пользовательских класса исключений:
// __
// "InsufficientFundsException" - выбрасывается при попытке снятия средств, превышающих доступный баланс на счете.
// "MaxBalanceExceededException" - выбрасывается при попытке пополнения счета, превышающего максимальное допустимое значение баланса.
// Класс "Bank" должен использовать многопоточность, чтобы обрабатывать транзакции с разных счетов параллельно.

import java.util.LinkedList;
import java.util.List;


public class Hw4 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createNewAccount(1000L);
        bank.createNewAccount(0);
        bank.createNewAccount(5465.46);
        System.out.println(bank);

        bank.refillAccount(1, 500);
        bank.withdrawalAccount(0, 499.99);
        System.out.println(bank);

        bank.refillAccount(2, 0321321342233L);
        bank.withdrawalAccount(0, 1500);
        
        System.out.println(bank);
    }
}
class Bank{
    private final List<BankAccount> accounts;
    private long nextId;
    public Bank() {
        accounts = new LinkedList<>();
        nextId = 0;
    }

    public void createNewAccount(double startAmount){
        BankAccount account = new BankAccount(nextId, startAmount);
        accounts.add(account);
        nextId++;
    }

    public void refillAccount(long id, double amount){
        int index = getAccountIndex(id);
        if(index >= 0){
            try {
                accounts.get(index).addition(amount);
            } catch (MaxBalanceExceededException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void withdrawalAccount(long id, double amount){
        int index = getAccountIndex(id);
        if(index >= 0){
            try {
                accounts.get(index).removal(amount);
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private int getAccountIndex(long id){
        for(int i = 0; i < accounts.size(); i++)
            if(accounts.get(i).getId() == id)
                return i;
        
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder("{");
        for (BankAccount bankAccount : accounts) {
            sBuilder.append("[")
                    .append(bankAccount)
                    .append("]");
        }
        sBuilder.append("}");
        return sBuilder.toString();
    }
}

class BankAccount{
    private final double MAX_BALANCE = 10_000_000_000L;
    private long id;
    private double balance;

    public BankAccount(long id, double startBalance) {
        this.id = id;
        this.balance = startBalance;
    }
    public BankAccount(long id) {
        this(id, 0);
    }
    
    
    public long getId() {
        return id;
    }

    public boolean addition(double addictionAmount) throws MaxBalanceExceededException{
        if(this.balance + addictionAmount > MAX_BALANCE)
        {
            String errorMessage = String.format("Addiction error! Account %s, balance %s, addiction amount %s", id, balance, addictionAmount);
            throw new MaxBalanceExceededException(errorMessage);
        }

        this.balance += addictionAmount;
        return true;
    }

    public boolean removal(double removalAmount) throws InsufficientFundsException{
        if(removalAmount > this.balance){
            String errorMessage = String.format("Romoving error! Account %s, balance %s, removal amount %s", id, balance, removalAmount);
            throw new InsufficientFundsException(errorMessage);
        }
            
        this.balance -= removalAmount;
        return true;
    }

    @Override
    public String toString(){
        return String.format("Account %s, balance: %s", id, balance);
    }
}

class InsufficientFundsException extends Exception{
    InsufficientFundsException(String message){
        super(message);
    }
}

class MaxBalanceExceededException extends Exception{
    MaxBalanceExceededException(String message){
        super(message);
    }
}