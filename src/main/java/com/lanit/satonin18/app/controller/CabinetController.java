package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.entity.*;
import com.lanit.satonin18.app.dto.Common_Default_var;
import com.lanit.satonin18.app.dto.cabinet.*;
import com.lanit.satonin18.app.entity.no_db.Status;
import com.lanit.satonin18.app.service.app_service.CabinetService;
import com.lanit.satonin18.app.service.entities_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

//@Scope("session")
//@SessionAttributes(value = "showListNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {
    //TODO take in sesion //can be store ID
    private User currentUser;
    private CabinetState state; //save state if user go in other web-page, and after return here

//    @ModelAttribute("cabinetModel")
//    public CabinetModel getCabinetModel(){
//        return new CabinetModel();
//    }

    @Autowired
    private UserService userService;
    @Autowired
    private CabinetService cabinetService;

    private void addAttributes_Notification(Model model, User currentUser) {
        model.addAttribute("state", state);

        model.addAttribute("user", currentUser);
        model.addAttribute("selectShowListMaxResult", Common_Default_var.selectShowListMaxResult);
        model.addAttribute("listFastFilter", Default_Cabinet_var.list4FastFilter);

        //dto.addAttribute("currentFastFilter", cabinetModel.getCurrentFastFilter());
    }

    @GetMapping("/selectUser")
    public String selectUser(
            @RequestParam("idSelectUser") int idSelectUser,
//            HttpSession session,
            Model model){
        currentUser = userService.getById(idSelectUser);
//        session.setAttribute("user", idSelectUser);

        CabinetModel cabinetModel = new CabinetModel();
        cabinetModel.setIdFilterStatus( new ArrayList<>( Status.getAllId() ) );

        state = new CabinetState();
        state.setModel(cabinetModel);

        cabinetService.initCommonVar4CabinetState(state); //@PostConstruct

        return "redirect:/cabinet/current_state";

    }

    @GetMapping("/current_state") //save state if user go in other web-page, and after return here
    public String current_state( HttpSession session, Model model){
        if(currentUser==null) return "redirect:/";
//        Integer userId = (Integer) session.getAttribute("user");
//        if( userId == null) return "redirect:/";
//        User currentUser = userService.getById(userId);

        cabinetService.executeQuery(state, currentUser);

        addAttributes_Notification(model, currentUser);
        return "cabinet/list";
    }

    @GetMapping("/listNew") //will need add  @GetMapping("/")
    public String listNew(
//            HttpServletRequest request, HttpServletResponse response,
//            HttpSession session,
            @ModelAttribute(value = "cabinetModel") CabinetModel cabinetModel,
            Model model
    ){
        if(currentUser==null) return "redirect:/";
//        Integer userId = (Integer) session.getAttribute("user");
//        if( userId == null) return "redirect:/";
//        User currentUser = userService.getById(userId);

        if(cabinetModel.isSelectedNewResultAndNeedSetFirstPage() )
            cabinetModel.setPage(1);
        if(cabinetModel.isFlagNeedReplaceStatus())
            replaceStatus(cabinetModel);

        state.setModel(cabinetModel);
        cabinetService.executeQuery(state, currentUser);

        addAttributes_Notification(model, currentUser);
        return "cabinet/list";
    }

    private void replaceStatus(@ModelAttribute("cabinetModel") CabinetModel cabinetModel) {
        int idNotification = cabinetModel.getSelectedIdNotification4editStatus();
        int idNewStatus = cabinetModel.getSelectedNewIdStatus();

        cabinetService.editStatus(idNotification, idNewStatus);
    }


}
