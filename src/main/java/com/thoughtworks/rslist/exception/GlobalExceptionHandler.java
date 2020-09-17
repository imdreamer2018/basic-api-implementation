package com.thoughtworks.rslist.exception;


import com.thoughtworks.rslist.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO ValidationExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        ErrorDTO r = new ErrorDTO();
        r.setMessage(e.getMessage());
        r.setCode(400);
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        logger.error("[LOGGING]: " + e.getMessage());
        return r;
    }

}
