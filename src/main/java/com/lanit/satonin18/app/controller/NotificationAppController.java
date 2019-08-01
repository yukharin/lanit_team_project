package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.dto.Common_Default_var;
import com.lanit.satonin18.app.dao.CrudDAO;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.dto.notification_app.NotificationAppModel;
import com.lanit.satonin18.app.dto.notification_app.NotificationAppState;
import com.lanit.satonin18.app.entity.no_db.ActionType;
import com.lanit.satonin18.app.entity.no_db.Status;
import com.lanit.satonin18.app.service.app_service.NotificationAppService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.Arrays;

@Controller("notificationAppController")
@RequestMapping("/cabinet/about_the_notification")
public class NotificationAppController {
    //TODO take in sesion //can be store ID
    private User currentUser;
    private Notification currentNotification;
    private NotificationAppState state;

    @Autowired
    private NotificationAppService notificationAppService;

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
//    @Autowired
//    private CrudDAO<ActionType> actionTypeService;
//    @Autowired
//    private NotificationStatusService statusService;

    private void addAttributes_Action(Model model) {
        model.addAttribute("state", state);

        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("selectShowListMaxResult", Common_Default_var.selectShowListMaxResult);
//        model.addAttribute("listActionType", actionTypeService.list());
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()) );
//        model.addAttribute("listStatus", statusService.list());
        model.addAttribute("listStatus", Arrays.asList(Status.values()) );
    }

    @GetMapping("/selectTheNotification")
    public String selectTheNotification(
            @RequestParam("notificationId") int notificationId,
            @RequestParam("userId") int userId,
            Model model) {
        currentUser = userService.getById(userId);
        if(currentUser==null) return "redirect:/";

        currentNotification = notificationService.getById(notificationId);
//------------------------------------------------
//        NotificationAppModel notificationModel = new NotificationAppModel();
//        notificationModel.set#(#);
//
        state = new NotificationAppState();
//        state.setModel(notificationModel);

//        notificationAppService.initCommonVar4NotificationAppState(state, currentNotification);

//        addAttributes_Action(dto);
//        return "redirect:/cabinet/about_the_notification/current_state";
        return "redirect:/cabinet/about_the_notification/moreNew";
    }

    @GetMapping("/current_state")
    public String currentState(
            Model model) {
        if(currentUser==null) return "redirect:/";

        notificationAppService.executeQuery(state, currentNotification);

        addAttributes_Action(model);
        return "about_the_notification/form_more";
    }

    @GetMapping("/moreNew")
    public String moreNew(
//            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute(value = "NotificationModel") NotificationAppModel notificationModel,
            Model model){
        if(currentUser==null) return "redirect:/";

        if(notificationModel.isSelectedNewResultAndNeedSetFirstPage() ) notificationModel.setPage(1);

        state.setModel(notificationModel);

        notificationAppService.executeQuery(state, currentNotification);

        addAttributes_Action(model);
        return "about_the_notification/form_more";
    }


}
