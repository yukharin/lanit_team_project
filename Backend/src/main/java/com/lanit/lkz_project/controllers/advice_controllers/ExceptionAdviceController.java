package com.lanit.lkz_project.controllers.advice_controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class ExceptionAdviceController {


    @Value("${general_error_page}")
    private String general_error_page;
    @Value("${error_404_page}")
    private String error_404_page;
    @Value("${error_403_page}")
    private String error_403_page;

    @ExceptionHandler(Throwable.class)
    public ModelAndView handle(Throwable ex) {
        log.error("Exception: ", ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(general_error_page);
        modelAndView.addObject("reason", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(Exception e) {
        log.error("404 error: ", e);
        return error_404_page;
    }


    @ExceptionHandler(AccessDeniedException.class)
    public String handleError403(Exception e) {
        log.error("Access denied: ", e);
        return error_403_page;
    }

}
