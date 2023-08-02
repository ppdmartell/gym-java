package exceptions;

public class OutOfBoundsValue extends RuntimeException {
    public OutOfBoundsValue(String msg) {
        super(msg);
    }
}
