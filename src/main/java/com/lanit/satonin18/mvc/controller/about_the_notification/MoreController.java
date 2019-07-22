package com.lanit.satonin18.mvc.controller.about_the_notification;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.controller.Common_var;
import com.lanit.satonin18.mvc.dao.CrudDAO;
import com.lanit.satonin18.mvc.entity.*;
import com.lanit.satonin18.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller("moreController")
@RequestMapping("/cabinet/aboutTheNotification")
public class MoreController {
    private static final String DEFAULT_ORDER_FIELD_NAME_ACTION = "date";
//----------------------------------------------------------------
    //user vars
    private User currentUser;
    //for web-page Cabinet/MoreAction  -------------------------------
    private Notification currentNotification;
    private Pagination<Action> paginationAction;
    private List<Action> listAction;
    private Action latestAction;
    private String orderFieldNameAction;
    private boolean descAction;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private CrudDAO<ActionType> actionTypeService;
    @Autowired
    private NotificationStatusService statusService;
    @Autowired
    private OrganizationService organizationService;

    private void initVar_after_selectTheNotification(int notificationId) {

        currentNotification = notificationService.getById(notificationId);
        orderFieldNameAction = DEFAULT_ORDER_FIELD_NAME_ACTION;
        descAction = Common_var.DEFAULT_DESC;

        int maxResultAction = Common_var.DEFAULT_MAX_RESULT;
        int navigationPagesAction = Common_var.DEFAULT_NAVIGATION_PAGES;

        paginationAction = actionService.filter_Notific_Order_Pagination(
                currentNotification,
                orderFieldNameAction, descAction,
                new Pagination<Action>(1, maxResultAction, navigationPagesAction)
        );
        listAction = paginationAction.getList();

        //need go in db
        if( ! listAction.isEmpty()) {
            latestAction = Collections.max(listAction, new Comparator<Action>() {
                @Override
                public int compare(Action o1, Action o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }
    }
    private void addAttributes_Action(Model model) {
        model.addAttribute("user", currentUser);
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listAction", listAction);
        model.addAttribute("lastAction", latestAction);
        model.addAttribute("orderFieldNameAction", orderFieldNameAction);
        model.addAttribute("descAction",descAction);
        model.addAttribute("paginationAction",paginationAction);
//        model.addAttribute("listUser", userService.list());
        model.addAttribute("listActionType", actionTypeService.list());
        model.addAttribute("listStatus", statusService.list());
        model.addAttribute("selectShowListMaxResult", Common_var.selectShowListMaxResult);
    }

    @GetMapping("/selectTheNotification")
    public String selectTheNotification(
            @RequestParam("notificationId") int notificationId,
            @RequestParam("userId") int userId,
            Model model) {
        currentUser = userService.getById(userId);
        if(currentUser==null) return "redirect:/";
//------------------------------------------------
        initVar_after_selectTheNotification(notificationId);

        addAttributes_Action(model);
        return "about_the_notification/form_more";
    }

    @GetMapping("/currentState_TheNotification")
    public String currentState_TheNotification(
            Model model) {
        if(currentUser==null) return "redirect:/";
//------------------------------------------------
        paginationAction = actionService.filter_Notific_Order_Pagination(
                currentNotification,
                orderFieldNameAction, descAction,
                new Pagination<Action>(paginationAction.getCurrentPage(), paginationAction.getMaxResult(), paginationAction.getMaxNavigationPage())
        );
        listAction = paginationAction.getList();

        //need go in db
        if( ! listAction.isEmpty()) {
            latestAction = Collections.max(listAction, new Comparator<Action>() {
                @Override
                public int compare(Action o1, Action o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }
//------------------------------------------------
        addAttributes_Action(model);
        return "about_the_notification/form_more";
    }

    @GetMapping("/moreNew")
    public String moreNew(
            HttpServletRequest request, HttpServletResponse response,
            Model model){
        if(currentUser==null) return "redirect:/";
//-------------------------------------------------------------------------
        String[] pageMas = request.getParameterValues("page");
        int page = (pageMas != null) ? Integer.parseInt(pageMas[0]) : 1;

        String[] maxResultMas = request.getParameterValues("maxResult");
        int maxResult = (maxResultMas != null) ? Integer.parseInt(maxResultMas[0]) : paginationAction.getMaxResult();
//-------------------------------------------------------------------------
        String[] orderFieldNameMas = request.getParameterValues("orderFieldName");
        orderFieldNameAction = (orderFieldNameMas != null) ? orderFieldNameMas[0] : DEFAULT_ORDER_FIELD_NAME_ACTION;

        String[] descMas = request.getParameterValues("desc");
        descAction = (descMas != null) ? Boolean.parseBoolean(descMas[0]) : Common_var.DEFAULT_DESC;
//-------------------------------------------------------------------------
        String[] selectNewMaxResultMas = request.getParameterValues("selectedNewResultAndNeedSetFirstPage");
        boolean selectedNewResultAndNeedSetFirstPage = (selectNewMaxResultMas != null) ? Boolean.parseBoolean(selectNewMaxResultMas[0]) : false;

        if(selectedNewResultAndNeedSetFirstPage ) page = 1;
//-------------------------------------------------------------------------
        paginationAction = actionService.filter_Notific_Order_Pagination(
                currentNotification,
                orderFieldNameAction, descAction,
                new Pagination<>(page, maxResult, paginationAction.getMaxNavigationPage())
        );
        listAction = paginationAction.getList();
//------------------------------------------------
        addAttributes_Action(model);
        return "about_the_notification/form_more";
    }


}
