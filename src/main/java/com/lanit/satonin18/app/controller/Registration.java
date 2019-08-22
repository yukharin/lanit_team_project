package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.objects.input.dto.valid.RegistrationDto;
import com.lanit.satonin18.app.service.app_service.RegistrationService;
import com.lanit.satonin18.app.service.entities_service.authorization.UserAccountService;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller("registrationController")
//@RequestMapping("")
public class Registration {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/registration")
    public ModelAndView formPage(
            HttpSession session,
            Model model) {
        addAttributes(model);
        return new ModelAndView(
                "/registrationForm",
                "registrationDto",
                new RegistrationDto());
    }

    @PostMapping("/registration")
    public ModelAndView save(
            Model model, HttpSession session,
//            RedirectAttributes redir,
            @Valid @ModelAttribute(name = "registrationDto") RegistrationDto registrationDto,
            BindingResult bindingResult) {
        UserAccount accountToCheck = userAccountService.findByUsername(registrationDto.getUsername());
        if (accountToCheck != null) {
            FieldError usernameError = new FieldError("error.user", "username", "Такой логин уже существует в системе");
            bindingResult.addError(usernameError);
        }
        if (bindingResult.hasErrors()) {
            addAttributes(model);
            return new ModelAndView(
//                    "redirect:/cabinet/the_notification/add_action/register",
                    "/registrationForm",
                    "registrationDto",
                    registrationDto
            );
        }
        registrationService.register(registrationDto);

        return new ModelAndView(
                "redirect:/login?messageRegistrationSuccess"
//                "/q"
        );
    }

    private void addAttributes(Model model) {
        model.addAttribute("listOrg", organizationService.findAll());
    }
}
