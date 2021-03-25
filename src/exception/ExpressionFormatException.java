package exception;

public class ExpressionFormatException extends RuntimeException {

    String messsage = "Your expression doesn't match the rules. Please check the rules and re-enter.";

    public ExpressionFormatException(String messsage) {
        this.messsage = messsage;
    }

    public ExpressionFormatException() {
    }

    public String getMesssage() {
        return messsage;
    }
}
