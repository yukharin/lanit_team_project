package com.lanit.lkz_project.controllers.mvc_controllers;

//@Controller
//public class HomePageController {
//
//    private static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);
//
//    @Value("${login_page}")
//    private String login_page;
//    @Value("${registration_page}")
//    private String registration_page;
//
//
//    @Autowired
//    private OrganizationService organizationService;
//
//    @Autowired
//    private UserAuthorizationService userServiceAuthorization;
//
//
//    @RequestMapping(path = "/", produces = "text/html; charset=UTF-8")
//    public ModelAndView toLoginPage(ModelAndView modelAndView) {
//        logger.trace("sending loginPage.html");
//        modelAndView.setViewName(login_page);
//        return modelAndView;
//    }
//
//    @GetMapping("/registration/")
//    public ModelAndView toRegistrationPage(ModelAndView modelAndView,
//                                           @ModelAttribute User user) {
//        List<Organization> organizations = organizationService.organizations();
//        modelAndView.addObject("organizations", organizations);
//        modelAndView.setViewName(registration_page);
//        logger.trace("Adding model attribute - list of all organizations, then sending userRegistrationPage.html");
//        return modelAndView;
//    }
//
//
//    @PostMapping("/success")
//    public String login(@NonNull @RequestParam String username,
//                        @NonNull @RequestParam String password,
//                        HttpSession session) {
//        @NonNull User user = userServiceAuthorization.authorize(username, password);
//        session.setAttribute("login", username);
//        session.setAttribute("password", password);
//        logger.info("User: " + user + " authorized, sending account.html");
//        return "redirect:/account/";
//    }
//
//    @GetMapping("/logout/")
//    public String logout(@NonNull @SessionAttribute String login,
//                         @NonNull @SessionAttribute String password,
//                         HttpSession session) {
//        @NonNull User user = userServiceAuthorization.authorize(login, password);
//        session.removeAttribute("login");
//        session.removeAttribute("password");
//        logger.info("User: " + user + " made a logout, redirecting to main page");
//        return "redirect:/";
//    }
//
//
//}
