package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customersPage(HttpServletRequest request, Model model) {

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("customers", service.findPaginated(PageRequest.of(page, size)));
        return "customers";
    }
}
