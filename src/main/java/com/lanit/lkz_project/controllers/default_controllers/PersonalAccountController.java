package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.authorization.UserAuthorizationService;
import com.lanit.lkz_project.entities.*;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.entities_service.NotificationService;
import com.lanit.lkz_project.service.entities_service.OrganizationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
public class PersonalAccountController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private PersonalAccountService personalAccountService;
    @Autowired
    private UserAuthorizationService userAuthorization;


    @RequestMapping("/account/")
    public String getPage(@SessionAttribute(required = false) String login,
                          @SessionAttribute(required = false) String password,
                          @RequestBody Optional<PersonalAccountPage<Notification>> optionalPage,
                          Model model) {
        @NonNull User user = userAuthorization.authorize(login, password);
        PersonalAccountPage<Notification> page = optionalPage.orElseGet(PersonalAccountPage::new);
        System.err.println("NUMBER:" + page.getPage().getPageable().getPageNumber());
        personalAccountService.setAccountPageState(page, user);
        model.addAttribute("stateOfPage", page);
        model.addAttribute("user", user);
        return "personalAccount";
    }

    @PostMapping("/account/notification_actions_history/")
    public String getNotificationActions(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam String id,
            Model model) {
        User user = userAuthorization.authorize(login, password);
        Set<Action> actions = notificationService.getNotification(Long.valueOf(id)).getActions();
        model.addAttribute("user", user);
        model.addAttribute("actions", actions);
        return "notificationActions";
    }

    @PostMapping("/account/delete/")
    public String deleteNotification(@NonNull @RequestParam String id) {
        notificationService.removeNotification(Long.valueOf(id));
        return "redirect:/account/";
    }

    @GetMapping("/account/addNotification/")
    public String getAddNotificationPage(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            Model model) {
        User user = userAuthorization.authorize(login, password);
        model.addAttribute("user", user);
        List<Organization> organizations = organizationService.nonGovernmentOrganizations();
        model.addAttribute("organizations", organizations);
        return "addNotification";
    }

    @PostMapping("/account/addNotification/add/")
    public String addNotification(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam String notificationType,
            @NonNull @RequestParam String dateResponse,
            @NonNull @RequestParam String orgId) throws ParseException {
        User user = userAuthorization.authorize(login, password);
        personalAccountService.addNotification(user, notificationType, dateResponse, orgId);
        return "redirect:/account/";
    }

    @PostMapping("/account/notification_info/")
    public String getNotificationPage(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam String id,
            Model model) {
        User user = userAuthorization.authorize(login, password);
        Notification notification = notificationService.getNotification(Long.valueOf(id));
        EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
        model.addAttribute("user", user);
        model.addAttribute("notification", notification);
        model.addAttribute("actionTypes", types);
        return "notificationInfo";
    }

    @PostMapping("/account/notification_info/commit/")
    public String addAction(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam String actionTypeParam,
            @NonNull @RequestParam String idNotification,
            @NonNull @RequestParam String comment) {
        User userImplementor = userAuthorization.authorize(login, password);
        personalAccountService.addAction(userImplementor, actionTypeParam, idNotification, comment);
        return "redirect:/account/";
    }

}
