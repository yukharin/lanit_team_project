import com.lanit.satonin18.model.entity.Organization;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("organizationJSP", new Organization());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="organizationJSP" action="check-organization">,
    то попадем вот сюда
     */
    @RequestMapping(value = "/check-organization")
    public ModelAndView checkOrganization(@ModelAttribute("organizationJSP") Organization organization) {
        ModelAndView modelAndView = new ModelAndView();

        //имя представления, куда нужно будет перейти
        modelAndView.setViewName("secondPage");

        //записываем в атрибут organizationJSP (используется на странице *.jsp объект organization
        modelAndView.addObject("organizationJSP", organization);

        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }

}