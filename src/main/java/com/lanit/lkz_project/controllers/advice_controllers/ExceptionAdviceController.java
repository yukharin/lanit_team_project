package com.lanit.lkz_project.controllers.advice_controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.lanit.lkz_project.controllers.Utils.ERROR_404_PAGE;
import static com.lanit.lkz_project.controllers.Utils.GENERAL_ERROR_PAGE;

@ControllerAdvice
public class ExceptionAdviceController {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdviceController.class);

    @ExceptionHandler(Throwable.class)
    public ModelAndView handle(Throwable ex) {
        logger.error("exception: " + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(GENERAL_ERROR_PAGE);
        modelAndView.addObject("reason", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler({NoHandlerFoundException.class})
    public ModelAndView handleError404(Exception e, ModelAndView modelAndView) {
        logger.error("404 error: " + e.getMessage());
        modelAndView.setViewName(ERROR_404_PAGE);
        return modelAndView;
    }

}
