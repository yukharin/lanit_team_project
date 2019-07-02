package com.lanit.satonin18.controller;

import com.lanit.satonin18.model.Notification;
import com.lanit.satonin18.model.NotificationStatus;
import com.lanit.satonin18.model.Organization;
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
//@SessionAttributes(value = "currentNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController{

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CrudService<User> userService;
    @Autowired
    private NotificationStatusService notificationStatusService;

    //todo IT VERY VERY BAD, need Session
    private User currentUser;
    private List<NotificationStatus> checkedNotificStatuses;
    private boolean flagArchive;
    private List<Notification> currentNotifications;

    @GetMapping("/")
    public String list(Model model) {
        checkedNotificStatuses = notificationStatusService.list();
        currentNotifications = notificationService.list();

        addAttributes(model);
        return "cabinet/list";
    }

    private void addAttributes(Model model) {
        model.addAttribute("user", currentUser);
        model.addAttribute("user_list", userService.list());

        model.addAttribute("notificStatus_list", notificationStatusService.list());
        model.addAttribute("checked_list", checkedNotificStatuses);
        model.addAttribute("flagArchive", flagArchive);

        model.addAttribute("notific_list", currentNotifications);
    }

    @PostMapping("/selectUser")
    public String selectUser(@RequestParam("idSelectUser") int idSelectUser,
                             Model model) {
        User user = userService.getById(idSelectUser);
        Organization org = user.getOrganization();
//        List<Notification> notifications4Org = notificationService.getNotificationsByIdOrg(org.getId());
        List<Notification> notifications4Org = org.getNotifications();

        currentUser = user;
        checkedNotificStatuses = notificationStatusService.list();
        currentNotifications = notifications4Org;

        addAttributes(model);
        return "cabinet/list";
    }

    @PostMapping("/filterByNotificStatus")
    public String filterByNotificStatus(HttpServletRequest request, HttpServletResponse response,
//                                        @RequestParam("id") int[] id,
                                        Model model) {
        String[] ids = request.getParameterValues("id");

        if(ids == null || currentUser == null) {
            currentNotifications = Collections.EMPTY_LIST;
            checkedNotificStatuses = Collections.EMPTY_LIST;
        }else{
            List<NotificationStatus> listCheckedNotificStatus = notificationStatusService.filterIds(ids);//MOCK//notificationStatusService.list();
            checkedNotificStatuses = listCheckedNotificStatus;
            //List<Notification> notificationsFilterStatus = notificationService.filterCurrentsAndNotificStatuses(currentNotifications,/*ids*/ /*String[] ids*/ listCheckedNotificStatus);
            List<Notification> notificationsFilterStatus = notificationService.filterOrgAndNotificStatuses(currentUser.getOrganization(), listCheckedNotificStatus);
            currentNotifications = notificationsFilterStatus;
        }
        addAttributes(model);
        return "cabinet/list";
    }
}