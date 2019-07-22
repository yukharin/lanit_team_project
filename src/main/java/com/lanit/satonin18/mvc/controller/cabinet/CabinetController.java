package com.lanit.satonin18.mvc.controller.cabinet;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.controller.Common_var;
import com.lanit.satonin18.mvc.dao.CrudDAO;
import com.lanit.satonin18.mvc.entity.*;
import com.lanit.satonin18.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

//@Scope("session")
//@SessionAttributes(value = "showListNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {
    //common vars in app
    private static final String DEFAULT_ORDER_FIELD_NAME = "dateResponse";
    private static final boolean DEFAULT_SHOW_ARCHIVE = false;
    private static final List<FastFilter> list4FastFilter = new ArrayList<>(Arrays.asList(FastFilter.values()));
//    @Autowired
//    public static NotificationStatusService static_statusService;
    public static /*final*/ List<NotificationStatus> statuses4selectFilter; /*= new ArrayList<NotificationStatus>( static_statusService.listByIds( IdStatus.getAllId() ))*/
    public static /*final*/ List<NotificationStatus> listArchiveStatus; /*= new ArrayList<NotificationStatus>( static_statusService.listByIds( IdStatus.getArchiveStatusesId() ))*/
//--------------------------------------------------------------------
    //user vars
    private User currentUser;
    //for web-page Cabinet/list  ---------------------------------------
    private List<NotificationStatus> checkedMainListNotificStatuses;
    private boolean showArchive; //=  is NOTIFICATION_STATUS = PROCESSED
    private FastFilter currentFastFilter; //Mock
    private List<Notification> showListNotifications;
    private String orderFieldName;
    private boolean desc;
    private Pagination<Notification> pagination;

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

    private void initVar_after_selectTheUser() {
        statuses4selectFilter = new ArrayList<>();
        for (int id : IdStatus.getAllId()) {
            statuses4selectFilter.add(
                    statusService.getById(id));
        }

        listArchiveStatus = new ArrayList<>();
        for (int id : IdStatus.getArchiveStatusesId()) {
            listArchiveStatus.add(
                    statusService.getById(id));
        }

        checkedMainListNotificStatuses = statuses4selectFilter;

        showArchive = DEFAULT_SHOW_ARCHIVE;
        orderFieldName = DEFAULT_ORDER_FIELD_NAME;
        desc = Common_var.DEFAULT_DESC;

        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
                currentUser.getOrganization(),
                checkedMainListNotificStatuses,
                showArchive, listArchiveStatus,
                orderFieldName, desc,
                new Pagination<Notification>(1, Common_var.DEFAULT_MAX_RESULT, Common_var.DEFAULT_NAVIGATION_PAGES)
        );
        showListNotifications = pagination.getList();
    }

    private void addAttributes_Notification(Model model) {
        model.addAttribute("listFastFilter", list4FastFilter);
        model.addAttribute("statuses4select", statuses4selectFilter);
        model.addAttribute("selectShowListMaxResult", Common_var.selectShowListMaxResult);

        model.addAttribute("user", currentUser);
        model.addAttribute("user_list", userService.list());

        model.addAttribute("checked_list", checkedMainListNotificStatuses);
        model.addAttribute("showArchive", showArchive);
        model.addAttribute("currentFastFilter", currentFastFilter);
        model.addAttribute("pagination",pagination);
        model.addAttribute("orderFieldName", orderFieldName);
        model.addAttribute("desc",desc);
        model.addAttribute("notific_list", showListNotifications);
    }

    //todo NEED REMOVE showING idUser (move post or inner redirect (without client) and better in inner session)
    @GetMapping("/selectUser")
    public String selectUser(
            @RequestParam("idSelectUser") int idSelectUser,
            Model model){
        currentUser = userService.getById(idSelectUser);

        initVar_after_selectTheUser();

        addAttributes_Notification(model);
        return "cabinet/list";
    }

    //USE ONLY FOR BACK FROM web-page"more"
    @GetMapping("/current_state") //add  @GetMapping("/")
    public String current_state(Model model){
        if(currentUser==null) return "redirect:/";

        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
                currentUser.getOrganization(),
                checkedMainListNotificStatuses,
                showArchive, listArchiveStatus,
                orderFieldName, desc,
                new Pagination<Notification>(pagination.getCurrentPage(), pagination.getMaxResult(), pagination.getMaxNavigationPage())
        );
        showListNotifications = pagination.getList();

        addAttributes_Notification(model);
        return "cabinet/list";
    }

    @GetMapping("/listNew") //will need add  @GetMapping("/")
    public String listNew(
            HttpServletRequest request, HttpServletResponse response,
            Model model){
        if(currentUser==null) return "redirect:/";
//-------------------------------------------------------------------------
        String[] pageMas = request.getParameterValues("page");
        int page = (pageMas != null) ? Integer.parseInt(pageMas[0]) : 1;

        String[] maxResultMas = request.getParameterValues("maxResult");
        int maxResult = (maxResultMas != null) ? Integer.parseInt(maxResultMas[0]) : pagination.getMaxResult();
//-------------------------------------------------------------------------
        String[] orderFieldNameMas = request.getParameterValues("orderFieldName");
        orderFieldName = (orderFieldNameMas != null) ? orderFieldNameMas[0] : DEFAULT_ORDER_FIELD_NAME;

        String[] descMas = request.getParameterValues("desc");
        desc = (descMas != null) ? Boolean.parseBoolean(descMas[0]) : Common_var.DEFAULT_DESC;
//-------------------------------------------------------------------------
        String[] selectNewMaxResultMas = request.getParameterValues("selectedNewResultAndNeedSetFirstPage");
        boolean selectedNewResultAndNeedSetFirstPage = (selectNewMaxResultMas != null) ? Boolean.parseBoolean(selectNewMaxResultMas[0]) : false;

        if(selectedNewResultAndNeedSetFirstPage ) page = 1;
//-------------------------------------------------------------------------
        String[] idNotification4editStatusMas = request.getParameterValues("idNotification4editStatus");
        boolean has_idNotification4editStatus = (idNotification4editStatusMas != null);

        if(has_idNotification4editStatus){
            int idNotification4editStatus = Integer.parseInt(idNotification4editStatusMas[0]);

            String[] idStatus4editStatusMas = request.getParameterValues("idStatus_With_idNotification="+idNotification4editStatus);
            int idStatus4editStatus = Integer.parseInt(idStatus4editStatusMas[0]);

            Notification notification = notificationService.getById(idNotification4editStatus);
            NotificationStatus status = statusService.getById(idStatus4editStatus);
            notification.setNotificationStatus(status);

            notificationService.saveOrUpdate(notification);
        }
//-------------------------------------------------------------------------
        String[] ids = request.getParameterValues("idFilterStatus");
        boolean haveIdCheckedStatusList = (ids != null);

        String[] masshowArchive = request.getParameterValues("showArchive");
        boolean hasCheckedStatusArhive = (masshowArchive != null);
        showArchive = hasCheckedStatusArhive;

        //MOCK
        String[] selectFastFilter = request.getParameterValues("selectFastFilter");
        boolean hasSelectedFastFilter = (masshowArchive != null);
//-------------------------------------------------------------------------
        showListNotifications = new ArrayList<>();
        checkedMainListNotificStatuses = new ArrayList<>();
        List<NotificationStatus> checkedStatusList = new ArrayList<>();

        if (haveIdCheckedStatusList) {
            checkedMainListNotificStatuses = statusService.filterIds(ids);

            pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
                    currentUser.getOrganization(),
                    checkedMainListNotificStatuses,
                    showArchive, listArchiveStatus,
                    orderFieldName, desc,
//                    new Pagination<Notification>(1, pagination.getMaxResult(), pagination.getMaxNavigationPage())
                    new Pagination<Notification>(page, maxResult/*1, pagination.getMaxResult()*/, pagination.getMaxNavigationPage())
            );
            showListNotifications = pagination.getList();
        }else{
            pagination = new Pagination.EmptyPagination<Notification>(pagination);
        }

        addAttributes_Notification(model);
        return "cabinet/list";
    }
}
