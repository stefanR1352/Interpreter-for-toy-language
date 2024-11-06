package exceptions;

public class ReadException extends RuntimeException {
    public ReadException(String message) {
        super(message);
    }
}
