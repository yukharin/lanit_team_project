//package com.lanit.satonin18.mvc.controller;
//
//import com.lanit.satonin18.Pagination;
//import com.lanit.satonin18.mvc.entity.Action;
//import com.lanit.satonin18.mvc.entity.Notification;
//import com.lanit.satonin18.mvc.entity.NotificationStatus;
//import com.lanit.satonin18.mvc.entity.User;
//import com.lanit.satonin18.mvc.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//
////@Scope("session")
////@SessionAttributes(value = "showListNotifications")
////@SessionAttributes(value = "currentUser")
//
//@Controller("cabinetController")
//@RequestMapping("/cabinet/more")
//public class MoreController {
//    @Autowired
//    private NotificationService notificationService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private ActionService actionService;
//    @Autowired
//    private NotificationStatusService statusService;
//    @Autowired
//    private OrganizationService organizationService;
//
////---------------------------------------------
//    private Pagination<Action> paginationAction;
//    private List<Action> listAction;
//    private Notification currentNotification;
//    private String orderFieldNameAction;
//    private boolean descAction;
//
//    @GetMapping("/")
//    public String getMore(@RequestParam("notificationId") int notificationId,
//                       Model model) {
//        //save model for bask2list
//        //addAttributes_NotificationAndOther(model, showListNotifications);
//
//        currentNotification = notificationService.getById(notificationId);
//
//        orderFieldNameAction = "date";
//        descAction = true;
//
//        int maxResultAction = 10;
//        int navigationPagesAction = 10;
//
//        paginationAction = actionService.filter_Notific_Order_Pagination(
//                currentNotification,
//                orderFieldNameAction, descAction,
//                new Pagination<Action>(1, maxResultAction, navigationPagesAction)
//        );
//        listAction = paginationAction.getList();
//        model.addAttribute("currentNotification", currentNotification);
//        model.addAttribute("listAction", listAction);
//        model.addAttribute("orderFieldNameAction", orderFieldNameAction);
//        model.addAttribute("descAction",descAction);
//        model.addAttribute("paginationAction",paginationAction);
//        return "cabinet/more/form_more";
//    }
//    @PostMapping("/more")
//    public String postMore(
//            @RequestParam("id")  int idNotification,
////			@ModelAttribute("user") User user,
//			Model model){
//
////        Notification notification = notificationService.getById(idNotification);
//////        NotificationStatus status = statusService.getById(idStatus);
//////        notification.setNotificationStatus(status);
////
////        notificationService.update(notification);
////
////        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
////                currentUser.getOrganization(),
////                checkedMainListNotificStatuses,
////                showArchive, listArchiveStatus,
////                orderFieldName, desc,
////                new Pagination<Notification>(pagination.getCurrentPage(), pagination.getMaxResult(), pagination.getMaxNavigationPage())
////        );
////        showListNotifications = pagination.getList();
////
////        addAttributes_NotificationAndOther(model, showListNotifications);
//        return "cabinet/list";
//    }
//
//
//    @GetMapping("productListAction")
//    public String listAction(
//                       @RequestParam("maxResult") int maxResultAction,
//                       @RequestParam("page") int pageAction,
//                       Model model) {
//        //save model for bask2list
////        addAttributes_NotificationAndOther(model, showListNotifications);
//
//        paginationAction = actionService.filter_Notific_Order_Pagination(
//                currentNotification,
//                orderFieldNameAction, descAction,
//                new Pagination<Action>(1, pageAction, maxResultAction)
//        );
//        listAction = paginationAction.getList();
//        model.addAttribute("currentNotification", currentNotification);
//        model.addAttribute("listAction", listAction);
//        model.addAttribute("orderFieldNameAction", orderFieldNameAction);
//        model.addAttribute("descAction",descAction);
//        model.addAttribute("paginationAction",paginationAction);
//        return "cabinet/more/form_more";
//    }
//
//    @GetMapping("orderDescAction")
//    public String orderDescAction(
//            @RequestParam("orderFieldName") String orderFieldName,
//            @RequestParam("desc") boolean desc,
//            Model model) {
//        this.orderFieldNameAction = orderFieldName;
//        this.descAction = desc;
//
//        return "redirect:/cabinet/productListAction?maxResult="+paginationAction.getMaxResult()+"&page=1";
//    }
//
//}
