package dtu.application;

public class DoesNotExistErrorException extends Exception {
    private static final long serialVersionUID = 5644804693994321392L;
    public DoesNotExistErrorException(String errorMessage) {
        super(errorMessage);
    }
}
