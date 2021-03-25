package exception;

public class UnsupportedNumberException extends RuntimeException {

    private String message = "Entered expression is incorrect, numbers must be from 1 to 10 or from I to X." +
            "If expression consists of roman numbers, output can't be negative or 0.";

    public UnsupportedNumberException(String message) {
        this.message = message;
    }

    public UnsupportedNumberException() {

    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
