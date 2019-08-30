package com.lanit.satonin18.app.controller.rest;

import com.lanit.satonin18.app.UserJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller("angularController")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/angular")
public class Angular {

    @RequestMapping("/user")
    public /*@ResponseBody*/ UserJson user() {
        UserJson userJson = new UserJson("ht", 3);
        return userJson;
    }
}
