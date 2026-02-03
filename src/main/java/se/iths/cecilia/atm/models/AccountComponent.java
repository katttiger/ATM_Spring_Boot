package se.iths.cecilia.atm.models;

public class AccountComponent {

    private int balance;

    public AccountComponent() {
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void increaseBalance(int balance) {
        this.balance += balance;
    }

    public void decreaseBalance(int balance) {
        this.balance -= balance;
    }

    //Used only for testing
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
