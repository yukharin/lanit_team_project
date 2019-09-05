package com.lanit.satonin18.app.controller.rest.cross;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@Controller("angularTheNotificationController")
@CrossOrigin(origins = "http://localhost:4200")
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
            @RequestParam int notificationId,
            HttpSession session,
            @RequestBody PaginationForm form) throws JsonProcessingException {
        validateAndSetDefaultVars(form);

        TheNotificationSessionState state = (TheNotificationSessionState)  session.getAttribute("theNotificationState");
        if (state == null) {
//            OrderByForm orderByForm = new OrderByForm();
//            validateAndSetDefaultVars(orderByForm);
//
//            state = new TheNotificationSessionState();
//            state.setOrderByForm(orderByForm);
            state = createNewDefault4watchTheNotificationState();
//            session.setAttribute("cabinetState", state);
        }
        state.setPaginationForm(form);
        if(state.getPaginationForm().getMaxResult() != form.getMaxResult())
            state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("theNotificationState", state);

        Integer userId = (Integer) session.getAttribute("userId");
//        return theNotification4renderHtml(userId, session);
        return theNotification4renderHtml(notificationId, session);
//        return "OK";
    }

//    @RequestMapping("/get_user")
//    public /*@ResponseBody*/ User get_user() throws JsonProcessingException {
//        User user = userService.findById(1);
//
//        testOnBuildJson(user);
//
//        return user;
//    }

    @RequestMapping("/theNotification4renderHtml")
    public /*@ResponseBody*/ TheNotification4renderHtml theNotification4renderHtml(
            @RequestParam int notificationId,
            HttpSession session) throws JsonProcessingException {
        User currentUser = userService.findById(1);
//        session.setAttribute("userId", userId);

//        Integer notificationId = (Integer) session.getAttribute("notificationId");
        Notification currentNotification = notificationService.findById(notificationId);

        TheNotificationSessionState state = (TheNotificationSessionState)  session.getAttribute("theNotificationState");
        if(state == null){
            state = createNewDefault4watchTheNotificationState();

        }
        TheNotification4renderHtml render = new TheNotification4renderHtml(state, currentUser, currentNotification);
        theNotificationService.executeQuery(render, currentNotification);

//        ArrayList<Integer> ids = new ArrayList<>();
//        for (Status status:render.getCheckedMainListNotificStatuses()) {
//            ids.add(status.getId());
//        }
//        render.setNewCheckedMainListNotificStatusesId(ids);

        testOnBuildJson(render);

//        addAttributes_Action(model, render, currentUser, currentNotification);
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

    private void testOnBuildJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(obj);
        System.err.println("JSON: " + str);
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

