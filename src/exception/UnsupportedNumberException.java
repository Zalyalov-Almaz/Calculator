package exception;

public class UnsupportedNumberException extends RuntimeException {

    private String message = "Entered expression is incorrect, numbers must be from 1 to 10";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
