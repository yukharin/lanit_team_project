package com.lanit.satonin18.app.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.satonin18.app.TestUser;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.input.form.FilterForm;
import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import com.lanit.satonin18.app.objects.output.Cabinet4renderHtml;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.property_in_future.DEFAULT_CABINET_VARS;
import com.lanit.satonin18.app.objects.state4session.CabinetSessionState;
import com.lanit.satonin18.app.service.app_service.CabinetService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Controller("angularInputController")
@RequestMapping("/angular/input")
public class InputController {

    @Autowired
    private UserService userService;
    @Autowired
    private CabinetService cabinetService;
    @Autowired
    private NotificationService notificationService;


//    @RequestMapping("/test_user")
//    public /*@ResponseBody*/ TestUser test_user() throws JsonProcessingException {
//        TestUser testUser = new TestUser("ht", 3);
//
//        testOnBuildJson(testUser);
//
//        return testUser;
//    }
//
//    @RequestMapping("/test_users")
//    public /*@ResponseBody*/ List<TestUser> test_users() throws JsonProcessingException {
//        TestUser testUser1 = new TestUser("111", 1);
//        TestUser testUser2 = new TestUser("22", 22);
//        TestUser testUser3 = new TestUser("вагон", 33);
//        ArrayList<TestUser> testUsers = new ArrayList<>();
//        testUsers.add(testUser1);
//        testUsers.add(testUser2);
//        testUsers.add(testUser3);
//
//        testOnBuildJson(testUsers);
//
//        return testUsers;
//    }

//    @RequestMapping("/user")
//    public /*@ResponseBody*/ User user() throws JsonProcessingException {
//        User user = userService.findById(1);
//
//        testOnBuildJson(user);
//
//        return user;
//    }

    @RequestMapping("/users")
    public /*@ResponseBody*/ List<User> users() throws JsonProcessingException {
        List<User> users = userService.findAll();

        testOnBuildJson(users);

        return users;
    }

    @PostMapping("/has_user")
    public /*@ResponseBody*/ boolean has_user(int id) throws JsonProcessingException {
        User user = userService.findById(id);

        return user != null;
    }

    private void testOnBuildJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(obj);
        System.err.println("JSON: " + str);
    }
}

