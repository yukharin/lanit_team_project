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

import java.util.Arrays;

@Controller("addActionController")
@RequestMapping("/cabinet/about_the_notification/addAction")
public class AddAction {
    //----------------------------------------------------------------
    private User currentUser;
    private Notification currentNotification;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
//    @Autowired
//    private CrudDAO<ActionType> actionTypeService;
//    @Autowired
//    private NotificationStatusService statusService;
    @Autowired
    private OrganizationService organizationService;

    // todo need set page=1
    @GetMapping("/formPage")
    public String formPage(
            @RequestParam("notificationId") int notificationId,
            @RequestParam("userId") int userId,
            Model model) {
        currentUser = userService.getById(userId);
        currentNotification = notificationService.getById(notificationId);

        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
//        model.addAttribute("listActionType", actionTypeService.list());
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()));
//        model.addAttribute("listStatus", statusService.list());
        model.addAttribute("listStatus",Arrays.asList(Status.values()));
        return "add_action";
    }

        // todo need set page=1
    @GetMapping("/save")
    public String save(
            @RequestParam("idActionType") int idActionType,
            @RequestParam("content") String content,
            @RequestParam("idUserImplementor") int idUserImplementor,
            @RequestParam("idNotificationStatus") int idNotificationStatus,
            Model model) {

//------------------------------------------------
//        ActionType actionType = actionTypeService.getById(idActionType);
//        ActionType actionType = ActionType.values()[idActionType];
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
