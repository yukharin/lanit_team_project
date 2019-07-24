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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
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


    @GetMapping("/account/")
    public String getPage(@NonNull @SessionAttribute String login,
                          @NonNull @SessionAttribute String password,
                          @RequestParam(required = false) String page,
                          @RequestParam(required = false) String size,
                          @RequestParam(required = false) String filterNew,
                          @RequestParam(required = false) String filterInProcessing,
                          @RequestParam(required = false) String filterApproved,
                          @RequestParam(required = false) String filterRejected,
                          @RequestParam(required = false) String timeFilter,
                          Model model,
                          HttpSession session) {
        @NonNull User user = userAuthorization.authorize(login, password);
        PersonalAccountPage<Notification> accountPage =
                (PersonalAccountPage<Notification>) session.getAttribute("stateOfPage");
        if (accountPage == null) {
            accountPage = new PersonalAccountPage<>();
        }
        personalAccountService.getPage(accountPage, user, page, size,
                filterNew, filterInProcessing, filterApproved, filterRejected, timeFilter);
        session.setAttribute("stateOfPage", accountPage);
        session.setAttribute("user", user);
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
