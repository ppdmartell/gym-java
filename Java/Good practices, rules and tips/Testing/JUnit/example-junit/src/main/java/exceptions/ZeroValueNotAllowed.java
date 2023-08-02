package exceptions;

public class ZeroValueNotAllowed extends RuntimeException {
    public ZeroValueNotAllowed(String msg) {
        super(msg);
    }
}
