package exceptions;

public class MinimumBalanceViolationException extends RuntimeException {
    public MinimumBalanceViolationException(String msg) {
        super(msg);
    }
}
