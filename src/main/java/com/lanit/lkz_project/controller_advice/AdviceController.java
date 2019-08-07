package com.lanit.lkz_project.controller_advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class AdviceController {

    private Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception ex) {
        logger.error("exception: " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("general-error");
        modelAndView.addObject("reason", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(Exception e) {
        logger.error("404 error: " + e.getMessage());
        ModelAndView modelAndView = new ModelAndView("404error");
        return modelAndView;
    }

}
