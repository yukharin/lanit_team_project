package com.lanit.lkz_project.controllers.mvc_controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    private Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(path = "/error")
    public ModelAndView handle(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("general-error");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable reason = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String message = reason.getMessage();
        logger.error("Error with status code: " + statusCode
                + " and reason exception: " + message);
        modelAndView.addObject("statusCode", statusCode);
        modelAndView.addObject("reason", message);
        return modelAndView;
    }
}