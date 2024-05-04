package dtu.application;

public class DoesNotExistException extends Exception {
    private static final long serialVersionUID = 5644804693994321392L;
    public DoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
