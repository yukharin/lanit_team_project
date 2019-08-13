package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.no_in_db.ActionType;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller("addActionController")
@RequestMapping("/cabinet/the_notification/addAction")
public class AddAction {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/formPage")
    public String formPage(
            @RequestParam int notificationId,
            @RequestParam int userId,
            HttpSession session,
            Model model) {
        User currentUser = userService.findById(userId);
        Notification currentNotification = notificationService.findById(notificationId);
        if(currentUser == null || currentNotification == null) return "redirect:/"; //todo add alert(error)

        session.setAttribute("user", userId);
        session.setAttribute("notification", notificationId);

        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()));
        model.addAttribute("listStatus",Arrays.asList(Status.values()));
        return "cabinet/the_notification/add_action";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam int idActionType,
            @RequestParam String content,
            @RequestParam int idUserImplementor,
            @RequestParam int idNotificationStatus,
            HttpSession session,
            Model model) {
        Integer userId = (Integer) session.getAttribute("user");
        Integer notificationId = (Integer) session.getAttribute("notification");
        if(userId == null || notificationId == null) return "redirect:/"; //todo add alert( IT DONT SAVE)
        User currentUser = userService.findById(userId);
        Notification currentNotification = notificationService.findById(notificationId);
//------------------------------------------------
        ActionType actionType = ActionType.getById(idActionType);
        User userImplementor = userService.findById(idUserImplementor);
        Status status = Status.getById(idNotificationStatus);

        long timeNow = System.currentTimeMillis();

        Action actionNew = new Action();
        actionNew.setNotification(currentNotification);//can be add in inside: notification.getActions().add(THIS);
        actionNew.setActionType(actionType);
        actionNew.setUserByIdImplementor(userImplementor);
        actionNew.setStatusAfterProcessing(status);
        actionNew.setDate(new java.sql.Timestamp(timeNow));
        actionNew.setContent(content);

        actionService.save(actionNew);

        currentNotification.getActions().add(actionNew);
        currentNotification.setStatus(actionNew.getStatusAfterProcessing());

        notificationService.save(currentNotification);//update

        return "redirect:/cabinet/the_notification/actions";
    }
}
