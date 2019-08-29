package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.UserJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Controller("angularController")
@RequestMapping("/angular")
public class Angular {

    @ResponseBody
    @RequestMapping("/")
    public String getPage(UserJson userJson) {
        
    }
}
