package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.Action;
import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.service.ActionService;
import com.lanit.lkz_project.service.NotificationService;
import com.lanit.lkz_project.service.NotificationStatusService;
import com.lanit.lkz_project.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class PersonalAccountController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private NotificationStatusService notificationStatusService;

    @GetMapping("/account/")
    public String getAllNotifications(Model model) {
        List<Notification> notifications = notificationService.notifications();
        model.addAttribute("notifications", notifications);
        return "personalAccount";
    }

    @GetMapping("/account/actions")
    public String getNotificationActions(HttpServletRequest request, Model model) {
        Long notificationId = Long.valueOf(request.getParameter("id"));
        List<Action> actions = actionService.actionOfNotification(notificationId);
        model.addAttribute("actions", actions);
        return "notificationActions";
    }

    @PostMapping("/account/delete")
    public String deleteNotification(HttpServletRequest request) {
        Long notificationId = Long.valueOf(request.getParameter("id"));
        notificationService.removeNotification(notificationId);
        return "redirect:/account";
    }

    @GetMapping("/account/getAddingPage")
    public String getAddNotificationPage(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("organizations", organizations);
        return "addNotification";
    }


}