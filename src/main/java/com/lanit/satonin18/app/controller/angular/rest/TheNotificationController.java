package com.lanit.satonin18.app.controller.angular.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.input.form.FilterForm;
import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import com.lanit.satonin18.app.objects.output.Cabinet4renderHtml;
import com.lanit.satonin18.app.objects.output.TheNotification4renderHtml;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.property_in_future.DEFAULT_CABINET_VARS;
import com.lanit.satonin18.app.objects.property_in_future.DEFAULT_THE_NOTIFICATION_VARS;
import com.lanit.satonin18.app.objects.state4session.CabinetSessionState;
import com.lanit.satonin18.app.objects.state4session.TheNotificationSessionState;
import com.lanit.satonin18.app.service.app_service.CabinetService;
import com.lanit.satonin18.app.service.app_service.TheNotificationService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import com.lanit.satonin18.app.utils.CheckOnBuildJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Controller("angularTheNotificationController")
@RequestMapping("/angular/cabinet/the_notification")
public class TheNotificationController {

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private TheNotificationService theNotificationService;

    @PostMapping("/pagination")
    public TheNotification4renderHtml pagination(
            @AuthenticationPrincipal UserAccount userAccount,
            @RequestParam int notificationId,
            HttpSession session,
            @RequestBody PaginationForm form) throws JsonProcessingException {
        validateAndSetDefaultVars(form);

        TheNotificationSessionState state = (TheNotificationSessionState)  session.getAttribute("theNotificationState");
        if (state == null) {
            state = createNewDefault4watchTheNotificationState();
        }
        state.setPaginationForm(form);
        if(state.getPaginationForm().getMaxResult() != form.getMaxResult())
            state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("theNotificationState", state);

        return theNotification4renderHtml(userAccount, notificationId, session);
    }

    @GetMapping("/theNotification4renderHtml")
    public /*@ResponseBody*/ TheNotification4renderHtml theNotification4renderHtml(
            @AuthenticationPrincipal UserAccount userAccount,
            @RequestParam int notificationId,
            HttpSession session) throws JsonProcessingException {
        User currentUser = userAccount.getUser();

        Notification currentNotification = notificationService.findById(notificationId);

        TheNotificationSessionState state = (TheNotificationSessionState)  session.getAttribute("theNotificationState");
        if(state == null){
            state = createNewDefault4watchTheNotificationState();
        }
        TheNotification4renderHtml render = new TheNotification4renderHtml(state, currentUser, currentNotification);
        theNotificationService.executeQuery(render, currentNotification);

        new CheckOnBuildJson().check(render);

        return render;
    }

    private TheNotificationSessionState createNewDefault4watchTheNotificationState() {
        PaginationForm paginationForm = new PaginationForm();
        OrderByForm orderByForm = new OrderByForm();

        validateAndSetDefaultVars(paginationForm);
        validateAndSetDefaultVars(orderByForm);

        TheNotificationSessionState state = new TheNotificationSessionState();
        state.setPaginationForm(paginationForm);
        state.setOrderByForm(orderByForm);
        return state;
    }

    private void validateAndSetDefaultVars(PaginationForm dto) {
        if(dto.getMaxResult() == null) dto.setMaxResult(COMMON_DEFAULT_VARS.MAX_RESULT);
        if(dto.getPage() == null) dto.setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);
    }
    private void validateAndSetDefaultVars(OrderByForm dto) {
        if(dto.getDesc() == null) dto.setDesc(COMMON_DEFAULT_VARS.DESC);
        if(dto.getOrderFieldName() == null) dto.setOrderFieldName(DEFAULT_THE_NOTIFICATION_VARS.ORDER_FIELD_NAME);
    }
}

