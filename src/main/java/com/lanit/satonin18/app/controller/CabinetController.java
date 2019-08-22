package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.objects.input.form.FilterForm;
import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.objects.output.Cabinet4renderHtml;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.property_in_future.DEFAULT_CABINET_VARS;
import com.lanit.satonin18.app.objects.state4session.CabinetSessionState;
import com.lanit.satonin18.app.service.app_service.CabinetService;
import com.lanit.satonin18.app.service.entities_service.*;
import com.lanit.satonin18.app.objects.value_object.cabinet.ColumnCabinetTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private UserService userService;
    @Autowired
    private CabinetService cabinetService;
    @Autowired
    private NotificationService notificationService;

    private void addAttributes_Notification(Model model, User currentUser, Cabinet4renderHtml render) {
        model.addAttribute("render", render);

        model.addAttribute("user", currentUser);
        model.addAttribute("selectShowListMaxResult", COMMON_DEFAULT_VARS.selectShowListMaxResult);
        model.addAttribute("listFastFilter", DEFAULT_CABINET_VARS.list4FastFilter);

        model.addAttribute("columnTable", ColumnCabinetTable.values());
    }

    @PostMapping("/filters")
    public String filters(
            HttpSession session,
            @ModelAttribute(value = "filterForm") FilterForm form){
        validateAndSetDefaultVars(form);
        
        CabinetSessionState state = (CabinetSessionState)  session.getAttribute("cabinetState");

        state.setFilterForm(form);
        state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/pagination")
    public String pagination(
            HttpSession session,
            @ModelAttribute(value = "paginationForm") PaginationForm form){
        validateAndSetDefaultVars(form);

        CabinetSessionState state = (CabinetSessionState)  session.getAttribute("cabinetState");

        state.setPaginationForm(form);
        if(state.getPaginationForm().getMaxResult() != form.getMaxResult())
            state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/orderby")
    public String orderby(
            HttpSession session,
            @ModelAttribute(value = "OrderByForm") OrderByForm form){
        validateAndSetDefaultVars(form);

        CabinetSessionState state = (CabinetSessionState)  session.getAttribute("cabinetState");

        state.setOrderByForm(form);
        state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/editStatus")
    public String editStatus(
            Boolean flagNeedReplaceStatus,
            Integer selectedIdNotification4editStatus,
            Integer selectedNewIdStatus){
        if(flagNeedReplaceStatus)
            cabinetService.editStatus(selectedIdNotification4editStatus, selectedNewIdStatus);
        return "redirect:/cabinet/notifications";
    }

    //todo need post
    @GetMapping("/deleteTheNotification")
    public String deleteTheNotification(
            int notificationId){
        notificationService.deleteById(notificationId); //todo not validate
        return "redirect:/cabinet/notifications";
    }


//    @GetMapping("{firstname}/{lastname}")
//    public String greeting(@PathVariable String firstname, @PathVariable String lastname) {
//        return "Hello, " + firstname + " " + lastname + "!";
//    }

    @GetMapping("/notifications")
    public String notifications(
            HttpSession session,
            @AuthenticationPrincipal UserAccount userAccount,
            Model model){
        User currentUser = userAccount.getUser();

        CabinetSessionState state = (CabinetSessionState)  session.getAttribute("cabinetState");
        if(state == null){
            state = createNewDefault4watchCabinetState();
            session.setAttribute("cabinetState", state);
        }
        Cabinet4renderHtml render = new Cabinet4renderHtml(state);
        cabinetService.executeQuery(render, currentUser);

        addAttributes_Notification(model, currentUser, render);
        return "cabinet/notificationsForm";
    }

    private CabinetSessionState createNewDefault4watchCabinetState() {
        FilterForm filterForm = new FilterForm();
        PaginationForm paginationForm = new PaginationForm();
        OrderByForm orderByForm = new OrderByForm();

        validateAndSetDefaultVars(filterForm);
        filterForm.setIdFilterStatus(Status.getAllId());
        validateAndSetDefaultVars(paginationForm);
        validateAndSetDefaultVars(orderByForm);

        CabinetSessionState state = new CabinetSessionState();
        state.setFilterForm(filterForm);
        state.setPaginationForm(paginationForm);
        state.setOrderByForm(orderByForm);
        return state;
    }


    private void validateAndSetDefaultVars(FilterForm form) {
        if(form.getIdFilterStatus() == null) form.setIdFilterStatus(Collections.EMPTY_LIST);//если ничего передали, значит пусто
        if(form.getShowArchive() == null) form.setShowArchive(DEFAULT_CABINET_VARS.SHOW_ARCHIVE);//если параметр не пришел, то false, если пришел( то приходит только true)
    }
    private void validateAndSetDefaultVars(PaginationForm form) {
        if(form.getMaxResult() == null) form.setMaxResult(COMMON_DEFAULT_VARS.MAX_RESULT);
        if(form.getPage() == null) form.setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);
    }
    private void validateAndSetDefaultVars(OrderByForm form) {
        if(form.getDesc() == null) form.setDesc(COMMON_DEFAULT_VARS.DESC);
        if(form.getOrderFieldName() == null) form.setOrderFieldName(DEFAULT_CABINET_VARS.ORDER_FIELD_NAME);
    }
}
