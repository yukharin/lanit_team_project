package com.lanit.satonin18.mvc.controller;

import com.lanit.satonin18.FastFilter;
import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.entity.Notification;
import com.lanit.satonin18.mvc.entity.NotificationStatus;
import com.lanit.satonin18.mvc.entity.User;
import com.lanit.satonin18.mvc.service.CrudService;
import com.lanit.satonin18.mvc.service.NotificationService;
import com.lanit.satonin18.mvc.service.NotificationStatusService;
import com.lanit.satonin18.mvc.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

//@Scope("session")
//@SessionAttributes(value = "orgNotifications")
//@SessionAttributes(value = "currentUser")

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {

    //todo remove somewhere (in ENAM)
    public static final int ID_NOTIFICATION_STATUS_PROCESSED = 7;
    public static final int ID_NOTIFICATION_STATUS_WAITING_TO_BE_PROCESSED = 1;
    public static final int ID_NOTIFICATION_STATUS_ANSWER_NOT_ACCEPTED = 2;
    public static final int ID_NOTIFICATION_STATUS_ANSWER_SENT = 3;

    public static final FastFilter NEW_NOTIFICATION = new FastFilter(1, "Новые уведомления");
    public static final FastFilter RUN_OUT_3_DAY_DATA_RESPONSE = new FastFilter(2, "Истекает срок предоставления ответа (за 3 дня)");
    public static final FastFilter DIR_LETTER = new FastFilter(3, "(Mock)По направленному письму (для выбранной записи)");
    public static final FastFilter MULTIPLE_RETURNS = new FastFilter(4, "Множественные возвраты");

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CrudService<User> userService;
    @Autowired
    private NotificationStatusService statusService;
    @Autowired
    private OrganizationService organizationService;

    //todo IT VERY VERY BAD, need Session
    private User currentUser;
    private List<NotificationStatus> checkedMainListNotificStatuses;
    private boolean showProcessed; //=  is NOTIFICATION_STATUS = PROCESSED
    private List<FastFilter> list4FastFilter;
    private FastFilter currentFastFilter;
    private List<Notification> orgNotifications;
    private List<NotificationStatus> statuses4selectFilter;
    private List<Notification> showListNotifications;
    private String orderFieldName;

    int maxResult = 1;
    int navigationPages = 10;

    private void addAttributes_NotificationAndOther(Model model, List<Notification> notifications) {
        model.addAttribute("user", currentUser);
        model.addAttribute("user_list", userService.list());

        model.addAttribute("statuses4select", statuses4selectFilter);
        model.addAttribute("checked_list", checkedMainListNotificStatuses);
        model.addAttribute("showProcessed", showProcessed);
        model.addAttribute("listFastFilter", list4FastFilter);
        model.addAttribute("currentFastFilter", currentFastFilter);

        model.addAttribute("orderFieldName", orderFieldName);

//        Collections.sort(notifications, new Comparator<Notification>() {
//            @Override
//            public int compare(Notification o1, Notification o2) {
//                return (o1.getDateResponse().before(o2.getDateResponse()))? 1 : -1;
//            }
//        });
        model.addAttribute("notific_list", notifications);
    }

    @GetMapping("/")
    public String list(Model model) throws NoSuchFieldException {
        currentUser = userService.getById(9);//for my test

        statuses4selectFilter = new ArrayList<>();
        statuses4selectFilter.add(
                statusService.getById(ID_NOTIFICATION_STATUS_WAITING_TO_BE_PROCESSED));
        statuses4selectFilter.add(
                statusService.getById(ID_NOTIFICATION_STATUS_ANSWER_NOT_ACCEPTED));
        statuses4selectFilter.add(
                statusService.getById(ID_NOTIFICATION_STATUS_ANSWER_SENT));
        checkedMainListNotificStatuses = statuses4selectFilter;

        list4FastFilter = new ArrayList<>();
        list4FastFilter.add(NEW_NOTIFICATION);
        list4FastFilter.add(RUN_OUT_3_DAY_DATA_RESPONSE);
        list4FastFilter.add(DIR_LETTER);
        list4FastFilter.add(MULTIPLE_RETURNS);
        currentFastFilter = RUN_OUT_3_DAY_DATA_RESPONSE;

        showProcessed = false;
        orderFieldName = "dateResponse";

        //for Test (DELETE)
        Pagination<Notification> result = notificationService.listByFilterOrg_Order_Pagination(
                currentUser.getOrganization(),
                orderFieldName, true,
                new Pagination<Notification>(1, maxResult, navigationPages)
        );

        orgNotifications = result.getList();

        model.addAttribute("paginationProduct",result);

        // OR TAKE FROM DATABASE!
//        orgNotifications = notificationService.paginationListByOrgAndOrder()
//        orgNotifications = currentUser.getOrganization().getNotifications();
//        Collections.sort(orgNotifications, new Comparator<Notification>() {
//            @Override
//            public int compare(Notification o1, Notification o2) {
//                return (o1.getDateResponse().before(o2.getDateResponse()))? 1 : -1;
//            }
//        });

        addAttributes_NotificationAndOther(model, orgNotifications);
        return "cabinet/list";
    }

    @GetMapping("/productList")
    public String list(@RequestParam("page") int page,
                       @RequestParam("maxResult") int maxResult,
                       //TODO VERY STRONG BAND LOGIC BETWEEN LEVELs
//                       @RequestParam("orderFieldName") String orderFieldName,
                       Model model) {
        //for Test (DELETE)
        this.maxResult = maxResult;
        Pagination<Notification> result = notificationService.listByFilterOrg_Order_Pagination(
                currentUser.getOrganization(),
                orderFieldName, true,
                new Pagination<Notification>(page, maxResult, navigationPages)
        );
        orgNotifications = result.getList();

        model.addAttribute("paginationProduct",result);

        // OR TAKE FROM DATABASE!
//        orgNotifications = notificationService.paginationListByOrgAndOrder()
//        orgNotifications = currentUser.getOrganization().getNotifications();
//        Collections.sort(orgNotifications, new Comparator<Notification>() {
//            @Override
//            public int compare(Notification o1, Notification o2) {
//                return (o1.getDateResponse().before(o2.getDateResponse()))? 1 : -1;
//            }
//        });

        addAttributes_NotificationAndOther(model, orgNotifications);
        return "cabinet/list";
    }

    //todo add privilegii for GovernmentUsers
    @PostMapping("/selectUser")
    public String selectUser(@RequestParam("idSelectUser") int idSelectUser,
                             Model model) {
        currentUser = userService.getById(idSelectUser);
        checkedMainListNotificStatuses = statuses4selectFilter;
        showProcessed = false;

        // OR TAKE FROM DATABASE!
        orgNotifications = currentUser.getOrganization().getNotifications();
        Collections.sort(orgNotifications, new Comparator<Notification>() {
            @Override
            public int compare(Notification o1, Notification o2) {
                return (o1.getDateResponse().before(o2.getDateResponse()))? 1 : -1;
            }
        });

        addAttributes_NotificationAndOther(model, orgNotifications);
        return "cabinet/list";
    }

    @GetMapping("/filterByNotificStatus")
    public String filterByNotificStatus(HttpServletRequest request, HttpServletResponse response,
//                                        @RequestParam("id") int[] id,
//                                        @RequestParam("showProcessed") boolean theFlagArchive,
                                        Model model) {
        showListNotifications = new ArrayList<>();
        checkedMainListNotificStatuses = new ArrayList<>();
        List<NotificationStatus> checkedStatusList = new ArrayList<>();
        showProcessed = false;

        String[] ids = request.getParameterValues("id");
        boolean haveIdCheckedStatusList = (ids != null);

        String[] masShowProcessed = request.getParameterValues("showProcessed");
        boolean hasCheckedStatusProcessed = (masShowProcessed != null);

        String[] selectFastFilter = request.getParameterValues("selectFastFilter");
        boolean hasSelectedFastFilter = (masShowProcessed != null);

        if(haveIdCheckedStatusList || hasCheckedStatusProcessed){
            if (haveIdCheckedStatusList) {
                checkedMainListNotificStatuses = statusService.filterIds(ids);//MOCK//statuses4selectFilter;
                checkedStatusList.addAll(checkedMainListNotificStatuses);
            }
            if (hasCheckedStatusProcessed) {
                showProcessed = true;
                checkedStatusList.add(statusService.getById(ID_NOTIFICATION_STATUS_PROCESSED));
            } else {
                showProcessed = false;
            }
            showListNotifications = notificationService.filterOrgAndNotificStatuses(
                    currentUser.getOrganization(),
                    checkedStatusList);
        }
        addAttributes_NotificationAndOther(model, showListNotifications);
        return "cabinet/list";
    }
}