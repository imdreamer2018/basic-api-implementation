package com.thoughtworks.rslist.exception;


import com.thoughtworks.rslist.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = BaseRsListException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO RsNullPointErrorHandler(HttpServletRequest req, BaseRsListException e) {
        ErrorDTO r = new ErrorDTO();
        r.setMessage(e.getMessage());
        r.setCode(400);
        return r;
    }

    @ExceptionHandler(value = BaseUserException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO UserNullPointErrorHandler(HttpServletRequest req, BaseUserException e) {
        ErrorDTO r = new ErrorDTO();
        r.setMessage(e.getMessage());
        r.setCode(400);
        return r;
    }

}
