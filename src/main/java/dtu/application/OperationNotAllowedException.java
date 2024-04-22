package dtu.application;

/**
 * This class represents an error that is thrown when to show that
 * the operation has invalid parameters
 */
public class OperationNotAllowedException extends Exception {
    private static final long serialVersionUID = 5644804383994321392L;
    public OperationNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
