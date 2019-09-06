package com.lanit.satonin18.app.controller.rest.cross;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.enum_type.ActionType;
import com.lanit.satonin18.app.entity.enum_type.Status;
import com.lanit.satonin18.app.objects.input.dto.valid.ActionPortionDto;
import com.lanit.satonin18.app.objects.output.AddAction4renderHtml;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@Controller("angularAddActionController")
@RequestMapping("/angular/cabinet/the_notification/add_action")
public class AddAction {
    
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/addAction4renderHtml")
    public AddAction4renderHtml formPage(
            HttpSession session,
            @RequestParam int notificationId,
            Model model) throws JsonProcessingException {
        User currentUser = userService.findById(1);
//        Integer notificationId = (Integer) session.getAttribute("notificationId");
        Notification currentNotification = notificationService.findById(notificationId);
        AddAction4renderHtml render = new AddAction4renderHtml(currentUser, currentNotification, currentNotification.getOrganization().getUsers());

        testOnBuildJson(render);

        return render;
//        addAttribute(model, currentUser, currentNotification);
//        return new ModelAndView(
//                "cabinet/the_notification/add_actionForm",
//                "actionPortionDto",
//                new ActionPortionDto());
    }

    @PostMapping("/save")
    public boolean save(
            Model model, HttpSession session,
            @Valid @RequestBody ActionPortionDto actionPortionDto,
            BindingResult bindingResult) {
//            redir.addAttribute("actionPortionDto", actionPortionDto);
        User currentUser = userService.findById(1);
//            Integer notificationId = (Integer) session.getAttribute("notificationId");
        Notification currentNotification = notificationService.findById(actionPortionDto.getNotificationId());

        if (bindingResult.hasErrors()) {
//            addAttribute(model, currentUser, currentNotification);
//            return new ModelAndView(
//                    "cabinet/the_notification/add_actionForm",
//                    "actionPortionDto",
//                    actionPortionDto
//            );
            return false;
        }
        ActionType actionType = ActionType.getById(actionPortionDto.getIdActionType());
        User userImplementor = userService.findById(actionPortionDto.getIdUserImplementor());
        Status status = Status.getById(actionPortionDto.getIdNotificationStatus());

        long timeNow = System.currentTimeMillis();
        Timestamp now = new Timestamp(timeNow);

        Action actionNew = new Action();
        actionNew.setNotification(currentNotification);//can be add in inside: notification.getActions().add(THIS);
        actionNew.setActionType(actionType);
        actionNew.setUserByIdImplementor(userImplementor);
        actionNew.setStatusAfterProcessing(status);
        actionNew.setDate(now);
        actionNew.setContent(actionPortionDto.getContent());

        actionService.save(actionNew);

        currentNotification.getActions().add(actionNew);
        currentNotification.setStatus(actionNew.getStatusAfterProcessing());

        notificationService.save(currentNotification);//update

//        return new ModelAndView(
//                "redirect:/cabinet/the_notification/actions"
//        );
        return true;
    }

//    private void addAttribute(Model model, User currentUser, Notification currentNotification) {
//        model.addAttribute("user", currentUser);
//        model.addAttribute("currentNotification", currentNotification);
//        model.addAttribute("listActionType", Arrays.asList(ActionType.values()));
//        model.addAttribute("listStatus", Arrays.asList(Status.values()));
//    }

    private void testOnBuildJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(obj);
        System.err.println("JSON: " + str);
    }
}
