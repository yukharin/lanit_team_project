package com.lanit.satonin18.app.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.satonin18.app.TestUser;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.input.form.FilterForm;
import com.lanit.satonin18.app.objects.input.form.OrderByForm;
import com.lanit.satonin18.app.objects.input.form.PaginationForm;
import com.lanit.satonin18.app.objects.output.Cabinet4renderHtml;
import com.lanit.satonin18.app.objects.property_in_future.COMMON_DEFAULT_VARS;
import com.lanit.satonin18.app.objects.property_in_future.DEFAULT_CABINET_VARS;
import com.lanit.satonin18.app.objects.state4session.CabinetSessionState;
import com.lanit.satonin18.app.service.app_service.CabinetService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@Controller("angularController")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/angular")
public class Angular {

    @Autowired
    private UserService userService;
    @Autowired
    private CabinetService cabinetService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/filters")
    public Cabinet4renderHtml filters(
            HttpSession session,
            @RequestBody FilterForm form) throws JsonProcessingException {

        validateAndSetDefaultVars(form);

        CabinetSessionState state = (CabinetSessionState)  session.getAttribute("cabinetState");
        if(state == null){
            state = createNewDefault4watchCabinetState();
//            session.setAttribute("cabinetState", state);
        }
        state.setFilterForm(form);
        state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);

        Integer userId = (Integer) session.getAttribute("userId");
//        return cabinet4renderHtml(userId, session);
        return cabinet4renderHtml(1, session);
//        return "OK";
    }

    @PostMapping("/pagination")
    public Cabinet4renderHtml pagination(
            HttpSession session,
            @RequestBody PaginationForm form) throws JsonProcessingException {
        validateAndSetDefaultVars(form);

        CabinetSessionState state = (CabinetSessionState) session.getAttribute("cabinetState");
        if (state == null) {
            state = createNewDefault4watchCabinetState();
//            session.setAttribute("cabinetState", state);
        }
        state.setPaginationForm(form);
        if (state.getPaginationForm().getMaxResult() != form.getMaxResult())
            state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);

        Integer userId = (Integer) session.getAttribute("userId");
//        return cabinet4renderHtml(userId, session);
        return cabinet4renderHtml(1, session);
//        return "OK";
    }



    @RequestMapping("/test_user")
    public /*@ResponseBody*/ TestUser test_user() throws JsonProcessingException {
        TestUser testUser = new TestUser("ht", 3);

        testOnBuildJson(testUser);

        return testUser;
    }

    @RequestMapping("/test_users")
    public /*@ResponseBody*/ List<TestUser> test_users() throws JsonProcessingException {
        TestUser testUser1 = new TestUser("111", 1);
        TestUser testUser2 = new TestUser("22", 22);
        TestUser testUser3 = new TestUser("вагон", 33);
        ArrayList<TestUser> testUsers = new ArrayList<>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);
        testUsers.add(testUser3);

        testOnBuildJson(testUsers);

        return testUsers;
    }

    @RequestMapping("/user")
    public /*@ResponseBody*/ User user() throws JsonProcessingException {
        User user = userService.findById(1);

        testOnBuildJson(user);

        return user;
    }
    @RequestMapping("/get_user")
    public /*@ResponseBody*/ User get_user(int id) throws JsonProcessingException {
        User user = userService.findById(id);

        testOnBuildJson(user);

        return user;
    }

    @RequestMapping("/users")
    public /*@ResponseBody*/ List<User> users() throws JsonProcessingException {
        List<User> users = userService.findAll();

        testOnBuildJson(users);

        return users;
    }

    @RequestMapping("/notification")
    public /*@ResponseBody*/ Notification notification() throws JsonProcessingException {
        Notification notification = notificationService.findById(1);

        testOnBuildJson(notification);

        return notification;
    }

    @RequestMapping("/notifications")
    public /*@ResponseBody*/ List<Notification> notifications(
//            int userId,
//            HttpSession session
    ) throws JsonProcessingException {
//        User currentUser = userService.findById(userId);
        List<Notification> notifications = notificationService.findAll();

        testOnBuildJson(notifications);

        return notifications;
    }

    @RequestMapping("/user_notifications")
    public /*@ResponseBody*/ List<Notification> user_notifications(
                int userId,
                HttpSession session) throws JsonProcessingException {
        User currentUser = userService.findById(userId);

        CabinetSessionState state = (CabinetSessionState) session.getAttribute("cabinetState");
        if(state == null){
            state = createNewDefault4watchCabinetState();
//            session.setAttribute("cabinetState", state);
        }
        Cabinet4renderHtml render = new Cabinet4renderHtml(state);
        cabinetService.executeQuery(render, currentUser);

        List<Notification> notifications = render.getPageImpl().getContent();

//        addAttributes_Notification(model, currentUser, render);
//        return "cabinet/notificationsForm";

        testOnBuildJson(notifications);

        return notifications;
    }

    @RequestMapping("/cabinet4renderHtml")
    public /*@ResponseBody*/ Cabinet4renderHtml cabinet4renderHtml(
            int userId,
            HttpSession session) throws JsonProcessingException {
        User currentUser = userService.findById(userId);
        session.setAttribute("userId", userId);

        CabinetSessionState state = (CabinetSessionState) session.getAttribute("cabinetState");
        if(state == null){
            state = createNewDefault4watchCabinetState();
//            session.setAttribute("cabinetState", state);
        }
        Cabinet4renderHtml render = new Cabinet4renderHtml(state);
        cabinetService.executeQuery(render, currentUser);

//        List<Notification> notifications = render.getPageImpl().getContent();

//        addAttributes_Notification(model, currentUser, render);
//        return "cabinet/notificationsForm";

        ArrayList<Integer> ids = new ArrayList<>();
        for (Status status:render.getCheckedMainListNotificStatuses()) {
            ids.add(status.getId());
        }
        render.setNewCheckedMainListNotificStatusesId(ids);

        testOnBuildJson(render);

        return render;
    }

    private void testOnBuildJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(obj);
        System.err.println("JSON: " + str);
    }

    //test
