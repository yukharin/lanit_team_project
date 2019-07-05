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
import java.util.*;

//@Scope("session")
//@SessionAttributes(value = "orgNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {

    //todo remove somewhere
    public static final int ID_NOTIFICATION_STATUS_PROCESSED = 7;
    public static final int ID_NOTIFICATION_STATUS_WAITING_TO_BE_PROCESSED = 1;
    public static final int ID_NOTIFICATION_STATUS_ANSWER_NOT_ACCEPTED = 2;
    public static final int ID_NOTIFICATION_STATUS_ANSWER_SENT = 3;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CrudService<User> userService;
    @Autowired
    private NotificationStatusService statusService;

    //todo IT VERY VERY BAD, need Session
    private User currentUser;
    private List<NotificationStatus> checkedMainListNotificStatuses;
    private boolean showProcessed; //=  is NOTIFICATION_STATUS = PROCESSED
    private List<Notification> orgNotifications;
    private List<NotificationStatus> statuses4select;
    private List<Notification> showListNotifications;

    private void addAttributes_NotificationAndOther(Model model, List<Notification> notifications) {
        model.addAttribute("user", currentUser);
        model.addAttribute("user_list", userService.list());

        model.addAttribute("statuses4select", statuses4select);
        model.addAttribute("checked_list", checkedMainListNotificStatuses);
        model.addAttribute("showProcessed", showProcessed);

        Collections.sort(notifications, new Comparator<Notification>() {
            @Override
            public int compare(Notification o1, Notification o2) {
                return (o1.getDateResponse().before(o2.getDateResponse()))? 1 : -1;
            }
        });
        model.addAttribute("notific_list", notifications);
    }

    @GetMapping("/")
    public String list(Model model) {
        currentUser = userService.getById(9);//for test

        statuses4select = new ArrayList<>();
        statuses4select.add(
                statusService.getById(ID_NOTIFICATION_STATUS_WAITING_TO_BE_PROCESSED));
        statuses4select.add(
                statusService.getById(ID_NOTIFICATION_STATUS_ANSWER_NOT_ACCEPTED));
        statuses4select.add(
                statusService.getById(ID_NOTIFICATION_STATUS_ANSWER_SENT));
        checkedMainListNotificStatuses = statuses4select;

        showProcessed = false;
        orgNotifications = currentUser.getOrganization().getNotifications();

        addAttributes_NotificationAndOther(model, orgNotifications);
        return "cabinet/list";
    }

    @PostMapping("/selectUser")
    public String selectUser(@RequestParam("idSelectUser") int idSelectUser,
                             Model model) {
        currentUser = userService.getById(idSelectUser);
        checkedMainListNotificStatuses = statuses4select;
        showProcessed = false;
        orgNotifications = currentUser.getOrganization().getNotifications();

        addAttributes_NotificationAndOther(model, orgNotifications);
        return "cabinet/list";
    }

    @GetMapping("/filterByNotificStatus")
    public String filterByNotificStatus(HttpServletRequest request, HttpServletResponse response,
//                                        @RequestParam("id") int[] id,
//                                        @RequestParam("showProcessed") boolean theFlagArchive,
                                        Model model) {
        showListNotifications = new ArrayList<>();
        checkedMainListNotificStatuses = new ArrayList<>();
        List<NotificationStatus> checkedStatusList = new ArrayList<>();
        showProcessed = false;

        String[] ids = request.getParameterValues("id");
        boolean haveIdCheckedStatusList = (ids != null);

        String[] masShowProcessed = request.getParameterValues("showProcessed");
        boolean hasCheckedStatusProcessed = (masShowProcessed != null);

        if(haveIdCheckedStatusList || hasCheckedStatusProcessed){
            if (haveIdCheckedStatusList) {
                checkedMainListNotificStatuses = statusService.filterIds(ids);//MOCK//statuses4select;
                checkedStatusList.addAll(checkedMainListNotificStatuses);
            }
            if (hasCheckedStatusProcessed) {
                showProcessed = true;
                checkedStatusList.add(statusService.getById(ID_NOTIFICATION_STATUS_PROCESSED));
            } else {
                showProcessed = false;
            }
            showListNotifications = notificationService.filterOrgAndNotificStatuses(
                    currentUser.getOrganization(),
                    checkedStatusList);
        }
        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }
}