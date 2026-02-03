package se.iths.cecilia.atm.exception;

public class MaxWithdrawalExceededException extends RuntimeException {
    public MaxWithdrawalExceededException(String message) {
        super(message);
    }
}
