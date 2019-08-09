package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.dto.CommonDefaultVar;
import com.lanit.satonin18.app.dto.cabinet.*;
import com.lanit.satonin18.app.entity.no_in_db.Status;
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

//@Scope("session")
//@SessionAttributes(value = "showListNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {
    @Autowired
    private UserService userService;
    @Autowired
    private CabinetService cabinetService;

    private void addAttributes_Notification(Model model, User currentUser, CabinetState state) {
        model.addAttribute("state", state);

        model.addAttribute("user", currentUser);
        model.addAttribute("selectShowListMaxResult", CommonDefaultVar.selectShowListMaxResult);
        model.addAttribute("listFastFilter", DefaultCabinetVar.list4FastFilter);
    }

    @GetMapping("/selectUser")
    public String selectUser(
            @RequestParam int idSelectUser,
            HttpSession session, Model model,
            final RedirectAttributes redirectAttributes){
        session.setAttribute("user", idSelectUser); //todo добавляем без проверки
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/filters")
    public String filters(HttpServletRequest request,
                                HttpSession session,
                                @ModelAttribute(value = "cabinetDto") CabinetDto dto,//todo take only filter Var
                                Model model){
        setNullVarCabinetDtoOnInput(dto);//todo set only filter Var
        session.setAttribute("cabinetDto", dto);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/pagination")
    public String pagination(HttpServletRequest request,
                          HttpSession session,
                          @ModelAttribute(value = "cabinetDto") CabinetDto dto,//todo take only pagination Var
                          Model model){
        setNullVarCabinetDtoOnInput(dto);//todo set only pagination Var
        session.setAttribute("cabinetDto", dto);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/orderby")
    public String orderby(HttpServletRequest request,
                             HttpSession session,
                             @ModelAttribute(value = "cabinetDto") CabinetDto dto,//todo take only orderby Var
                             Model model){
        setNullVarCabinetDtoOnInput(dto);//todo set only orderby Var
        session.setAttribute("cabinetDto", dto);
        return "redirect:/cabinet/notifications";
    }

    @PostMapping("/editStatus")
    public String editStatus(HttpServletRequest request,
                             HttpSession session,
//                             2 vars
                             Model model){
//        setNullVarCabinetDtoOnInput(dto);//todo set only editStatus Var
//        session.setAttribute("cabinetDto", dto);
        return "redirect:/cabinet/notifications";
    }

    @GetMapping("/notifications")
    public String notifications(
//            HttpServletRequest request,
                          HttpSession session,
//                          @ModelAttribute(value = "cabinetDto") CabinetDto dto,
                          Model model){
        Integer userId = (Integer) session.getAttribute("user");
        if( userId == null) return "redirect:/";
        User currentUser = userService.findById(userId);

        CabinetDto dto = (CabinetDto)  session.getAttribute("cabinetDto");
        if(dto == null) {
            dto = new CabinetDto();
            dto.setIdFilterStatus( new ArrayList(Status.getAllId()) );
            setNullVarCabinetDtoOnInput(dto);
        }
//        dto.setPage(dto.getPage()-1);
//        if ( request.getParameterMap().isEmpty() ) {
//            CabinetDto temp = (CabinetDto)  session.getAttribute("cabinetDto");
//            if(temp != null) {
//                dto = temp;
//            } else {
//                dto = new CabinetDto();
//                dto.setIdFilterStatus( new ArrayList(Status.getAllId()) );
//            }
//        } else {
//            if(dto.getFlagNeedSetFirstPage() )
//                dto.setPage(CommonDefaultVar.FIRST_PAGE);
//            if(dto.getFlagNeedReplaceStatus())
//                replaceStatus(dto);
//        }
//------------------------------------------------
        CabinetState state = new CabinetState(dto);
        cabinetService.executeQuery(state, currentUser);
//------------------------------------------------
        addAttributes_Notification(model, currentUser, state);
        return "cabinet/notifications";
    }

    private void setNullVarCabinetDtoOnInput(CabinetDto dto) {
        if(dto.getMaxResult() == null) dto.setMaxResult(CommonDefaultVar.MAX_RESULT);
        if(dto.getPage() == null) dto.setPage(CommonDefaultVar.FIRST_PAGE);
        if(dto.getDesc() == null) dto.setDesc(CommonDefaultVar.DESC);
        if(dto.getOrderFieldName() == null) dto.setOrderFieldName(DefaultCabinetVar.ORDER_FIELD_NAME);
        if(dto.getFlagNeedSetFirstPage() == null) dto.setFlagNeedSetFirstPage(false);
        if(dto.getIdFilterStatus() == null) dto.setIdFilterStatus(Collections.EMPTY_LIST);//если ничего передали, значит пусто
        if(dto.getShowArchive() == null) dto.setShowArchive(DefaultCabinetVar.SHOW_ARCHIVE);//если параметр не пришел, то false, если пришел( то приходит только true)

        if(dto.getFlagNeedReplaceStatus() == null) dto.setFlagNeedReplaceStatus(false);
    }
    private void setNullVarFilterDto(FilterDto dto) {
        if(dto.getIdFilterStatus() == null) dto.setIdFilterStatus(Collections.EMPTY_LIST);//если ничего передали, значит пусто
        if(dto.getShowArchive() == null) dto.setShowArchive(DefaultCabinetVar.SHOW_ARCHIVE);//если параметр не пришел, то false, если пришел( то приходит только true)
    }

    private void replaceStatus(CabinetDto dto) {
        int idNotification = dto.getSelectedIdNotification4editStatus();
        int idNewStatus = dto.getSelectedNewIdStatus();

        cabinetService.editStatus(idNotification, idNewStatus);
    }
}
