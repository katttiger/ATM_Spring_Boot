package se.iths.cecilia.atm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountComponentTest {

    AccountComponent accountComponent;

    @BeforeEach
    void setUp() {
        accountComponent = new AccountComponent();
    }

    @Test
    @DisplayName("Assert that balance is 0 upon instantiation")
    void getBalanceTest() {
        int balance=accountComponent.getBalance();
        assertEquals(0,balance);
    }

    @Test
    @DisplayName("Assert that balance has changed")
    void getBalance() {
        int balance=accountComponent.getBalance();
        accountComponent.increaseBalance(400);
        int newBalance=accountComponent.getBalance();
        assertFalse(newBalance==balance);
    }

    @Test
    @DisplayName("Assert the correct balance is returned after several changes of balance")
    void getBalanceAfterSeveralChanges() {
        int balance=accountComponent.getBalance();
        accountComponent.increaseBalance(400);
        accountComponent.increaseBalance(400);
        accountComponent.decreaseBalance(200);
        accountComponent.increaseBalance(1000);
        int newBalance=accountComponent.getBalance();

        assertEquals(1600,newBalance);
    }

    @Test
    @DisplayName("Assert that balance is greater after calling method")
    void increaseBalance() {
        int oldBalance=accountComponent.getBalance();
        accountComponent.increaseBalance(500);
        int newBalance=accountComponent.getBalance();
        assertTrue(newBalance>oldBalance);
    }

    @Test
    @DisplayName("Assert that balance is decreased after calling method")
    void decreaseBalance() {
        accountComponent.increaseBalance(500);
        int oldBalance=accountComponent.getBalance();
        accountComponent.decreaseBalance(100);
        int newBalance=accountComponent.getBalance();
        assertTrue(newBalance<oldBalance);
    }
}