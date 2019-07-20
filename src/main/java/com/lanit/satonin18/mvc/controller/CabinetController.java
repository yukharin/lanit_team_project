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
import java.util.*;

//@Scope("session")
//@SessionAttributes(value = "showListNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {
    //can be store in type NotificationStatusES
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
    //for web-page Cabinet/MoreAction  -------------------------------
    private Pagination<Action> paginationAction;
    private List<Action> listAction;
    private Action latestAction;
    private Notification currentNotification;
    private String orderFieldNameAction;
    private boolean descAction;

    private void initVar4Cabinet() {
        //currentUser = userService.getById(1);// MOCK for my test

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
    private void initVar4Action(int notificationId) {
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
        if( ! listAction.isEmpty()) {
            latestAction = Collections.max(listAction, new Comparator<Action>() {
                @Override
                public int compare(Action o1, Action o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }
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
    private void addAttributes_Action(Model model) {
        model.addAttribute("currentNotification", currentNotification);
        model.addAttribute("listAction", listAction);
        model.addAttribute("lastAction", latestAction);
        model.addAttribute("orderFieldNameAction", orderFieldNameAction);
        model.addAttribute("descAction",descAction);
        model.addAttribute("paginationAction",paginationAction);
//        model.addAttribute("listUser", userService.list());
        model.addAttribute("listActionType", actionTypeService.list());
        model.addAttribute("listStatus", statusService.list());
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
//        orderFieldName = "dateResponse";
//        desc = true;
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

    @GetMapping("/list") //add  @GetMapping("/")
    public String list(Model model){
//        if(pagination==null) initVar4Cabinet();
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

    @GetMapping("/selectPagination")
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

        addAttributes_Notification(model);
        return "cabinet/list";
    }

    @GetMapping("/orderDesc")
    public String orderDesc(
                       @RequestParam("orderFieldName") String orderFieldName,
                       @RequestParam("desc") boolean desc,
                       Model model) {
        this.orderFieldName = orderFieldName;
        this.desc = desc;
        return "redirect:/cabinet/selectPagination?maxResult="+pagination.getMaxResult()+"&page=1";
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
        addAttributes_Notification(model);
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

        addAttributes_Notification(model);
        return "cabinet/list";
    }

    //TODO move in other controler
    @GetMapping("/more")
    public String more(@RequestParam("notificationId") int notificationId,
                       Model model) {
//        if(currentNotification == null)
        initVar4Action(notificationId);

        addAttributes_Notification(model); //save model for bask2list
        addAttributes_Action(model);
        return "cabinet/form_more";
    }

    //TODO move in other controler
    @GetMapping("selectPaginationAction")
    public String listAction(
                       @RequestParam("maxResult") int maxResultAction,
                       @RequestParam("page") int pageAction,
                       Model model) {
        Pagination<Action> actionPagination = new Pagination<>(pageAction, maxResultAction, paginationAction.getMaxNavigationPage());

        paginationAction = actionService.filter_Notific_Order_Pagination(
                currentNotification,
                orderFieldNameAction, descAction,
                actionPagination
        );
        listAction = paginationAction.getList();

        addAttributes_Notification(model); //save model for bask2list
        addAttributes_Action(model);
        return "cabinet/form_more";
    }

    //TODO move in other controler
    @GetMapping("orderDescAction")
    public String orderDescAction(
            @RequestParam("orderFieldName") String orderFieldName,
            @RequestParam("desc") boolean desc,
            Model model) {
        this.orderFieldNameAction = orderFieldName;
        this.descAction = desc;

        return "redirect:/cabinet/selectPaginationAction?maxResult="+paginationAction.getMaxResult()+"&page=1";
    }

    //TODO move in other controler
    @GetMapping("/addAction")
    public String GetAddAction(
//            @RequestParam("notificationId") int notificationId,
            @RequestParam("idActionType") int idActionType,
            @RequestParam("content") String content,
            @RequestParam("idUserImplementor") int idUserImplementor,
            @RequestParam("idNotificationStatus") int idNotificationStatus,
            Model model) {
        ActionType actionType = actionTypeService.getById(idActionType);
        User userImplementor = userService.getById(idUserImplementor);
        NotificationStatus status = statusService.getById(idNotificationStatus);

        long timeNow = System.currentTimeMillis();

        Action actionNew = new Action();
        actionNew.setNotification(currentNotification);//can be add in inside: notification.getActions().add(THIS);
        actionNew.setActionType(actionType);
        actionNew.setUserByIdImplementor(userImplementor);
        actionNew.setNotificationStatusAfterProcessing(status);
        actionNew.setDate(new java.sql.Timestamp(timeNow));
        actionNew.setContent(content);

        actionService.save(actionNew);

        currentNotification.getActions().add(actionNew);
        currentNotification.setNotificationStatus(actionNew.getNotificationStatusAfterProcessing());
        //other logic app//currentNotification.setDateResponse(new java.sql.Date(timeNow));

        notificationService.saveOrUpdate(currentNotification);

        return "redirect:/cabinet/selectPaginationAction?maxResult="+paginationAction.getMaxResult()+"&page="+paginationAction.getCurrentPage();
    }
}
