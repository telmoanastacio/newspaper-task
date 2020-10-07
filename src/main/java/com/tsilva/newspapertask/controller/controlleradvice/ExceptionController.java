package com.tsilva.newspapertask.controller.controlleradvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Telmo Silva on 07.10.2020.
 */

@ControllerAdvice
@RestControllerAdvice
public class ExceptionController
{
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleError(HttpServletResponse response, Exception e)
    {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        LOG.debug("Exception occurred.", e);

        return new ExceptionResponse(new Date().getTime(), HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionResponse handleError404(HttpServletResponse response, Exception e)
    {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        LOG.debug("Page not found.", e);

        return new ExceptionResponse(new Date().getTime(), HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }
}
