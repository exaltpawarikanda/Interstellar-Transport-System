package za.co.discovery.assignment.exceptions;

/**
 * @author Exalt Pawarikanda
 */
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String s) {
        super(s);
    }

    public RecordNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    protected RecordNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

