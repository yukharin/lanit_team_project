package com.lanit.satonin18.app.controller.old.ignorMe.crud;

import com.lanit.satonin18.app.service.entities_service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("notificationController")
@RequestMapping("/crud/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", notificationService.findAll());
        return "crud/notification/list";
    }

//    @PostMapping("/search")
//    public String searchUsers(@RequestParam("theSearchName") String theSearchName,
//                              Model theModel) {
//        System.out.println(theSearchName);
//        List<User> theUsers = userService.findByLastNameIgnoreCaseContaining(theSearchName);
//        for(User user : theUsers) {
//            System.out.println(user);
//        }
//        theModel.addAttribute("list", theUsers);
//        theModel.addAttribute("valueSearch", theSearchName);
//        return "crud/notification/list";
//    }
}