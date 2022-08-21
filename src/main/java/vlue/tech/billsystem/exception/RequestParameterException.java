package vlue.tech.billsystem.exception;

public class RequestParameterException extends RuntimeException {

    public RequestParameterException() {
        super("参数异常");
    }

    public RequestParameterException(String message) {
        super(message);
    }
}
