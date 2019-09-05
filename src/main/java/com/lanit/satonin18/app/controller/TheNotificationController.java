package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.objects.output.TheNotification4renderHtml;
import com.lanit.satonin18.app.objects.value_object.the_notification.ColumnTheNotificationTable;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.enum_type.ActionType;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.state4session.TheNotificationSessionState;
import com.lanit.satonin18.app.objects.property_in_future.DEFAULT_THE_NOTIFICATION_VARS;
import com.lanit.satonin18.app.service.app_service.TheNotificationService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller("theNotificationController")
@RequestMapping("/cabinet/the_notification")
public class TheNotificationController {

    @Autowired
    private TheNotificationService theNotificationService;

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    private void addAttributes_Action(Model model, TheNotification4renderHtml render, User currentUser, Notification currentNotification) {
        model.addAttribute("render", render);

        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("selectShowListMaxResult", COMMON_DEFAULT_VARS.selectShowListMaxResult);
        model.addAttribute("listActionType", Arrays.asList(ActionType.values()) );
        model.addAttribute("listStatus", Arrays.asList(Status.values()) );

        model.addAttribute("columnTable", ColumnTheNotificationTable.values());
    }

    @GetMapping("/selectTheNotification")
    public String selectTheNotification(
            @RequestParam int notificationId,
            HttpSession session, Model model,
            final RedirectAttributes redirectAttributes) {
        session.setAttribute("notificationId", notificationId); //todo добавляем без проверки

        PaginationForm paginationForm = new PaginationForm();
        OrderByForm orderByForm = new OrderByForm();

        validateAndSetDefaultVars(paginationForm);
        validateAndSetDefaultVars(orderByForm);

        TheNotificationSessionState state = new TheNotificationSessionState();
        state.setPaginationForm(paginationForm);
        state.setOrderByForm(orderByForm);

        session.setAttribute("theNotificationState", state);//remove prev
        return "redirect:/cabinet/the_notification/actions";
    }
    @PostMapping("/pagination")
    public String pagination(HttpSession session,
                             @ModelAttribute(value = "paginationDto") PaginationForm dto){
        validateAndSetDefaultVars(dto);

        TheNotificationSessionState state = (TheNotificationSessionState)  session.getAttribute("theNotificationState");

        state.setPaginationForm(dto);
        if(state.getPaginationForm().getMaxResult() != dto.getMaxResult())
            state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("theNotificationState", state);
        return "redirect:/cabinet/the_notification/actions";
    }

    @PostMapping("/orderby")
    public String orderby(HttpSession session,
                          @ModelAttribute(value = "orderByDto") OrderByForm dto){
        validateAndSetDefaultVars(dto);

        TheNotificationSessionState state = (TheNotificationSessionState)  session.getAttribute("theNotificationState");

        state.setOrderByForm(dto);
        state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("theNotificationState", state);
        return "redirect:/cabinet/the_notification/actions";
    }

    @GetMapping("/actions")
    public String actions(
            HttpSession session,
            @AuthenticationPrincipal UserAccount userAccount,
            Model model){
        User currentUser = userAccount.getUser();

        Integer notificationId = (Integer) session.getAttribute("notificationId");

        Notification currentNotification = notificationService.findById(notificationId);

        TheNotificationSessionState state = (TheNotificationSessionState)  session.getAttribute("theNotificationState");
        TheNotification4renderHtml render = new TheNotification4renderHtml(state, currentUser, currentNotification);
        theNotificationService.executeQuery(render, currentNotification);

        addAttributes_Action(model, render, currentUser, currentNotification);
        return "cabinet/the_notification/actionsForm";
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
