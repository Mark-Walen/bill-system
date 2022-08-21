package vlue.tech.billsystem.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vlue.tech.billsystem.exception.AuthenticationException;
import vlue.tech.billsystem.exception.RequestParameterException;
import vlue.tech.billsystem.util.ResultGenerator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity bindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new ResponseEntity<>(ResultGenerator.genErrorResult(400, message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return new ResponseEntity<>(ResultGenerator.genErrorResult(400, message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new ResponseEntity<>(ResultGenerator.genErrorResult(400, message), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RequestParameterException.class)
    @ResponseBody
    public ResponseEntity requestParameterExceptionHandler(RequestParameterException e) {
        String message = e.getMessage();
        return new ResponseEntity<>(ResultGenerator.genErrorResult(400, message), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity authenticationExceptionHandler(AuthenticationException e) {
        String message = e.getMessage();
        return new ResponseEntity<>(ResultGenerator.genErrorResult(401, message), HttpStatus.UNAUTHORIZED);
    }
}
