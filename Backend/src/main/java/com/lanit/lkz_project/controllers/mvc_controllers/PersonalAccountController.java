package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.repositories.entitity_repositories.UserRepository;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.jpa_entities_service.NotificationService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/account")
public class PersonalAccountController {


    private static int counter = 0;

    {
        counter++;
        System.err.println("PersonalAccountController: " + counter);
    }


    @Value("${account_page}")
    private String account_page;
    @Value("${actions_history_page}")
    private String actions_history_page;
    @Value("${create_notification_page}")
    private String create_notification_page;
    @Value("${notification_info_page}")
    private String notification_info_page;
    @Value("${admin_page}")
    private String admin_page;
    @Value("${user_page}")
    private String user_page;


    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private PersonalAccountService personalAccountService;
    @Autowired
    private UserRepository userRepository;


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


//    @GetMapping("/notification{id}/history")
//    public ModelAndView getNotificationActions(
//            @PathVariable long id,
//            ModelAndView modelAndView) {
//        Notification notification = notificationService.getNotification(id);
//        Set<Action> actions = notification.getActions();
//        modelAndView.addObject("actions", actions);
//        modelAndView.addObject("notification", notification);
//        modelAndView.setViewName(actions_history_page);
//        return modelAndView;
//    }
//
//    @PostMapping("/delete")
//    public String deleteNotification(@AuthenticationPrincipal User user,
//                                     @RequestParam long id) {
//        notificationService.removeNotification(id);
//        log.info("user id: " + user.getId() + "deleted notification with id: " + id);
//        return "redirect:/account/";
//    }
//
//    @GetMapping("/addNotification")
//    public ModelAndView getAddNotificationPage(
//            @ModelAttribute Notification notification,
//            ModelAndView modelAndView) {
//        List<Organization> organizations = organizationService.organizations();
//        modelAndView.addObject("organizations", organizations);
//        modelAndView.setViewName(create_notification_page);
//        return modelAndView;
//    }
//
//    @PostMapping("/addNotification")
//    public ModelAndView addNotification(@AuthenticationPrincipal User user,
//                                        @Validated(value = NotificationValidationGroup.class) @ModelAttribute Notification notification,
//                                        BindingResult bindingResult,
//                                        ModelAndView modelAndView) {
//        if (bindingResult.hasErrors()) {
//            log.info("user with id: " + user.getId() + " passed wrong args: " + bindingResult);
//            List<Organization> organizations = organizationService.organizations();
//            modelAndView.addObject("organizations", organizations);
//            modelAndView.setViewName(create_notification_page);
//            return modelAndView;
//        } else {
//            personalAccountService.addNotification(notification, user);
//            log.info("user with id: " + user.getId()
//                    + " added notification with id: " + notification.getId());
//            modelAndView.setViewName("redirect:/account/");
//            return modelAndView;
//        }
//    }
//
//    @GetMapping("/notification{id}")
//    public ModelAndView getNotificationPage(
//            @PathVariable long id,
//            @ModelAttribute Action action,
//            ModelAndView modelAndView) {
//        Notification notification = notificationService.getNotification(id);
//        EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
//        modelAndView.addObject("notification", notification);
//        modelAndView.addObject("actionTypes", types);
//        modelAndView.setViewName(notification_info_page);
//        return modelAndView;
//    }
//
//    @PostMapping("/notification")
//    public ModelAndView addAction(
//            @AuthenticationPrincipal User user,
//            @Validated(value = ActionValidationGroup.class) @ModelAttribute Action action,
//            BindingResult bindingResult,
//            @RequestParam("notification.id") long id, ModelAndView modelAndView) {
//        if (bindingResult.hasErrors()) {
//            log.info("user with id: " + user.getId() + " passed wrong args: " + bindingResult);
//            Notification notification = notificationService.getNotification(id);
//            EnumSet<ActionType> types = personalAccountService.getAppropriateActions(notification);
//            modelAndView.addObject("notification", notification);
//            modelAndView.addObject("actionTypes", types);
//            modelAndView.setViewName(notification_info_page);
//            return modelAndView;
//        } else {
//            personalAccountService.addAction(user, action);
//            log.info("user with id: " + user.getId()
//                    + " added notification with id: " + action.getId());
//            modelAndView.setViewName("redirect:/account/");
//            return modelAndView;
//        }
//    }
//
//    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ModelAndView getAdminPage(
//            @AuthenticationPrincipal User user,
//            ModelAndView modelAndView) {
//        log.info("user with id: " + user.getId() + " requested admin page");
//        List<User> users = userRepository.findAll();
//        modelAndView.addObject("users", users);
//        modelAndView.setViewName(admin_page);
//        return modelAndView;
//    }
//
//
//    @GetMapping("/admin/user{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ModelAndView getUserInfo(ModelAndView modelAndView,
//                                    @PathVariable long id) {
//        User user = userRepository.findById(id).get();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName(user_page);
//        return modelAndView;
//    }

}
