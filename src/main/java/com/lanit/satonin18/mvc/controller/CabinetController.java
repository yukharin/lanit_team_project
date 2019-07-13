package com.lanit.satonin18.mvc.controller;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.dao.CrudDAO;
import com.lanit.satonin18.mvc.entity.*;
import com.lanit.satonin18.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

//@Scope("session")
//@SessionAttributes(value = "showListNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {
    //can be store in NotificationStatusES
    public enum IdStatus {
        NEW(1),
        IN_WORK(2),
        REJECTED(3),
        OK(4);

        private int id;

        private IdStatus(int id) {
            this.id = id;
        }

        public int getId() {return id;}
//        public static LIST values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }

        public enum IdArchiveStatus {
            REJECTED(3),
            OK(4);

            private int id;

            private IdArchiveStatus(int id) {
                this.id = id;
            }

            public int getId() {return id;}

//        public static LIST values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }
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

//        public static LIST values(){
//            return ALL_STATIC FINAL_OBJECT;
//        }
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

    //todo IT VERY VERY BAD, need Session
    private User currentUser;
    private List<NotificationStatus> checkedMainListNotificStatuses;
    private boolean showArchive; //=  is NOTIFICATION_STATUS = PROCESSED
    private List<FastFilter> list4FastFilter;
    private FastFilter currentFastFilter;
    //private List<Notification> showListNotifications;
    private List<NotificationStatus> statuses4selectFilter;
    private List<NotificationStatus> listArchiveStatus;
    private List<Notification> showListNotifications;
    private List<Integer> selectShowListMaxResult;
    private String orderFieldName;
    private boolean desc;
    private Pagination<Notification> pagination;
//---------------------------------------------
    private Pagination<Action> paginationAction;
    private List<Action> listAction;
    private Notification currentNotification;
    private String orderFieldNameAction;
    private boolean descAction;

    private void initVars() {
        currentUser = userService.getById(1);//for my test

        statuses4selectFilter = new ArrayList<>();
        statuses4selectFilter.add(
                statusService.getById(IdStatus.NEW.getId()));
        statuses4selectFilter.add(
                statusService.getById(IdStatus.IN_WORK.getId()));
        statuses4selectFilter.add(
                statusService.getById(IdStatus.REJECTED.getId()));
        statuses4selectFilter.add(
                statusService.getById(IdStatus.OK.getId()));
        checkedMainListNotificStatuses = statuses4selectFilter;

        listArchiveStatus = new ArrayList<>();
        listArchiveStatus.add(
                statusService.getById(IdStatus.IdArchiveStatus.OK.getId()));
        listArchiveStatus.add(
                statusService.getById(IdStatus.IdArchiveStatus.REJECTED.getId()));

        list4FastFilter = new ArrayList<>();
        list4FastFilter.add(FastFilter.NEW_NOTIFICATION);
        list4FastFilter.add(FastFilter.RUN_OUT_3_DAY_DATA_RESPONSE);
        list4FastFilter.add(FastFilter.DIR_LETTER);
        list4FastFilter.add(FastFilter.MULTIPLE_RETURNS);
        currentFastFilter = FastFilter.RUN_OUT_3_DAY_DATA_RESPONSE;

        selectShowListMaxResult = new ArrayList<>();
        selectShowListMaxResult.add(1);
        selectShowListMaxResult.add(2);
        selectShowListMaxResult.add(5);
        selectShowListMaxResult.add(10);
        selectShowListMaxResult.add(25);
        selectShowListMaxResult.add(50);
        selectShowListMaxResult.add(100);

        showArchive = false;

        orderFieldName = "dateResponse";
        desc = true;

        int maxResult = 10;
        int navigationPages = 10;

        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
                currentUser.getOrganization(),
                checkedMainListNotificStatuses,
                showArchive, listArchiveStatus,
                orderFieldName, desc,
                new Pagination<Notification>(1, maxResult, navigationPages)
        );
        showListNotifications = pagination.getList();
    }

    private void addAttributes_NotificationAndOther(Model model, List<Notification> notifications) {
        model.addAttribute("user", currentUser);
        model.addAttribute("user_list", userService.list());

        model.addAttribute("statuses4select", statuses4selectFilter);
        model.addAttribute("checked_list", checkedMainListNotificStatuses);
        model.addAttribute("showArchive", showArchive);
        model.addAttribute("listFastFilter", list4FastFilter);
        model.addAttribute("currentFastFilter", currentFastFilter);

        model.addAttribute("selectShowListMaxResult", selectShowListMaxResult);

        model.addAttribute("orderFieldName", orderFieldName);
        model.addAttribute("desc",desc);
        model.addAttribute("pagination",pagination);

        model.addAttribute("notific_list", notifications);
    }

    @GetMapping("/")
    public String initList(Model model) throws NoSuchFieldException {
        initVars();
        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }

    @GetMapping("/list")
    public String list(Model model) throws NoSuchFieldException {

        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }

    @PostMapping("/selectUser")
    public String selectUser(@RequestParam("idSelectUser") int idSelectUser,
                             Model model) {
        currentUser = userService.getById(idSelectUser);
        checkedMainListNotificStatuses = statuses4selectFilter;
        showArchive = false;

        orderFieldName = "dateResponse";
        desc = true;

        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
                currentUser.getOrganization(),
                checkedMainListNotificStatuses,
                showArchive, listArchiveStatus,
                orderFieldName, desc,
                new Pagination<Notification>(1, pagination.getMaxResult(), pagination.getMaxNavigationPage())
        );
        showListNotifications = pagination.getList();
        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }

    @GetMapping("/productList")
    public String list(
                       @RequestParam("maxResult") int maxResult,
                       @RequestParam("page") int page,
                       Model model) {
        pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
                currentUser.getOrganization(),
                checkedMainListNotificStatuses,
                showArchive, listArchiveStatus,
                orderFieldName, desc,
                new Pagination<Notification>(page, maxResult, pagination.getMaxNavigationPage())
        );
        showListNotifications = pagination.getList();
        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }

    @GetMapping("/orderDesc")
    public String orderDesc(
                       @RequestParam("orderFieldName") String orderFieldName,
                       @RequestParam("desc") boolean desc,
                       Model model) {
        this.orderFieldName = orderFieldName;
        this.desc = desc;

        return "redirect:/cabinet/productList?maxResult="+pagination.getMaxResult()+"&page=1";
    }

    @GetMapping("/filterByNotificStatus")
    public String filterByNotificStatus(HttpServletRequest request, HttpServletResponse response,
//                                        @RequestParam("id") int[] id,
//                                        @RequestParam("showArchive") boolean theFlagArchive,
                                        Model model) {
        showListNotifications = new ArrayList<>();
        checkedMainListNotificStatuses = new ArrayList<>();
        List<NotificationStatus> checkedStatusList = new ArrayList<>();
        showArchive = false;
        //pagination = null;

        String[] ids = request.getParameterValues("id");
        boolean haveIdCheckedStatusList = (ids != null);

        String[] masshowArchive = request.getParameterValues("showArchive");
        boolean hasCheckedStatusArhive = (masshowArchive != null);
        showArchive = hasCheckedStatusArhive;

        //MOCK
        String[] selectFastFilter = request.getParameterValues("selectFastFilter");
        boolean hasSelectedFastFilter = (masshowArchive != null);

        if (haveIdCheckedStatusList) {
            checkedMainListNotificStatuses = statusService.filterIds(ids);

            pagination = notificationService.filter_Org_NotificStatuses_Archive_Order_Pagination(
                    currentUser.getOrganization(),
                    checkedMainListNotificStatuses,
                    showArchive, listArchiveStatus,
                    orderFieldName, desc,
                    new Pagination<Notification>(1, pagination.getMaxResult(), pagination.getMaxNavigationPage())
            );
            showListNotifications = pagination.getList();
        }else{
            pagination = new Pagination.EmptyPagination<Notification>(pagination);
        }
        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }

    @GetMapping("/editStatus")
    public String editStatus(
            @RequestParam("idNotification") int idNotification,
            @RequestParam("idStatus") int idStatus,
            Model model) {

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

        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }

    //TODO move in other controler
    @GetMapping("/more")
    public String getMore(@RequestParam("notificationId") int notificationId,
                       Model model) {
        //save model for bask2list
        addAttributes_NotificationAndOther(model, showListNotifications);

        currentNotification = notificationService.getById(notificationId);

        orderFieldNameAction = "date";
        descAction = true;

        int maxResultAction = 10;
        int navigationPagesAction = 10;

        paginationAction = actionService.filter_Notific_Order_Pagination(
                currentNotification,
                orderFieldNameAction, descAction,
                new Pagination<Action>(1, maxResultAction, navigationPagesAction)
        );
        listAction = paginationAction.getList();

        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listAction", listAction);
        model.addAttribute("orderFieldNameAction", orderFieldNameAction);
        model.addAttribute("descAction",descAction);
        model.addAttribute("paginationAction",paginationAction);

//        model.addAttribute("listUser", userService.list());
        model.addAttribute("listActionType", actionTypeService.list());
        model.addAttribute("listStatus", statusService.list());

        return "cabinet/form_more";
    }
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


    @GetMapping("productListAction")
    public String listAction(
                       @RequestParam("maxResult") int maxResultAction,
                       @RequestParam("page") int pageAction,
                       Model model) {
        //save model for bask2list
        addAttributes_NotificationAndOther(model, showListNotifications);

        Pagination<Action> actionPagination = new Pagination<>(pageAction, maxResultAction, paginationAction.getMaxNavigationPage());

        paginationAction = actionService.filter_Notific_Order_Pagination(
                currentNotification,
                orderFieldNameAction, descAction,
                actionPagination
        );
        listAction = paginationAction.getList();

        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listAction", listAction);
        model.addAttribute("orderFieldNameAction", orderFieldNameAction);
        model.addAttribute("descAction",descAction);
        model.addAttribute("paginationAction",paginationAction);

//        model.addAttribute("listUser", userService.list());
        model.addAttribute("listActionType", actionTypeService.list());
        model.addAttribute("listStatus", statusService.list());

        return "cabinet/form_more";
    }

    @GetMapping("orderDescAction")
    public String orderDescAction(
            @RequestParam("orderFieldName") String orderFieldName,
            @RequestParam("desc") boolean desc,
            Model model) {
        this.orderFieldNameAction = orderFieldName;
        this.descAction = desc;

        return "redirect:/cabinet/productListAction?maxResult="+paginationAction.getMaxResult()+"&page=1";
    }


    @GetMapping("/addAction")
    public String GetAddActionEditNotificSatus(
            @RequestParam("notificationId") int notificationId,
            @RequestParam("idActionType") int idActionType,
            @RequestParam("content") String content,
            @RequestParam("idUserImplementor") int idUserImplementor,
            @RequestParam("idNotificationStatus") int idNotificationStatus,
                          Model model) {
        Notification notification = notificationService.getById(notificationId);
        ActionType actionType = actionTypeService.getById(idActionType);
        User userImplementor = userService.getById(idUserImplementor);
        NotificationStatus status = statusService.getById(idNotificationStatus);

        Action actionNew = new Action();
//        Action actionNew = new Action(
//                content,
//                new Timestamp(System.currentTimeMillis()),
//                notification,
//                actionType,
//                userImplementor,
//                status
//        );

      //  actionNew.setId(999);
        actionNew.setNotification(notification);
        actionNew.setActionType(actionType);
        actionNew.setUserByIdImplementor(userImplementor);
        actionNew.setNotificationStatusAfterProcessing(status);
        actionNew.setDate(notification.getActions().get(0).getDate());
        actionNew.setContent(content);

        //notification.getActions().add(actionNew);
        //notificationService.saveOrUpdate(notification);

        //        actionService.saveOrUpdate(actionNew);
        actionService.save(actionNew);

        //notification.setNotificationStatus(actionNew.getNotificationStatusAfterProcessing());

        return "redirect:/cabinet/productListAction?maxResult="+paginationAction.getMaxResult()+"&page="+paginationAction.getCurrentPage();
    }
//    @PostMapping("/addAction")
//    public String PostAddAction(
//            @RequestParam("notificationId") int notificationId,
//                          Model model) {
//        //save model for bask2list
//        addAttributes_NotificationAndOther(model, showListNotifications);
//
//        model.addAttribute("listUser", userService.list());
//        model.addAttribute("listActionType", actionTypeService.list());
//        return "cabinet/form_add_action";
//    }
}
