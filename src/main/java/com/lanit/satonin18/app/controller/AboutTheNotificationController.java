package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.dto.CommonDefaultVar;
import com.lanit.satonin18.app.dto.notification_app.AboutTheNotificationDto;
import com.lanit.satonin18.app.dto.notification_app.AboutTheNotificationState;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.no_in_db.ActionType;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.service.app_service.AboutTheNotificationService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller("aboutTheNotificationController")
@RequestMapping("/cabinet/about_the_notification")
public class AboutTheNotificationController {
    @Autowired
    private AboutTheNotificationService aboutTheNotificationService;

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    private void addAttributes_Action(Model model, AboutTheNotificationState state, User currentUser, Notification currentNotification) {
        model.addAttribute("state", state);

        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("selectShowListMaxResult", CommonDefaultVar.selectShowListMaxResult);
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()) );
        model.addAttribute("listStatus", Arrays.asList(Status.values()) );
    }

    @GetMapping("/selectTheNotification")
    public String selectTheNotification(
            @RequestParam int notificationId,
            @RequestParam int userId,
            HttpSession session, Model model,
            final RedirectAttributes redirectAttributes) {
        session.setAttribute("currentNotification", notificationId); //todo добавляем без проверки
        return "redirect:/cabinet/about_the_notification/actions";
    }

    @GetMapping("/actions")
    public String actions(
                          @ModelAttribute(value = "aboutTheNotificationDto") AboutTheNotificationDto dto,
                          HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("user");
        Integer notificationId = (Integer) session.getAttribute("currentNotification");
        if(userId == null || notificationId == null) return "redirect:/";
        User currentUser = userService.findById(userId);
        Notification currentNotification = notificationService.findById(notificationId);
//------------------------------------------------
//        dto.setPage(dto.getPage()-1);
        if(dto.isFlagNeedSetFirstPage() ) dto.setPage(CommonDefaultVar.FIRST_PAGE);

        AboutTheNotificationState state = new AboutTheNotificationState(dto);
        aboutTheNotificationService.executeQuery(state, currentNotification);
//------------------------------------------------
        addAttributes_Action(model, state, currentUser, currentNotification);
        return "cabinet/about_the_notification/actions";
    }


}
