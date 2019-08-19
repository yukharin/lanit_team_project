package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.dto.FilterDto;
import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.cabinet.*;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.property_in_future.DEFAULT_CABINET_VARS;
import com.lanit.satonin18.app.service.app_service.CabinetService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            @ModelAttribute(value = "filterDto") FilterDto dto){
        validateAndSetDefaultVars(dto);
        
        CabinetState state = (CabinetState)  session.getAttribute("cabinetState");

        state.setFilterDto(dto);
        state.getPaginationDto().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/pagination")
    public String pagination(
            HttpSession session,
            @ModelAttribute(value = "paginationDto") PaginationDto dto){
        validateAndSetDefaultVars(dto);

        CabinetState state = (CabinetState)  session.getAttribute("cabinetState");

        state.setPaginationDto(dto);
        if(state.getPaginationDto().getMaxResult() != dto.getMaxResult())
            state.getPaginationDto().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/orderby")
    public String orderby(
            HttpSession session,
            @ModelAttribute(value = "orderByDto") OrderByDto dto){
        validateAndSetDefaultVars(dto);

        CabinetState state = (CabinetState)  session.getAttribute("cabinetState");

        state.setOrderByDto(dto);
        state.getPaginationDto().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

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

        CabinetState state = (CabinetState)  session.getAttribute("cabinetState");
        if(state == null){
            state = createNewDefault4watchCabinetState();
            session.setAttribute("cabinetState", state);
        }
        Cabinet4renderHtml render = new Cabinet4renderHtml(state);
        cabinetService.executeQuery(render, currentUser);

        addAttributes_Notification(model, currentUser, render);
        return "cabinet/notifications";
    }

    private CabinetState createNewDefault4watchCabinetState() {
        FilterDto filterDto = new FilterDto();
        PaginationDto paginationDto = new PaginationDto();
        OrderByDto orderByDto = new OrderByDto();

        validateAndSetDefaultVars(filterDto);
        filterDto.setIdFilterStatus(Status.getAllId());
        validateAndSetDefaultVars(paginationDto);
        validateAndSetDefaultVars(orderByDto);

        CabinetState state = new CabinetState();
        state.setFilterDto(filterDto);
        state.setPaginationDto(paginationDto);
        state.setOrderByDto(orderByDto);
        return state;
    }


    private void validateAndSetDefaultVars(FilterDto dto) {
        if(dto.getIdFilterStatus() == null) dto.setIdFilterStatus(Collections.EMPTY_LIST);//если ничего передали, значит пусто
        if(dto.getShowArchive() == null) dto.setShowArchive(DEFAULT_CABINET_VARS.SHOW_ARCHIVE);//если параметр не пришел, то false, если пришел( то приходит только true)
    }
    private void validateAndSetDefaultVars(PaginationDto dto) {
        if(dto.getMaxResult() == null) dto.setMaxResult(COMMON_DEFAULT_VARS.MAX_RESULT);
        if(dto.getPage() == null) dto.setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);
    }
    private void validateAndSetDefaultVars(OrderByDto dto) {
        if(dto.getDesc() == null) dto.setDesc(COMMON_DEFAULT_VARS.DESC);
        if(dto.getOrderFieldName() == null) dto.setOrderFieldName(DEFAULT_CABINET_VARS.ORDER_FIELD_NAME);
    }
}
