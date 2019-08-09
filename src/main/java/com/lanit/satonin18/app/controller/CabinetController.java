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

    private void addAttributes_Notification(Model model, User currentUser, CabinetStateOnOutput state) {
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
                          @ModelAttribute(value = "cabinetDto") CabinetDtoOnInput dto,
                          Model model){
        Integer userId = (Integer) session.getAttribute("user");
        if( userId == null) return "redirect:/";
        User currentUser = userService.findById(userId);
//------------------------------------------------
//        dto.setPage(dto.getPage()-1);
        if ( request.getParameterMap().isEmpty() ) {
            CabinetDtoOnInput temp = (CabinetDtoOnInput)  session.getAttribute("cabinetDto"+"#"+userId);
            if(temp != null) {
                dto = temp;
            } else {
                dto = new CabinetDtoOnInput();
                dto.setIdFilterStatus( new ArrayList(Status.getAllId()) );
            }
        } else {
            if(dto.isFlagNeedSetFirstPage() )
                dto.setPage(Common_Default_var.PAGE);
            if(dto.isFlagNeedReplaceStatus())
                replaceStatus(dto);
        }
//------------------------------------------------
        CabinetStateOnOutput state = new CabinetStateOnOutput(dto);
        cabinetService.executeQuery(state, currentUser);
//------------------------------------------------
        session.setAttribute("cabinetDto"+"#"+userId, dto);
        addAttributes_Notification(model, currentUser, state);
        return "cabinet/notifications";
    }

    private void replaceStatus(CabinetDtoOnInput dto) {
        int idNotification = dto.getSelectedIdNotification4editStatus();
        int idNewStatus = dto.getSelectedNewIdStatus();

        cabinetService.editStatus(idNotification, idNewStatus);
    }
}
