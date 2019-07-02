package com.lanit.satonin18.controller;

import com.lanit.satonin18.model.Notification;
import com.lanit.satonin18.model.Organization;
import com.lanit.satonin18.model.User;
import com.lanit.satonin18.service.CrudService;
import com.lanit.satonin18.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CrudService<User> userService;
    @Autowired
    private CrudService<Organization> organizationService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("notific_list", notificationService.list());
        model.addAttribute("user_list", userService.list());
        model.addAttribute("org_list", organizationService.list());
        return "cabinet/list";
    }

    @GetMapping("/selectUser")
    public String selectUser(@RequestParam("idSelectUser") int idSelectUser,
                              Model theModel) {
        System.out.println(idSelectUser);

        User user = userService.getById(idSelectUser);
        Organization org = user.getOrganization();
//        List<Notification> notifications4Org = notificationService.getNotificationsByIdOrg(org.getId());
        List<Notification> notifications4Org = org.getNotifications();
        for (Notification notif: notifications4Org) {
            System.out.println(notif);
        }
        theModel.addAttribute("notific_list", notifications4Org);
        theModel.addAttribute("user_list", userService.list());
        theModel.addAttribute("org_list", organizationService.list());
        theModel.addAttribute("user", user);
        theModel.addAttribute("org", org);
//        List<User> theUsers = userService.searchUserByLastName(theSearchName);
//        for(User user : theUsers) {
//            System.out.println(user);
//        }
//        theModel.addAttribute("list", theUsers);
        return "cabinet/list";
    }
}