package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.enums.ActionType;
import com.lanit.lkz_project.entities.jpa_entities.Action;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.entities.validation_groups.ActionValidationGroup;
import com.lanit.lkz_project.entities.validation_groups.NotificationValidationGroup;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.jpa_entities_service.NotificationService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/account")
public class PersonalAccountController {


    private static int counter = 0;

    {
        counter++;
        System.err.println("PersonalAccountController: " + counter);
    }

    private static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);

    @Value("${account_page}")
    private String account_page;
    @Value("${actions_history_page}")
    private String actions_history_page;
    @Value("${create_notification_page}")
    private String create_notification_page;
    @Value("${notification_info_page}")
    private String notification_info_page;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private PersonalAccountService personalAccountService;


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

    @GetMapping("/")
    public ModelAndView getPage(
            @ModelAttribute PersonalAccountPageDto<Notification> pageDTO,
            @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView(account_page);
        personalAccountService.setAccountPageState(pageDTO, user);
        modelAndView.addObject("pageDTO", pageDTO);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/notification_actions_history/")
    public ModelAndView getNotificationActions(
            @AuthenticationPrincipal User user,
            @NonNull @RequestParam Long id,
            ModelAndView modelAndView) {
        Notification notification = notificationService.getNotification(id);
        Set<Action> actions = notification.getActions();
        modelAndView.addObject("user", user);
        modelAndView.addObject("actions", actions);
        modelAndView.addObject("notification", notification);
        modelAndView.setViewName(actions_history_page);
        return modelAndView;
    }

    @PostMapping("/delete/")
    public String deleteNotification(@NonNull @RequestParam String id) {
        notificationService.removeNotification(Long.valueOf(id));
        return "redirect:/account/";
    }

    @GetMapping("/addNotification/")
    public ModelAndView getAddNotificationPage(
            @AuthenticationPrincipal User user,
            @ModelAttribute Notification notification,
            ModelAndView modelAndView) {
        modelAndView.addObject("user", user);
        List<Organization> organizations = organizationService.organizations();
        modelAndView.addObject("organizations", organizations);
        modelAndView.setViewName(create_notification_page);
        return modelAndView;
    }

    @PostMapping("/addNotification/")
    public ModelAndView addNotification(
            @AuthenticationPrincipal User user,
            @Validated(value = NotificationValidationGroup.class) @ModelAttribute Notification notification,
            BindingResult bindingResult,
            ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user", user);
            List<Organization> organizations = organizationService.organizations();
            modelAndView.addObject("organizations", organizations);
            modelAndView.setViewName(create_notification_page);
            return modelAndView;
        } else {
            personalAccountService.addNotification(notification, user);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/account/");
            return modelAndView;
        }
    }

    @GetMapping("/notification_info/")
    public ModelAndView getNotificationPage(
            @AuthenticationPrincipal User user,
            @NonNull @RequestParam Long id,
            @ModelAttribute Action action,
            ModelAndView modelAndView) {
        Notification notification = notificationService.getNotification(id);
        EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
        modelAndView.addObject("user", user);
        modelAndView.addObject("notification", notification);
        modelAndView.addObject("actionTypes", types);
        modelAndView.setViewName(notification_info_page);
        return modelAndView;
    }

    @PostMapping("/notification_info/")
    public ModelAndView addAction(
            @AuthenticationPrincipal User user,
            @Validated(value = ActionValidationGroup.class) @NonNull @ModelAttribute Action action,
            BindingResult bindingResult,
            ModelAndView modelAndView) {
        Notification notification = notificationService.getNotification(action.getNotification().getId());
        if (bindingResult.hasErrors()) {
            EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
            modelAndView.addObject("user", user);
            modelAndView.addObject("notification", notification);
            modelAndView.addObject("actionTypes", types);
            modelAndView.setViewName(notification_info_page);
            return modelAndView;
        } else {
            personalAccountService.addAction(user, action);
            modelAndView.setViewName("redirect:/account/");
            return modelAndView;
        }
    }

}
