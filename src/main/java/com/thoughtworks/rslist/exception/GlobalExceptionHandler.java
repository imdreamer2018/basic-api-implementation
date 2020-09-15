package com.thoughtworks.rslist.exception;


import com.thoughtworks.rslist.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = NullPointException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorDTO RsNullPointErrorHandler(HttpServletRequest req, NullPointException e) {
        ErrorDTO r = new ErrorDTO();
        r.setMessage(e.getMessage());
        r.setCode(200);
        return r;
    }

}
