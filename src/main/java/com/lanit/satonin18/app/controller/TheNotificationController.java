package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;
import com.lanit.satonin18.app.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.the_notification.TheNotification4renderHtml;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.no_in_db.ActionType;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.objects.the_notification.TheNotificationState;
import com.lanit.satonin18.app.property_in_future.DEFAULT_THE_NOTIFICATION_VARS;
import com.lanit.satonin18.app.service.app_service.TheNotificationService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
    }

    @GetMapping("/selectTheNotification")
    public String selectTheNotification(
            @RequestParam int notificationId,
            @RequestParam int userId,
            HttpSession session, Model model,
            final RedirectAttributes redirectAttributes) {
        session.setAttribute("currentNotification", notificationId); //todo добавляем без проверки

        PaginationDto paginationDto = new PaginationDto();
        OrderByDto orderByDto = new OrderByDto();

        validateAndSetDefaultVars(paginationDto);
        validateAndSetDefaultVars(orderByDto);

        TheNotificationState state = new TheNotificationState();
        state.setPaginationDto(paginationDto);
        state.setOrderByDto(orderByDto);

        session.setAttribute("theNotificationState", state);//remove prev
        return "redirect:/cabinet/the_notification/actions";
    }
    @PostMapping("/pagination")
    public String pagination(HttpServletRequest request,
                             HttpSession session,
                             @ModelAttribute(value = "paginationDto") PaginationDto dto,
                             Model model){
        validateAndSetDefaultVars(dto);

        TheNotificationState state = (TheNotificationState)  session.getAttribute("theNotificationState");

        state.setPaginationDto(dto);
        if(state.getPaginationDto().getMaxResult() != dto.getMaxResult())
            state.getPaginationDto().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("theNotificationState", state);
        return "redirect:/cabinet/the_notification/actions";
    }

    @PostMapping("/orderby")
    public String orderby(HttpServletRequest request,
                          HttpSession session,
                          @ModelAttribute(value = "orderByDto") OrderByDto dto,
                          Model model){
        validateAndSetDefaultVars(dto);

        TheNotificationState state = (TheNotificationState)  session.getAttribute("theNotificationState");

        state.setOrderByDto(dto);
        state.getPaginationDto().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("theNotificationState", state);
        return "redirect:/cabinet/the_notification/actions";
    }

    @GetMapping("/actions")
    public String actions(
            HttpSession session,
            Model model){
        Integer userId = (Integer) session.getAttribute("user");
        Integer notificationId = (Integer) session.getAttribute("currentNotification");
        if(userId == null || notificationId == null) return "redirect:/";
        User currentUser = userService.findById(userId);
        Notification currentNotification = notificationService.findById(notificationId);

        TheNotificationState state = (TheNotificationState)  session.getAttribute("theNotificationState");
        TheNotification4renderHtml render = new TheNotification4renderHtml(state);
        theNotificationService.executeQuery(render, currentNotification);

        addAttributes_Action(model, render, currentUser, currentNotification);
        return "cabinet/the_notification/actions";
    }

    private void validateAndSetDefaultVars(PaginationDto dto/*, PaginationDto prevDto*/) {
        if(dto.getMaxResult() == null) dto.setMaxResult(COMMON_DEFAULT_VARS.MAX_RESULT);
        if(dto.getPage() == null) dto.setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);
    }
    private void validateAndSetDefaultVars(OrderByDto dto) {
        if(dto.getDesc() == null) dto.setDesc(COMMON_DEFAULT_VARS.DESC);
        if(dto.getOrderFieldName() == null) dto.setOrderFieldName(DEFAULT_THE_NOTIFICATION_VARS.ORDER_FIELD_NAME);
    }
}
