package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.dto.ActionPortionForm;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.entity.no_in_db.ActionType;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Arrays;

@Controller("addActionController")
@RequestMapping("/cabinet/the_notification/add_action")
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
    public ModelAndView formPage(
            @RequestParam int notificationId,
            @AuthenticationPrincipal UserAccount userAccount,
            Model model) {
        User currentUser = userAccount.getUser();
        Notification currentNotification = notificationService.findById(notificationId);

        if(currentNotification == null) return new ModelAndView("redirect:/"); //todo add alert(error)

        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()));
        model.addAttribute("listStatus",Arrays.asList(Status.values()));
        return new ModelAndView(
                "cabinet/the_notification/add_action",
                "actionPortionForm",
                new ActionPortionForm());
    }

    @PostMapping("/save")
    public ModelAndView save(
            Model model,
            @AuthenticationPrincipal UserAccount userAccount,
            @Valid @ModelAttribute(name = "actionPortionForm") ActionPortionForm actionPortionForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            User currentUser = userAccount.getUser();
            Notification currentNotification = notificationService.findById(actionPortionForm.getNotificationId());

            model.addAttribute("user", currentUser);
            model.addAttribute("currentNotification", currentNotification);
            model.addAttribute("listActionType", Arrays.asList(ActionType.values()));
            model.addAttribute("listStatus",Arrays.asList(Status.values()));

            return new ModelAndView(
                    "cabinet/the_notification/add_action",
                    "actionPortionForm",
                    actionPortionForm);

        }
        Notification currentNotification = notificationService.findById(actionPortionForm.getNotificationId());

        ActionType actionType = ActionType.getById(actionPortionForm.getIdActionType());
        User userImplementor = userService.findById(actionPortionForm.getIdUserImplementor());
        Status status = Status.getById(actionPortionForm.getIdNotificationStatus());

        long timeNow = System.currentTimeMillis();
        Timestamp now = new Timestamp(timeNow);

        Action actionNew = new Action();
        actionNew.setNotification(currentNotification);//can be add in inside: notification.getActions().add(THIS);
        actionNew.setActionType(actionType);
        actionNew.setUserByIdImplementor(userImplementor);
        actionNew.setStatusAfterProcessing(status);
        actionNew.setDate(now);
        actionNew.setContent(actionPortionForm.getContent());

        actionService.save(actionNew);

        currentNotification.getActions().add(actionNew);
        currentNotification.setStatus(actionNew.getStatusAfterProcessing());

        notificationService.save(currentNotification);//update

        return new ModelAndView(
                "redirect:/cabinet/the_notification/actions",
                "actionPortionForm",
                actionPortionForm);
    }
}
