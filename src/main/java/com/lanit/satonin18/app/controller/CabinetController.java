package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.dto.FilterDto;
import com.lanit.satonin18.app.dto.OrderByDto;
import com.lanit.satonin18.app.dto.PaginationDto;
import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.cabinet.*;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.property_in_future.DEFAULT_CABINET_VARS;
import com.lanit.satonin18.app.service.app_service.CabinetService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    }

    @GetMapping("/selectUser")
    public String selectUser(
            @RequestParam int idSelectUser,
            HttpSession session, Model model,
            final RedirectAttributes redirectAttributes){
        session.setAttribute("user", idSelectUser); //todo добавляем без проверки

        CabinetState state = (CabinetState)  session.getAttribute("cabinetState");
        if(state == null){
            FilterDto filterDto = new FilterDto();
            PaginationDto paginationDto = new PaginationDto();
            OrderByDto orderByDto = new OrderByDto();

            validateAndSetDefaultVars(filterDto);
            filterDto.setIdFilterStatus(Status.getAllId());
            validateAndSetDefaultVars(paginationDto);
            validateAndSetDefaultVars(orderByDto);

            state = new CabinetState();
            state.setFilterDto(filterDto);
            state.setPaginationDto(paginationDto);
            state.setOrderByDto(orderByDto);

            session.setAttribute("cabinetState", state);
        }
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/filters")
    public String filters(
            HttpServletRequest request,
            HttpSession session,
            @ModelAttribute(value = "filterDto") FilterDto dto,
            Model model){
        validateAndSetDefaultVars(dto);
        
        CabinetState state = (CabinetState)  session.getAttribute("cabinetState");

        state.setFilterDto(dto);
        state.getPaginationDto().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/pagination")
    public String pagination(
            HttpServletRequest request,
            HttpSession session,
            @ModelAttribute(value = "paginationDto") PaginationDto dto,
            Model model){
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
            HttpServletRequest request,
            HttpSession session,
            @ModelAttribute(value = "orderByDto") OrderByDto dto,
            Model model){
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
    //todo heed POST
    @GetMapping("/deleteTheNotification")
    public String deleteTheNotification(
            Integer notificationId){
        notificationService.deleteById(notificationId); //todo not validate
        return "redirect:/cabinet/notifications";
    }
    
    @GetMapping("/notifications")
    public String notifications(
            HttpSession session,
            Model model){
        Integer userId = (Integer) session.getAttribute("user");
        if( userId == null) return "redirect:/";
        User currentUser = userService.findById(userId);

        CabinetState state = (CabinetState)  session.getAttribute("cabinetState");
        Cabinet4renderHtml render = new Cabinet4renderHtml(state);
        cabinetService.executeQuery(render, currentUser);

        addAttributes_Notification(model, currentUser, render);
        return "cabinet/notifications";
    }

    private void validateAndSetDefaultVars(FilterDto dto) {
        if(dto.getIdFilterStatus() == null) dto.setIdFilterStatus(Collections.EMPTY_LIST);//если ничего передали, значит пусто
        if(dto.getShowArchive() == null) dto.setShowArchive(DEFAULT_CABINET_VARS.SHOW_ARCHIVE);//если параметр не пришел, то false, если пришел( то приходит только true)
    }
    private void validateAndSetDefaultVars(PaginationDto dto/*, PaginationDto prevDto*/) {
        if(dto.getMaxResult() == null) dto.setMaxResult(COMMON_DEFAULT_VARS.MAX_RESULT);
        if(dto.getPage() == null) dto.setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);
    }
    private void validateAndSetDefaultVars(OrderByDto dto) {
        if(dto.getDesc() == null) dto.setDesc(COMMON_DEFAULT_VARS.DESC);
        if(dto.getOrderFieldName() == null) dto.setOrderFieldName(DEFAULT_CABINET_VARS.ORDER_FIELD_NAME);
    }
}
