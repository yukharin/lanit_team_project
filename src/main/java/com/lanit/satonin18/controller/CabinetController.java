package com.lanit.satonin18.controller;

import com.lanit.satonin18.model.Notification;
import com.lanit.satonin18.model.NotificationStatus;
import com.lanit.satonin18.model.User;
import com.lanit.satonin18.service.CrudService;
import com.lanit.satonin18.service.NotificationService;
import com.lanit.satonin18.service.NotificationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.*;

//@Scope("session")
//@SessionAttributes(value = "orgNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController{

    public static final int NOTIFICATION_STATUS_PROCESSED = 7;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CrudService<User> userService;
    @Autowired
    private NotificationStatusService notificationStatusService;

    //TODO filter no go in db (only minimize counts)
    //todo IT VERY VERY BAD, need Session
    private User currentUser;
    private List<NotificationStatus> checkedNotificStatuses;
    private boolean flagArchive;
    private boolean flagTimeFilter;
    private List<Notification> orgNotifications;
    private List<NotificationStatus> allNotificationStatuses;
    private List<Notification> filterNotifications;

    private void addAllAttributes(Model model, List<Notification> notifications) {
        model.addAttribute("user", currentUser);
        model.addAttribute("user_list", userService.list());

        model.addAttribute("notificStatus_list", notificationStatusService.list());
        model.addAttribute("checked_list", checkedNotificStatuses);
        model.addAttribute("flagArchive", flagArchive);

        model.addAttribute("notific_list", notifications);
    }

    @GetMapping("/")
    public String list(Model model) {
        currentUser = userService.getById(9);//for test
        allNotificationStatuses = notificationStatusService.list();
        checkedNotificStatuses = notificationStatusService.list();
        flagArchive = true;
        orgNotifications = currentUser.getOrganization().getNotifications();

        addAllAttributes(model, orgNotifications);
        return "cabinet/list";
    }

    @PostMapping("/selectUser")
    public String selectUser(@RequestParam("idSelectUser") int idSelectUser,
                             Model model) {
        currentUser = userService.getById(idSelectUser);
        checkedNotificStatuses = notificationStatusService.list();
        flagArchive = true;
        orgNotifications = currentUser.getOrganization().getNotifications();

        addAllAttributes(model, orgNotifications);
        return "cabinet/list";
    }

    //filter, no go in db (only minimize counts)
    @GetMapping("/filterByNotificStatus")
    public String filterByNotificStatus(HttpServletRequest request, HttpServletResponse response,
//                                        @RequestParam("id") int[] id,
//                                        @RequestParam("flagArchive") boolean theFlagArchive,
                                        Model model) {
        filterNotifications = new ArrayList<>();
        checkedNotificStatuses = new ArrayList<>();

        String[] ids = request.getParameterValues("id");
        String[] masflagArchive = request.getParameterValues("flagArchive");

        if(ids == null || currentUser == null) {
        }else {
            filterCheckedStatus(ids);
            filterArchives(masflagArchive);
        }
        addAllAttributes(model, filterNotifications);
        return "cabinet/list";
    }

    private void filterCheckedStatus(String[] ids) {
        filterNotifications = new ArrayList<Notification>(orgNotifications);
        //checkedNotificStatuses = notificationStatusService.filterIds(ids);//MOCK//notificationStatusService.list();
        for (int iNot = 0; iNot < allNotificationStatuses.size(); iNot++) {
            NotificationStatus ns = allNotificationStatuses.get(iNot);
            for (int i = 0; i < ids.length; i++) {
                if (ns.getId() == Integer.parseInt(ids[i])) {
                    checkedNotificStatuses.add(ns);
                    break;
                }
            }
        }
//            List<Notification> notificationsFilterStatus = notificationService.filterCurrentsAndNotificStatuses(orgNotifications,/*ids*/ /*String[] ids*/ listCheckedNotificStatus);
//            List<Notification> notificationsFilterStatus = notificationService.filterOrgAndNotificStatuses(currentUser.getOrganization(), checkedNotificStatuses);
//            orgNotifications = notificationsFilterStatus;
        for (int i = 0; i < orgNotifications.size(); i++) {
            Notification not = orgNotifications.get(i);
            if (!checkedNotificStatuses.contains(not.getNotificationStatus())) {
                filterNotifications.remove(not);
            }
        }
    }

    private void filterArchives(String[] masflagArchive) {
        if (masflagArchive != null) {
            flagArchive = true;
        } else {
            flagArchive = false;

            List<Notification> temp = new ArrayList<>(filterNotifications);
            for (Notification not : temp ) {
                if (not.getNotificationStatus().getId() == NOTIFICATION_STATUS_PROCESSED) {
                    filterNotifications.remove(not);
                }
            }
        }
    }

}