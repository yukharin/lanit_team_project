package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.authorization.UserAuthorizationService;
import com.lanit.lkz_project.entities.dto.PersonalAccountPage;
import com.lanit.lkz_project.entities.jpa_entities.*;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.jpa_entities_service.NotificationService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;
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


//    @RequestMapping("/account/")
//    public String getPage(@SessionAttribute String login,
//                          @SessionAttribute String password,
//                          @RequestBody Optional<PersonalAccountPage<Notification>> optionalPage,
//                          Model model) {
//        @NonNull User user = userAuthorization.authorize(login, password);
//        PersonalAccountPage<Notification> page = optionalPage.orElseGet(PersonalAccountPage::new);
//        personalAccountService.setAccountPageState(page, user);
//        model.addAttribute("stateOfPage", page);
//        model.addAttribute("user", user);
//        return "personalAccount";
//    }

    @RequestMapping("/account/")
    public String getPage(@SessionAttribute String login,
                          @SessionAttribute String password,
                          @ModelAttribute PersonalAccountPage<Notification> stateOfPage,
                          Model model) {
        @NonNull User user = userAuthorization.authorize(login, password);
        personalAccountService.setAccountPageState(stateOfPage, user);
        model.addAttribute("stateOfPage", stateOfPage);
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
        Notification notification = notificationService.getNotification(Long.valueOf(id));
        Set<Action> actions = notification.getActions();
        model.addAttribute("user", user);
        model.addAttribute("actions", actions);
        model.addAttribute("notification", notification);
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
            @ModelAttribute Notification notification,
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
            @ModelAttribute Notification notification) {
        User user = userAuthorization.authorize(login, password);
        personalAccountService.addNotification(notification, user);
        return "redirect:/account/";
    }

    @RequestMapping("/account/notification_info/")
    public String getNotificationPage(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam String id,
            Model model,
            Action action) {
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
            @NonNull @ModelAttribute Action action) {
        User userImplementor = userAuthorization.authorize(login, password);
        personalAccountService.addAction(userImplementor, action);
        return "redirect:/account/";
    }
}
