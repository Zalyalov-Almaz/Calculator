package exception;

public class UnsupportedOperatorException extends RuntimeException{

    private String message = "Unsupported operator entered, please check the rules and try again.";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
