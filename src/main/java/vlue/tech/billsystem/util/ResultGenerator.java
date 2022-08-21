package vlue.tech.billsystem.util;

import org.apache.commons.lang3.StringUtils;
import reactor.util.annotation.Nullable;
import vlue.tech.billsystem.vo.Result;

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    public static Result genSuccessResult() {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static Result genSuccessResult(String message) {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        return result;
    }

    public static Result genSuccessResult(@Nullable String message, Object data) {
        if (message == null || message.equals("")) message = DEFAULT_SUCCESS_MESSAGE;
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result genFailResult(String message) {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SERVER_ERROR);
        if (StringUtils.isEmpty(message)) {
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static Result genErrorResult(int code, String message) {
        Result result = new Result();
        result.setResultCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result genDmlResult(boolean result) {
        return result ? genSuccessResult() : genFailResult("database error");
    }
}
