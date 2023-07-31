package exceptions;

public class NegativeValueException extends RuntimeException {
    public NegativeValueException(String msg) {
        super(msg);
    }
}
