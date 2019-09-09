package com.lanit.satonin18.app.controller.angular.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.utils.CheckOnBuildJson;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Controller("angularUtilServiceController")
@RequestMapping("/angular/service")
public class UtilServiceController {

    @GetMapping("/getUser")
    public /*@ResponseBody*/ User cabinet4renderHtml(
            @AuthenticationPrincipal UserAccount userAccount) throws JsonProcessingException {
        User currentUser = userAccount.getUser();
        new CheckOnBuildJson().check(currentUser);
        return currentUser;
    }
}
