package exception;

public class ExpressionFormatException extends RuntimeException {

    String message = "Your expression doesn't match the rules. Please check the rules and re-enter.";

    public ExpressionFormatException(String message) {
        this.message = message;
    }

    public ExpressionFormatException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
