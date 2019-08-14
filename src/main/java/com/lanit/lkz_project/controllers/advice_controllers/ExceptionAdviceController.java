package com.lanit.lkz_project.controllers.advice_controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionAdviceController {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdviceController.class);

    @Value("${general_error_page}")
    private String general_error_page;
    @Value("${error_404_page}")
    private String error_404_page;

//    @ExceptionHandler(Throwable.class)
//    public ModelAndView handle(Throwable ex) {
//        logger.error("exception: " + ex.getMessage());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName(general_error_page);
//        modelAndView.addObject("reason", ex.getMessage());
//        return modelAndView;
//    }
//
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public String handleError404(Exception e) {
//        logger.error("404 error: " + e.getMessage());
//        return error_404_page;
//    }

}
