package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.Action;
import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.ActionService;
import com.lanit.lkz_project.service.NotificationService;
import com.lanit.lkz_project.service.NotificationStatusService;
import com.lanit.lkz_project.service.OrganizationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    @GetMapping("/account/actions/")
    public String getNotificationActions(HttpServletRequest request, Model model) {
        Long notificationId = Long.valueOf(request.getParameter("id"));
        List<Action> actions = actionService.actionOfNotification(notificationId);
        model.addAttribute("actions", actions);
        return "notificationActions";
    }

    @PostMapping("/account/delete/")
    public String deleteNotification(HttpServletRequest request) {
        Long notificationId = Long.valueOf(request.getParameter("id"));
        notificationService.removeNotification(notificationId);
        return "redirect:/account";
    }

    @GetMapping("/account/addNotification/")
    public String getAddNotificationPage(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("organizations", organizations);
        return "addNotification";
    }

    @PostMapping("/account/addNotification/add")
    public String addNotification(@NotNull @RequestParam(name = "notificationType") String notificationType,
                                  @NotNull @RequestParam(name = "dateResponse") String dateResponse,
                                  @NotNull @RequestParam(name = "orgId") String orgId, HttpSession session) {
        try {
            @NonNull User user = (User) session.getAttribute("user");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date dateOfResponse = format.parse(dateResponse);
            Organization organization = organizationService.getOrganization(Long.valueOf(orgId));
            Notification notification = new Notification();
            notification.setOrganization(organization);
            notification.setNotificationType(notificationType);
            notification.setDateResponse(dateOfResponse);
            notification.setDateRecieved(new Date());
            notification.setStatus(notificationStatusService.getNotificationStatus(1));
            notificationService.addNotification(notification);
            System.err.println("!!!!!" + organization.getNotifications().size());
        } catch (ParseException e
        ) {
            e.printStackTrace();
        }
        return "personalAccount";
    }


}
