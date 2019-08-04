package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.dto.Common_Default_var;
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
        model.addAttribute("selectShowListMaxResult", Common_Default_var.selectShowListMaxResult);
        model.addAttribute("listFastFilter", Default_Cabinet_var.list4FastFilter);
    }

    @GetMapping("/selectUser")
    public String selectUser(
            @RequestParam int idSelectUser,
            HttpSession session, Model model,
            final RedirectAttributes redirectAttributes){
        session.setAttribute("user", idSelectUser); //todo добавляем без проверки
        return "redirect:/cabinet/notifications";
    }

    @GetMapping("/notifications")
    public String notifications(HttpServletRequest request,
                          HttpSession session,
                          @ModelAttribute(value = "cabinetDto") CabinetDto dto,
                          Model model){
        Integer userId = (Integer) session.getAttribute("user");
        if( userId == null) return "redirect:/";
        User currentUser = userService.getById(userId);
//------------------------------------------------
        if ( request.getParameterMap().isEmpty() ) {
            CabinetDto temp = (CabinetDto)  session.getAttribute("cabinetDto"+"#"+userId);
            if(temp != null) {
                dto = temp;
            } else {
                dto = new CabinetDto();
                dto.setIdFilterStatus( new ArrayList(Status.getAllId()) );
            }
        } else {
            if(dto.isFlagNeedSetFirstPage() )
                dto.setPage(1);
            if(dto.isFlagNeedReplaceStatus())
                replaceStatus(dto);
        }
//------------------------------------------------
        CabinetState state = new CabinetState(dto);
        cabinetService.executeQuery(state, currentUser);
//------------------------------------------------
        session.setAttribute("cabinetDto"+"#"+userId, dto);
        addAttributes_Notification(model, currentUser, state);
        return "cabinet/notifications";
    }

    private void replaceStatus(CabinetDto cabinetDto) {
        int idNotification = cabinetDto.getSelectedIdNotification4editStatus();
        int idNewStatus = cabinetDto.getSelectedNewIdStatus();

        cabinetService.editStatus(idNotification, idNewStatus);
    }
}
