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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
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

    @RequestMapping("/")
    public ModelAndView getPage(@SessionAttribute String login,
                                @SessionAttribute String password,
                                @ModelAttribute PersonalAccountPageDto<Notification> pageDTO) {
        @NonNull User user = userAuthorization.authorize(login, password);
        ModelAndView modelAndView = new ModelAndView(account_page);
        personalAccountService.setAccountPageState(pageDTO, user);
        modelAndView.addObject("pageDTO", pageDTO);
        modelAndView.addObject("user", user);
        logger.trace("added 2 attributes to model: pageDTO - "
                + pageDTO + " and user - " + user + " , then sending to personalAccount.html");
        return modelAndView;
    }

    @PostMapping("/notification_actions_history/")
    public ModelAndView getNotificationActions(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam Long id,
            ModelAndView modelAndView) {
        @NonNull User user = userAuthorization.authorize(login, password);
        Notification notification = notificationService.getNotification(id);
        Set<Action> actions = notification.getActions();
        modelAndView.addObject("user", user);
        modelAndView.addObject("actions", actions);
        modelAndView.addObject("notification", notification);
        modelAndView.setViewName(actions_history_page);
        logger.trace("added 3 attributes to model: user - " + user
                + ", actions: " + actions + ", notification - " + notification);
        return modelAndView;
    }

    @PostMapping("/delete/")
    public String deleteNotification(@NonNull @RequestParam String id) {
        notificationService.removeNotification(Long.valueOf(id));
        logger.info("Removed notification with id: " + id + " , then sending to account.html");
        return "redirect:/account/";
    }

    @GetMapping("/addNotification/")
    public ModelAndView getAddNotificationPage(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @ModelAttribute Notification notification,
            ModelAndView modelAndView) {
        @NonNull User user = userAuthorization.authorize(login, password);
        modelAndView.addObject("user", user);
        List<Organization> organizations = organizationService.nonGovernmentOrganizations();
        modelAndView.addObject("organizations", organizations);
        modelAndView.setViewName(create_notification_page);
        logger.trace("added 2 attributes to model: user - "
                + user + " and organizations - " + organizations + " , then sending to addNotification.html");
        return modelAndView;
    }

    @PostMapping("/addNotification/add/")
    public ModelAndView addNotification(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @Valid @ModelAttribute Notification notification,
            BindingResult bindingResult,
            ModelAndView modelAndView) {
        @NonNull User user = userAuthorization.authorize(login, password);
        if (bindingResult.hasErrors()) {
            logger.info("user: " + user + "passed wrong input to the form: "
                    + bindingResult.getAllErrors());
            modelAndView.addObject("user", user);
            List<Organization> organizations = organizationService.nonGovernmentOrganizations();
            modelAndView.addObject("organizations", organizations);
            modelAndView.setViewName(create_notification_page);
            logger.info("added 2 attributes to model: user - "
                    + user + " and organizations - " + organizations + " , then sending to addNotification.html");
            return modelAndView;
        } else {
            personalAccountService.addNotification(notification, user);
            logger.info("user: " + user + "added notificationDTO to the database, added notificationDTO - "
                    + notification + ", then redirect to account.html");
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/account/");
            return modelAndView;
        }
    }

    @RequestMapping("/notification_info/")
    public ModelAndView getNotificationPage(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @NonNull @RequestParam Long id,
            @ModelAttribute Action action,
            ModelAndView modelAndView) {
        @NonNull User user = userAuthorization.authorize(login, password);
        Notification notification = notificationService.getNotification(id);
        EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
        modelAndView.addObject("user", user);
        modelAndView.addObject("notification", notification);
        modelAndView.addObject("actionTypes", types);
        modelAndView.setViewName(notification_info_page);
        logger.trace("added 3 attributes to model: user - "
                + user + " and notification - " + notification
                + "and  actionTypes " + types + " then sending to notificationInfo.html");
        return modelAndView;
    }

    @PostMapping("/notification_info/commit/")
    public ModelAndView addAction(
            @NonNull @SessionAttribute String login,
            @NonNull @SessionAttribute String password,
            @Valid @NonNull @ModelAttribute Action action,
            BindingResult bindingResult,
            ModelAndView modelAndView) {
        @NonNull User userImplementor = userAuthorization.authorize(login, password);
        Notification notification = notificationService.getNotification(action.getNotification().getId());
        logger.info("user: " + userImplementor + " passed input to the form");
        if (bindingResult.hasErrors()) {
            logger.info("user: " + userImplementor + "passed wrong input to the form: "
                    + bindingResult.getAllErrors());
            EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
            modelAndView.addObject("user", userImplementor);
            modelAndView.addObject("notification", notification);
            modelAndView.addObject("actionTypes", types);
            modelAndView.setViewName(notification_info_page);
            logger.info("added 3 attributes to model: user - "
                    + userImplementor + " and notification - " + notification
                    + "and  actionTypes " + types + " then sending to notificationInfo.html");
            return modelAndView;
        } else {
            personalAccountService.addAction(userImplementor, action);
            modelAndView.setViewName("redirect:/account/");
            logger.info("user: " + userImplementor + " added action : "
                    + action + " to notification: " + notification + " , then redirecting to account.html");
            return modelAndView;
        }
    }

//
//    @InitBinder("dateResponse")
//    public void customizeBinding(WebDataBinder binder) {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormatter.setLenient(false);
//        binder.registerCustomEditor(LocalDate.class, "dateResponse",
//                new CustomDateEditor(dateFormatter, true));
//    }


    @PostConstruct
    private void print() {
        System.err.println("CONTROLLER!: " + this);
    }


}
