package se.iths.cecilia.atm.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.cecilia.atm.exception.InsufficientFundsException;
import se.iths.cecilia.atm.exception.InvalidAmountException;
import se.iths.cecilia.atm.exception.MaxWithdrawalExceededException;
import se.iths.cecilia.atm.models.AccountComponent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class ATMServiceTest {

    @InjectMocks
    ATMService atmService;

    @Mock
    AccountComponent accountComponent;

    @BeforeEach
    void setUp() {
        atmService = new ATMService(accountComponent);
    }

    @Test
    @DisplayName("The new amount is larger than the old amount")
    void addAmountIncreasesBalance() {
        int oldAmount = atmService.getBalance();
        atmService.addAmount(500);

        when(accountComponent.getBalance()).thenReturn(500);
        int newAmount = atmService.getBalance();

        assertTrue(oldAmount < newAmount);
    }

    @Test
    @DisplayName("Selected amount is added")
    void addAmount() {
        int oldAmount = atmService.getBalance();

        when(accountComponent.getBalance()).thenReturn(oldAmount + 500);
        atmService.addAmount(500);
        int newAmount = atmService.getBalance();
        assertEquals(oldAmount + 500, newAmount);
    }

    @Test
    @DisplayName("No exceptions are thrown when add is called properly")
    void addAmountNoExceptionsAreThrown() {
        assertDoesNotThrow(() -> atmService.addAmount(500));
    }

    @Test
    @DisplayName("method increaseBalance is called")
    void increaseBalance() {
        int amount = 500;
        atmService.addAmount(amount);
        Mockito.verify(accountComponent).increaseBalance(amount);
    }

    @Test
    @DisplayName("addAmount throws InvalidAmountException due to the value being invalid")
    void addAmountThrowsInvalidAmountException() {
        assertThrows(InvalidAmountException.class, () -> atmService.addAmount(0));
    }

    @Test
    @DisplayName("Verify that the method decreaseAmount is called from accountComponent")
    void decreaseAmountIsCalled() {
        int amount = 10;
        Mockito.when(accountComponent.getBalance()).thenReturn(500);
        atmService.decreaseAmount(amount);
        Mockito.verify(accountComponent).decreaseBalance(amount);
    }

    @Test
    @DisplayName("decreaseAmount throws InvalidAmountException due to the amount entered being 0 or negative")
    void decreaseAmount() {
        int amount = 0;
        assertThrows(InvalidAmountException.class, () -> atmService.decreaseAmount(amount));
    }

    @Test
    @DisplayName("decreaseAmount throws InsufficientFundsException due to the amount entered being higher than that of the account")
    void decreaseAmountThrowsInsufficientFundsException() {
        int amount = 500;
        assertThrows(InsufficientFundsException.class, () -> atmService.decreaseAmount(amount));
    }

    @Test
    @DisplayName("decreaseAmount throws MaxWithdrawalExceedException due to the amount exceeding the set limit of 500 SEK")
    void decreaseAmountThrowsMaxWithdrawalExceedException() {
        int amount = 1000;
        assertThrows(MaxWithdrawalExceededException.class, () -> atmService.decreaseAmount(amount));
    }

    @Test
    @DisplayName("No exceptions are thrown when money is withdrawn")
    void withdrawMoney() {
        Mockito.when(accountComponent.getBalance()).thenReturn(500);
        atmService.decreaseAmount(400);
        assertDoesNotThrow(() -> atmService.decreaseAmount(400));
    }

    @Test
    @DisplayName("decreaseAmount withdraws the correct amount")
    void decreaseAmountWithdrawsTheCorrectAmount() {
        int amount = 300;
        Mockito.when(accountComponent.getBalance()).thenReturn(500, 500 - amount);
        atmService.decreaseAmount(amount);
        assertEquals(200, atmService.getBalance());

    }

    @Test
    @DisplayName("getBalance gets the correct balance")
    void getBalance() {
        Mockito.when(accountComponent.getBalance()).thenReturn(500);
        int balance = atmService.getBalance();
        assertEquals(500, balance);
    }
}