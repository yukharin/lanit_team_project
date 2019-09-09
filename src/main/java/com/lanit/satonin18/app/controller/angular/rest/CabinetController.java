package com.lanit.satonin18.app.controller.angular.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.authorization.UserAccount;
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
import com.lanit.satonin18.app.utils.CheckOnBuildJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Controller("angularCabinetController")
@RequestMapping("/angular/cabinet")
public class CabinetController {

    @Autowired
    private UserService userService;
    @Autowired
    private CabinetService cabinetService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/filters")
    public Cabinet4renderHtml filters(
            @AuthenticationPrincipal UserAccount userAccount,
            HttpSession session,
            @RequestBody FilterForm form) throws JsonProcessingException {

        validateAndSetDefaultVars(form);

        CabinetSessionState state = (CabinetSessionState)  session.getAttribute("cabinetState");
        if(state == null){
            state = createNewDefault4watchCabinetState();
        }
        state.setFilterForm(form);
        state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);

        return cabinet4renderHtml(userAccount,session);
    }

    @PostMapping("/pagination")
    public Cabinet4renderHtml pagination(
            @AuthenticationPrincipal UserAccount userAccount,
            HttpSession session,
            @RequestBody PaginationForm form) throws JsonProcessingException {
        validateAndSetDefaultVars(form);

        CabinetSessionState state = (CabinetSessionState) session.getAttribute("cabinetState");
        if (state == null) {
            state = createNewDefault4watchCabinetState();
        }
        state.setPaginationForm(form);
        if (state.getPaginationForm().getMaxResult() != form.getMaxResult())
            state.getPaginationForm().setPage(COMMON_DEFAULT_VARS.FIRST_PAGE);

        session.setAttribute("cabinetState", state);

        return cabinet4renderHtml(userAccount, session);
    }

    @GetMapping("/cabinet4renderHtml")
    public /*@ResponseBody*/ Cabinet4renderHtml cabinet4renderHtml(
            @AuthenticationPrincipal UserAccount userAccount,
            HttpSession session) throws JsonProcessingException {
        User currentUser = userAccount.getUser();

        CabinetSessionState state = (CabinetSessionState) session.getAttribute("cabinetState");
        if(state == null){
            state = createNewDefault4watchCabinetState();
        }
        Cabinet4renderHtml render = new Cabinet4renderHtml(state, currentUser);
        cabinetService.executeQuery(render, currentUser);

        //todo replace
        ArrayList<Integer> ids = new ArrayList<>();
        for (Status status:render.getCheckedMainListNotificStatuses()) {
            ids.add(status.getId());
        }
        render.setNewCheckedMainListNotificStatusesId(ids);

        new CheckOnBuildJson().check(render);

        return render;
    }

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
