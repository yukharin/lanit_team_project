package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.authorization.UserServiceAuthorization;
import com.lanit.lkz_project.entities.Action;
import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.NotificationService;
import com.lanit.lkz_project.service.NotificationStatusService;
import com.lanit.lkz_project.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@SessionAttributes({"login", "password"})
public class PersonalAccountController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private NotificationStatusService notificationStatusService;
    @Autowired
    private UserServiceAuthorization authorization;

    @RequestMapping("/account/")
    public String toAccount(@NotNull @SessionAttribute String login, @NotNull @SessionAttribute String password, Model model) {
        User user = authorization.authorize(login, password);
        model.addAttribute("notifications", user.getOrganization().getNotifications());
        return "personalAccount";
    }

    @GetMapping("/account/actions/")
    public String getNotificationActions(@NotNull @RequestParam String id, Model model) {
        List<Action> actions = notificationService.getNotification(Long.valueOf(id)).getActions();
        model.addAttribute("actions", actions);
        return "notificationActions";
    }

    @PostMapping("/account/delete/")
    public String deleteNotification(@NotNull @RequestParam String id) {
        notificationService.removeNotification(Long.valueOf(id));
        return "redirect:/account/";
    }

    @GetMapping("/account/addNotification/")
    public String getAddNotificationPage(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("organizations", organizations);
        return "addNotification";
    }

    @PostMapping("/account/addNotification/add/")
    public String addNotification(@NotNull @RequestParam String notificationType,
                                  @NotNull @RequestParam String dateResponse,
                                  @NotNull @RequestParam String orgId) throws ParseException {
        System.err.println(notificationType);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfResponse = format.parse(dateResponse);
        Organization organization = organizationService.getOrganization(Long.valueOf(orgId));
        Notification notification = new Notification();
        notification.setOrganization(organization);
        notification.setNotificationType(notificationType);
        notification.setDateResponse(dateOfResponse);
        notification.setDateReceived(new Date());
        notification.setStatus(notificationStatusService.getNotificationStatus(1));
        notificationService.addNotification(notification);
        return "redirect:/account/";
    }


}
