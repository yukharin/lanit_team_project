package com.lanit.satonin18.mvc.controller;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.Common_var;
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



    static final String DEFAULT_ORDER_FIELD_NAME = "dateResponse";
    static final boolean DEFAULT_SHOW_ARCHIVE = false;

    //can be store in type NotificationStatusES
    public enum IdStatus {
        NEW(1),
        IN_WORK(2),
        REJECTED(3, true),
        OK(4, true);

        private int id;
        private boolean isArchiveStatus;

        private IdStatus(int id) {
            this.id = id;
            this.isArchiveStatus = false;
        }
        private IdStatus(int id, boolean isArchiveStatus) {
            this.id = id;
            this.isArchiveStatus = isArchiveStatus;
        }

        public int getId() {return id;}

        public boolean isArchiveStatus() {
            return isArchiveStatus;
        }
//        public static mas[] values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }

        public static void addAllInTheList(List<IdStatus> list){
            list.addAll(Arrays.asList(IdStatus.values()));
        }
        public static void addAllArchiveStatusesInTheList(List<IdStatus> list){
//            list.add(IdStatus.REJECTED);
//            list.add(IdStatus.OK);
            for (IdStatus itemEnam : IdStatus.values() ) {
                if(itemEnam.isArchiveStatus())  list.add(itemEnam);
            }
        }
        public static List<Integer> getAllId(){
            ArrayList<Integer> ids = new ArrayList<>(IdStatus.values().length);

            int i=0;
            for (IdStatus itemEnam : IdStatus.values() ) {
                ids.add(itemEnam.getId() );
                i++;
            }
            return ids;
        }
        public static List<Integer> getArchiveStatusesId(){
            ArrayList<Integer> ids = new ArrayList<>(/*IdStatus.values().length*/);

            int i=0;
            for (IdStatus itemEnam : IdStatus.values() ) {
                if(itemEnam.isArchiveStatus())  ids.add(itemEnam.getId() );
                i++;
            }
            return ids;
        }
    };
    //MOCK
    public enum FastFilter {
        NEW_NOTIFICATION (1, "Новые уведомления"),
        RUN_OUT_3_DAY_DATA_RESPONSE (2, "Истекает срок предоставления ответа (за 3 дня)"),
        DIR_LETTER(3, "(Mock)По направленному письму (для выбранной записи)"),
        MULTIPLE_RETURNS(4, "Множественные возвраты");

        private int id;
        private String description;

        private FastFilter(int id, String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

//        public static mas[] values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }

        public static void addAllInTheList(List<FastFilter> list){
            list.addAll(Arrays.asList(FastFilter.values()));
        }
    };

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

    //common vars in app
    private List<FastFilter> list4FastFilter;
    private List<NotificationStatus> statuses4selectFilter;
    private List<NotificationStatus> listArchiveStatus;
    private List<Integer> selectShowListMaxResult;
    //--------------------------------------------------
    //user vars //todo IT VERY VERY BAD, need Session
    private User currentUser;
    //for web-page Cabinet/list  ---------------------------------------
    private List<NotificationStatus> checkedMainListNotificStatuses;
    private boolean showArchive; //=  is NOTIFICATION_STATUS = PROCESSED
    private FastFilter currentFastFilter;
    private List<Notification> showListNotifications;
    private String orderFieldName;
    private boolean desc;
    private Pagination<Notification> pagination;


    private void initVar4Cabinet() {
        //currentUser = userService.getById(1);// MOCK for my test

        statuses4selectFilter = new ArrayList<>();
        for (int id : IdStatus.getAllId()) {
            statuses4selectFilter.add(
                    statusService.getById(id));
        }
//        statuses4selectFilter.add(
//                statusService.getById(IdStatus.NEW.getId()));
//        statuses4selectFilter.add(
//                statusService.getById(IdStatus.IN_WORK.getId()));
//        statuses4selectFilter.add(
//                statusService.getById(IdStatus.REJECTED.getId()));
//        statuses4selectFilter.add(
//                statusService.getById(IdStatus.OK.getId()));
        checkedMainListNotificStatuses = statuses4selectFilter;

        listArchiveStatus = new ArrayList<>();
        for (int id : IdStatus.getArchiveStatusesId()) {
            listArchiveStatus.add(
                    statusService.getById(id));
        }
//        listArchiveStatus.add(
//                statusService.getById(IdStatus.IdArchiveStatus.OK.getId()));
//        listArchiveStatus.add(
//                statusService.getById(IdStatus.IdArchiveStatus.REJECTED.getId()));

        list4FastFilter = new ArrayList<>();
        FastFilter.addAllInTheList(list4FastFilter);
//        list4FastFilter.add(FastFilter.NEW_NOTIFICATION);
//        list4FastFilter.add(FastFilter.RUN_OUT_3_DAY_DATA_RESPONSE);
//        list4FastFilter.add(FastFilter.DIR_LETTER);
//        list4FastFilter.add(FastFilter.MULTIPLE_RETURNS);
//        currentFastFilter = FastFilter.RUN_OUT_3_DAY_DATA_RESPONSE;

        selectShowListMaxResult = new ArrayList<>();
        for (int nomer : Common_var.DEFAULT_MAS_4_SELECT_MAX_RESULT) {
            selectShowListMaxResult.add(nomer);
        }
//        selectShowListMaxResult.add(1);
//        selectShowListMaxResult.add(2);
//        selectShowListMaxResult.add(5);
//        selectShowListMaxResult.add(10);
//        selectShowListMaxResult.add(25);
//        selectShowListMaxResult.add(50);
//        selectShowListMaxResult.add(100);

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
        model.addAttribute("selectShowListMaxResult", selectShowListMaxResult);

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
//        checkedMainListNotificStatuses = statuses4selectFilter;
//        showArchive = false;
//
//        orderFieldName = DEFAULT_ORDER_FIELD_NAME;
//        desc = DEFAULT_DESC;
//
//        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
//                currentUser.getOrganization(),
//                checkedMainListNotificStatuses,
//                showArchive, listArchiveStatus,
//                orderFieldName, desc,
//                new Pagination<Notification>(1, pagination.getMaxResult(), pagination.getMaxNavigationPage())
//        );
//        showListNotifications = pagination.getList();
        initVar4Cabinet();

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
//-------------------------------------------------------------------------
    }
//
//    @GetMapping("/selectPagination")
//    public String list(
//                       @RequestParam("maxResult") int maxResult,
//                       @RequestParam("page") int page,
//                       Model model) {
////        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
////                currentUser.getOrganization(),
////                checkedMainListNotificStatuses,
////                showArchive, listArchiveStatus,
////                orderFieldName, desc,
////                new Pagination<Notification>(page, maxResult, pagination.getMaxNavigationPage())
////        );
////        showListNotifications = pagination.getList();
////
////        addAttributes_Notification(model);
//        return "cabinet/list";
//    }

//    @GetMapping("/selectMaxResult")
//    public String selectMaxResult(
//                       @RequestParam("maxResult") int maxResult,
//                       Model model) {
//
//        return "redirect:cabinet/listNew?maxResult="+maxResult+"";
//    }

//    @GetMapping("/orderDesc")
//    public String orderDesc(
//                       @RequestParam("orderFieldName") String orderFieldName,
//                       @RequestParam("desc") boolean desc,
//                       Model model) {
//        this.orderFieldName = orderFieldName;
//        this.desc = desc;
//        return "redirect:/cabinet/selectPagination?maxResult="+pagination.getMaxResult()+"&page=1";
//    }

//    @GetMapping("/filterByNotificStatus")
//    public String filterByNotificStatus(HttpServletRequest request, HttpServletResponse response,
////                                        @RequestParam("id") int[] id,
////                                        @RequestParam("showArchive") boolean theFlagArchive,
//                                        Model model) {
//        showListNotifications = new ArrayList<>();
//        checkedMainListNotificStatuses = new ArrayList<>();
//        List<NotificationStatus> checkedStatusList = new ArrayList<>();
//        showArchive = false;
//        //pagination = null;
//
//        String[] ids = request.getParameterValues("idFilterStatus");
//        boolean haveIdCheckedStatusList = (ids != null);
//
//        String[] masshowArchive = request.getParameterValues("showArchive");
//        boolean hasCheckedStatusArhive = (masshowArchive != null);
//        showArchive = hasCheckedStatusArhive;
//
//        //MOCK
//        String[] selectFastFilter = request.getParameterValues("selectFastFilter");
//        boolean hasSelectedFastFilter = (masshowArchive != null);
//
//        if (haveIdCheckedStatusList) {
//            checkedMainListNotificStatuses = statusService.filterIds(ids);
//
//            pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
//                    currentUser.getOrganization(),
//                    checkedMainListNotificStatuses,
//                    showArchive, listArchiveStatus,
//                    orderFieldName, desc,
//                    new Pagination<Notification>(1, pagination.getMaxResult(), pagination.getMaxNavigationPage())
//            );
//            showListNotifications = pagination.getList();
//        }else{
//            pagination = new Pagination.EmptyPagination<Notification>(pagination);
//        }
//        addAttributes_Notification(model);
//        return "cabinet/list";
//    }

    //TODO NEED EDIT !!!
    @GetMapping("/editStatus")
    public String editStatus(
            @RequestParam("idNotification") int idNotification,
            @RequestParam("idStatus") int idStatus,
            Model model) {
        if(currentUser==null) return "redirect:/";

        Notification notification = notificationService.getById(idNotification);
        NotificationStatus status = statusService.getById(idStatus);
        notification.setNotificationStatus(status);

        notificationService.saveOrUpdate(notification);

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






}
