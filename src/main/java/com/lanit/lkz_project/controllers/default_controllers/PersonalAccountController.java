package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.authorization.UserServiceAuthorization;
import com.lanit.lkz_project.entities.*;
import com.lanit.lkz_project.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;


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
    private ActionService actionService;
    @Autowired
    private ActionTypeService actionTypeService;
    @Autowired
    private UserServiceAuthorization authorization;


    @RequestMapping("/account/")
    public String toAccount(@SessionAttribute String login,
                            @SessionAttribute String password, Model model) {
        User user = authorization.authorize(login, password);
        model.addAttribute("notifications", user.getOrganization().getNotifications());
        return "personalAccount";
    }

    @GetMapping("/account/actions_history/")
    public String getNotificationActions(@RequestParam String id,
                                         Model model) {
        Set<Action> actions = notificationService.getNotification(Long.valueOf(id)).getActions();
        model.addAttribute("actions", actions);
        return "notificationActions";
    }

    @PostMapping("/account/delete/")
    public String deleteNotification(@RequestParam String id) {
        notificationService.removeNotification(Long.valueOf(id));
        return "redirect:/account/";
    }

    @GetMapping("/account/addNotification/")
    public String getAddNotificationPage(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("organizations", organizations);
        return "addNotification";
    }

    @GetMapping("/account/perform_action/")
    public String getAddActionPage(@RequestParam String id, Model model) {
        Notification notification = notificationService.getNotification(Long.valueOf(id));
        List<ActionType> actionTypes = actionTypeService.actionTypes();
        model.addAttribute("notification", notification);
        model.addAttribute("actionTypes", actionTypes);
        return "addAction";
    }

    @PostMapping("/account/addAction/")
    public String addAction(@RequestParam String idActionType,
                            @RequestParam String idNotification,
                            @RequestParam String comment,
                            @SessionAttribute String login,
                            @SessionAttribute String password) {
        User user = authorization.authorize(login, password);
        ActionType actionType = actionTypeService.getActionType(Long.valueOf(idActionType));
        Notification notification = notificationService.getNotification(Long.valueOf(idNotification));
        NotificationStatus status = notification.getStatus();
        Action action = new Action();
        action.setActionType(actionType);
        action.setNotification(notification);
        action.setContent(comment);
        action.setDate(new Date());
        action.setImplementor(user);
        action.setStatus(notificationStatusService.getNotificationStatus(2));
        actionService.addAction(action);
        return "redirect:/account/";
    }

    @PostMapping("/account/addNotification/add/")
    public String addNotification(@RequestParam String notificationType,
                                  @RequestParam String dateResponse,
                                  @RequestParam String orgId) throws ParseException {
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
