package com.lanit.satonin18.app.controller.angular.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.entity.enum_type.ActionType;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.input.dto.valid.ActionPortionDto;
import com.lanit.satonin18.app.objects.output.AddAction4renderHtml;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import com.lanit.satonin18.app.utils.CheckOnBuildJson;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Controller("angularAddActionController")
@RequestMapping("/angular/cabinet/the_notification/add_action")
public class AddActionController {
    
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/canBeAdd")
    public boolean canBeAdd(
            @AuthenticationPrincipal UserAccount userAccount,
            HttpSession session,
            @RequestParam int notificationId,
            Model model) throws JsonProcessingException {
        User currentUser = userAccount.getUser();
        Notification currentNotification = notificationService.findById(notificationId);

        List<Status> statuses = new ArrayList<>();
        List<ActionType> actionTypes = new ArrayList<>();

        selectAvailableStatusAndActionType(currentNotification, statuses, actionTypes);

        if(statuses.isEmpty() || actionTypes.isEmpty()) return false;
        return true;
    }
    @GetMapping("/addAction4renderHtml")
    public AddAction4renderHtml formPage(
            @AuthenticationPrincipal UserAccount userAccount,
            HttpSession session,
            @RequestParam int notificationId,
            Model model) throws JsonProcessingException {
        User currentUser = userAccount.getUser();
        Notification currentNotification = notificationService.findById(notificationId);

        List<Status> statuses = new ArrayList<>();
        List<ActionType> actionTypes = new ArrayList<>();

        selectAvailableStatusAndActionType(currentNotification, statuses, actionTypes);

        AddAction4renderHtml render = new AddAction4renderHtml(
                currentUser,
                currentNotification,
                currentNotification.getOrganization().getUsers(),
                statuses,
                actionTypes
        );

        new CheckOnBuildJson().check(render);

        return render;
    }

    private void selectAvailableStatusAndActionType(Notification currentNotification, List<Status> statuses, List<ActionType> actionTypes) {
        switch( currentNotification.getStatus() ) {
            case NEW:
                statuses.addAll(Arrays.asList(
                        Status.IN_WORK
                ));
                actionTypes.addAll(Arrays.asList(
                        ActionType.SENDING_ANSWER,
                        ActionType.CONFORM_ANSWER,
                        ActionType.REJECT_ANSWER
                ));
                break;
            case IN_WORK:
                statuses.addAll(Arrays.asList(
                        Status.REJECTED,
                        Status.OK
                ));
                actionTypes.addAll(Arrays.asList(
                        ActionType.SENDING_ANSWER,
                        ActionType.CONFORM_ANSWER,
                        ActionType.REJECT_ANSWER
                ));
                break;
            default:
                /*NOP*/
                break;
        }
    }

    @PostMapping("/save")
    public boolean save(
            @AuthenticationPrincipal UserAccount userAccount,
            Model model, HttpSession session,
            @Valid @RequestBody ActionPortionDto actionPortionDto,
            BindingResult bindingResult) {
        User currentUser = userAccount.getUser();

        Notification currentNotification = notificationService.findById(actionPortionDto.getNotificationId());

        if (bindingResult.hasErrors()) {
            return false;
        }
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

        return true;
    }
}
