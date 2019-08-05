package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.authorization.UserAuthorizationService;
import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.enums.ActionType;
import com.lanit.lkz_project.entities.jpa_entities.Action;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.jpa_entities_service.NotificationService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Controller
public class PersonalAccountController {

    private static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private PersonalAccountService personalAccountService;
    @Autowired
    private UserAuthorizationService userAuthorization;

//    JavaScript + JSON edition
//    @RequestMapping("/account/")
//    public String getPage(@SessionAttribute String login,
//                          @SessionAttribute String password,
//                          @RequestBody Optional<PersonalAccountPageDto<Notification>> optionalPage,
//                          Model model) {
//        @NonNull User user = userAuthorization.authorize(login, password);
//        PersonalAccountPageDto<Notification> page = optionalPage.orElseGet(PersonalAccountPageDto::new);
//        personalAccountService.setAccountPageState(page, user);
//        model.addAttribute("stateOfPage", page);
//        model.addAttribute("user", user);
//        return "personalAccount";
//    }

    @RequestMapping("/account/")
    public String getPage(@SessionAttribute String login,
                          @SessionAttribute String password,
                          @ModelAttribute PersonalAccountPageDto<Notification> stateOfPage,
                          Model model) {
        @NonNull User user = userAuthorization.authorize(login, password);
        personalAccountService.setAccountPageState(stateOfPage, user);
        model.addAttribute("stateOfPage", stateOfPage);
        model.addAttribute("user", user);
        logger.trace("added 2 attributes to model: stateOfPage - "
                + stateOfPage + " and user - " + user + " , then sending to personalAccount.html");
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
        logger.trace("added 3 attributes to model: user - " + user
                + ", actions: " + actions + ", notification - " + notification);
        return "notificationActions";
    }

    @PostMapping("/account/delete/")
    public String deleteNotification(@NonNull @RequestParam String id) {
        notificationService.removeNotification(Long.valueOf(id));
        logger.info("Removed notification with id: " + id + " , then sending to account.html");
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
        logger.trace("added 2 attributes to model: user - "
                + user + " and organizations - " + organizations + " , then sending to addNotification.html");
        return "addNotification";
    }

    @PostMapping("/account/addNotification/add/")
    public String addNotification(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @Valid @ModelAttribute Notification notification, BindingResult bindingResult, Model model) {
        User user = userAuthorization.authorize(login, password);
        if (bindingResult.hasErrors()) {
            logger.info("user: " + user + "passed wrong input to the form: "
                    + bindingResult.getAllErrors());
            model.addAttribute("user", user);
            List<Organization> organizations = organizationService.nonGovernmentOrganizations();
            model.addAttribute("organizations", organizations);
            logger.info("added 2 attributes to model: user - "
                    + user + " and organizations - " + organizations + " , then sending to addNotification.html");
            return "addNotification";
        } else {
            personalAccountService.addNotification(notification, user);
            logger.info("user: " + user + "added notification to the database, added notification - "
                    + notification + ", then redirect to account.html");
            return "redirect:/account/";
        }
    }

    @RequestMapping("/account/notification_info/")
    public String getNotificationPage(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam String id,
            @ModelAttribute Action action,
            Model model) {
        User user = userAuthorization.authorize(login, password);
        Notification notification = notificationService.getNotification(Long.valueOf(id));
        EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
        model.addAttribute("user", user);
        model.addAttribute("notification", notification);
        model.addAttribute("actionTypes", types);
        logger.trace("added 3 attributes to model: user - "
                + user + " and notification - " + notification
                + "and  actionTypes " + types + " then sending to notificationInfo.html");
        return "notificationInfo";
    }

    @PostMapping("/account/notification_info/commit/")
    public String addAction(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @Valid @NonNull @ModelAttribute Action action,
            BindingResult bindingResult,
            Model model) {
        User userImplementor = userAuthorization.authorize(login, password);
        Notification notification = notificationService.getNotification(action.getNotification().getId());
        logger.info("user: " + userImplementor + " passed input to the form");
        if (bindingResult.hasErrors()) {
            logger.info("user: " + userImplementor + "passed wrong input to the form: "
                    + bindingResult.getAllErrors());
            EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
            model.addAttribute("user", userImplementor);
            model.addAttribute("notification", notification);
            model.addAttribute("actionTypes", types);
            logger.info("added 3 attributes to model: user - "
                    + userImplementor + " and notification - " + notification
                    + "and  actionTypes " + types + " then sending to notificationInfo.html");
            return "notificationInfo";
        } else {
            personalAccountService.addAction(userImplementor, action);
            logger.info("user: " + userImplementor + " added action : "
                    + action + " to notification: " + notification + " , then redirecting to account.html");
            return "redirect:/account/";
        }
    }
}
