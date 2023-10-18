package mk.ukim.finki.ordersmanagement.domain.exceptions;

public class InsufficientCreditException extends RuntimeException {
    public InsufficientCreditException() {
        super("Insufficient credit balance to complete the purchase.");
    }
}