//    @RequestMapping("/notes")
//    public /*@ResponseBody*/ TestUser notes() {
//            HttpSession session,
////            @AuthenticationPrincipal UserAccount userAccount,
//            Model model){
//        User currentUser = userAccount.getUser();
//
//        CabinetSessionState state = (CabinetSessionState)  session.getAttribute("cabinetState");
//        if(state == null){
//            state = createNewDefault4watchCabinetState();
//            session.setAttribute("cabinetState", state);
//        }
//        Cabinet4renderHtml render = new Cabinet4renderHtml(state);
//        cabinetService.executeQuery(render, currentUser);
//
//        addAttributes_Notification(model, currentUser, render);
////        return "cabinet/notificationsForm";
//        return null;
//    }

    private CabinetSessionState createNewDefault4watchCabinetState() {
        FilterForm filterForm = new FilterForm();
        PaginationForm paginationForm = new PaginationForm();
        OrderByForm orderByForm = new OrderByForm();

        validateAndSetDefaultVars(filterForm);
        filterForm.setIdFilterStatus(Status.getAllId());
        validateAndSetDefaultVars(paginationForm);
        validateAndSetDefaultVars(orderByForm);

        CabinetSessionState state = new CabinetSessionState();
        state.setFilterForm(filterForm);
        state.setPaginationForm(paginationForm);
        state.setOrderByForm(orderByForm);
        return state;
    }

    private void validateAndSetDefaultVars(FilterForm form) {
        if(form.getIdFilterStatus() == null) form.setIdFilterStatus(Collections.EMPTY_LIST);//если ничего передали, значит пусто
        if(form.getShowArchive() == null) form.setShowArchive(DEFAULT_CABINET_VARS.SHOW_ARCHIVE);//если параметр не пришел, то false, если пришел( то приходит только true)
    }
    private void validateAndSetDefaultVars(PaginationForm form) {
        if(form.getMaxResult() == null) form.setMaxResult(COMMON_DEFAULT_VARS.MAX_RESULT);
        if(form.getPage() == null) form.setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);
    }
    private void validateAndSetDefaultVars(OrderByForm form) {
        if(form.getDesc() == null) form.setDesc(COMMON_DEFAULT_VARS.DESC);
        if(form.getOrderFieldName() == null) form.setOrderFieldName(DEFAULT_CABINET_VARS.ORDER_FIELD_NAME);
    }
}
