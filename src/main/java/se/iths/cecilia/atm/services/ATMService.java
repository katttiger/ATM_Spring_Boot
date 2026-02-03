package se.iths.cecilia.atm.services;

import se.iths.cecilia.atm.exception.InsufficientFundsException;
import se.iths.cecilia.atm.exception.InvalidAmountException;
import se.iths.cecilia.atm.exception.MaxWithdrawalExceededException;
import se.iths.cecilia.atm.models.AccountComponent;

public class ATMService {
    private AccountComponent accountComponent;

    private final int maximumAmountAllowed = 500;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }


    public void addAmount(int amount) {
       if(amount>0){
           accountComponent.increaseBalance(amount);
       }
       else{
           throw new InvalidAmountException("You may only add an amount corresponding to a positive whole number");
       }
    }

    public void decreaseAmount(int amount) {
        if(amount<1){
            throw new InvalidAmountException("You may only withdraw an amount represented by a positive whole number");
        }
        if(amount > maximumAmountAllowed) {
            throw new MaxWithdrawalExceededException("You cannot withdraw more than 500 SEK");
        }
        if(amount > accountComponent.getBalance()) {
            throw new InsufficientFundsException("You do not have enough money");
        }
        else{
            accountComponent.decreaseBalance(amount);
        }
    }

    public int getBalance(){
        return accountComponent.getBalance();
    }


}
