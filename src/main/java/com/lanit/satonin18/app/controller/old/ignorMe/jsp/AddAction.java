package com.lanit.satonin18.app.controller.old.ignorMe.jsp;

import com.lanit.satonin18.app.objects.input.dto.valid.ActionPortionDto;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.entity.enum_type.ActionType;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Arrays;

@Controller("addActionController")
@RequestMapping("/cabinet/the_notification/add_action")
public class AddAction { //todo rename(add Controller)
    
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/formPage")
    public ModelAndView formPage(
            HttpSession session,
            @AuthenticationPrincipal UserAccount userAccount,
            Model model) {
        User currentUser = userAccount.getUser();
        Integer notificationId = (Integer) session.getAttribute("notificationId");
        Notification currentNotification = notificationService.findById(notificationId);

        addAttribute(model, currentUser, currentNotification);
        return new ModelAndView(
                "cabinet/the_notification/add_actionForm",
                "actionPortionDto",
                new ActionPortionDto());
    }

    @PostMapping("/save")
    public ModelAndView save(
            Model model, HttpSession session,
//            RedirectAttributes redir,
            @AuthenticationPrincipal UserAccount userAccount,
            @Valid @ModelAttribute(name = "actionPortionDto") ActionPortionDto actionPortionDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            redir.addAttribute("actionPortionDto", actionPortionDto);
            User currentUser = userAccount.getUser();
            Integer notificationId = (Integer) session.getAttribute("notificationId");
            Notification currentNotification = notificationService.findById(notificationId);

            addAttribute(model, currentUser, currentNotification);
            return new ModelAndView(
//                    "redirect:/cabinet/the_notification/add_action/register",
                    "cabinet/the_notification/add_actionForm",
                    "actionPortionDto",
                    actionPortionDto
            );
        }
        Notification currentNotification = notificationService.findById(actionPortionDto.getNotificationId());

        ActionType actionType = ActionType.getById(actionPortionDto.getIdActionType());
        User userImplementor = userService.findById(actionPortionDto.getIdUserImplementor());
        Status status = Status.getById(actionPortionDto.getIdNotificationStatus());

        long timeNow = System.currentTimeMillis();
        Timestamp now = new Timestamp(timeNow);

        Action actionNew = new Action();
        actionNew.setNotification(currentNotification);//can be add in inside: notification.getActions().add(THIS);
        actionNew.setActionType(actionType);
        actionNew.setUserByIdImplementor(userImplementor);
        actionNew.setStatusAfterProcessing(status);
        actionNew.setDate(now);
        actionNew.setContent(actionPortionDto.getContent());

        actionService.save(actionNew);

        currentNotification.getActions().add(actionNew);
        currentNotification.setStatus(actionNew.getStatusAfterProcessing());

        notificationService.save(currentNotification);//update

        return new ModelAndView(
                "redirect:/cabinet/the_notification/actions"
        );
    }

    private void addAttribute(Model model, User currentUser, Notification currentNotification) {
        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()));
        model.addAttribute("listStatus", Arrays.asList(Status.values()));
    }
}
