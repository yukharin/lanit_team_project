package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.no_db.ActionType;
import com.lanit.satonin18.app.entity.no_db.Status;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller("addActionController")
@RequestMapping("/cabinet/about_the_notification/addAction")
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
            @RequestParam("notificationId") int notificationId,
            @RequestParam("userId") int userId,
            HttpSession session, Model model) {
        User currentUser = userService.getById(userId);
        Notification currentNotification = notificationService.getById(notificationId);
        if(currentUser == null || currentNotification == null) return "redirect:/"; //todo add alert(error)

        session.setAttribute("user", userId);
        session.setAttribute("notification", notificationId);

        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()));
        model.addAttribute("listStatus",Arrays.asList(Status.values()));
        return "add_action";
    }

    @GetMapping("/save")
    public String save(
            @RequestParam("idActionType") int idActionType,
            @RequestParam("content") String content,
            @RequestParam("idUserImplementor") int idUserImplementor,
            @RequestParam("idNotificationStatus") int idNotificationStatus,
            HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("user");
        Integer notificationId = (Integer) session.getAttribute("notification");
        if(userId == null || notificationId == null) return "redirect:/"; //todo add alert( IT DONT SAVE)
        User currentUser = userService.getById(userId);
        Notification currentNotification = notificationService.getById(notificationId);
//------------------------------------------------
        ActionType actionType = ActionType.getById(idActionType);
        User userImplementor = userService.getById(idUserImplementor);
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
        //other logic app//currentNotification.setDateResponse(new java.sql.Date(timeNow));

        notificationService.saveOrUpdate(currentNotification);

        return "redirect:/cabinet/about_the_notification/current_state";
    }
}
