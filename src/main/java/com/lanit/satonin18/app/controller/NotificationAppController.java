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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Controller("notificationAppController")
@RequestMapping("/cabinet/about_the_notification")
public class NotificationAppController {
    @Autowired
    private NotificationAppService notificationAppService;

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    private void addAttributes_Action(Model model, NotificationAppState state, User currentUser, Notification currentNotification) {
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
            @RequestParam int notificationId,
            @RequestParam int userId,
            HttpSession session, Model model,
            final RedirectAttributes redirectAttributes) {
        session.setAttribute("user", userId); //todo добавляем без проверки
        session.setAttribute("notification", notificationId);
        return "redirect:/cabinet/about_the_notification/moreNew";
    }

    //было решено не сохранять настройки этой страницы в сессии
    @GetMapping("/moreNew")
    public String moreNew(
                          @ModelAttribute(value = "notificationModel") NotificationAppModel notificationModel,
                          HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("user");
        Integer notificationId = (Integer) session.getAttribute("notification");
        if(userId == null || notificationId == null) return "redirect:/"; //todo add alert( IT DONT SAVE)
        User currentUser = userService.getById(userId);
        Notification currentNotification = notificationService.getById(notificationId);
//------------------------------------------------
        if(notificationModel.isSelectedNewResultAndNeedSetFirstPage() ) notificationModel.setPage(1);

        NotificationAppState state = new NotificationAppState(notificationModel);
        notificationAppService.executeQuery(state, currentNotification);
//------------------------------------------------
        addAttributes_Action(model, state, currentUser, currentNotification);
        return "about_the_notification/form_more";
    }


}
