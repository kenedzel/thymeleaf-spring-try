package kenneth.thymeleaf.controllers;

import kenneth.thymeleaf.bean.ProductBean;
import kenneth.thymeleaf.bean.UserBean;
import kenneth.thymeleaf.models.Product;
import kenneth.thymeleaf.models.User;
import kenneth.thymeleaf.services.ProductService;
import kenneth.thymeleaf.services.UserService;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenneth on 3/14/17.
 */
@Controller
public class IndexController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    UserController userController;

    public String name="Default Name";

    public String bannerTitle = "Thymeleaf";


    private List<ProductBean> prepareListofBean(@RequestBody List<Product> products)
    {
        List<ProductBean> beans = null;
        if(products != null && !products.isEmpty())
        {
            beans = new ArrayList<ProductBean>();
            ProductBean bean = null;
            for(Product product : products)
            {
                bean = new ProductBean();
                bean.setId(product.getId());
                bean.setName(product.getName());
                bean.setDescription(product.getDescription());
                bean.setPrice(product.getPrice());
                beans.add(bean);
            }
        }
        return beans;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("role value: " + auth.getAuthorities());
        model.addAttribute("authRole", auth.getAuthorities());
        model.addAttribute("authName", auth.getName());
        model.addAttribute("products", prepareListofBean(productService.findAllActive()));
        model.addAttribute("name", this.name);
        model.addAttribute("bannerTitle", this.bannerTitle);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login()
    {
        return "users/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String auth()
    {
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model)
    {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        model.addAttribute("authRole", auth.getAuthorities());
        model.addAttribute("user", new User());
        return "/users/registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSave(@ModelAttribute UserBean userBean , Model model)
    {
        User user = userController.prepareModel(userBean);

        userService.create(user);
        System.out.println("created user: " + user);
        //temporary
        return "/users/registration";
    }

    @RequestMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("products", prepareListofBean(productService.findAll()));
        model.addAttribute("name", this.name);
        model.addAttribute("bannerTitle", this.bannerTitle);
        return "index";
    }
}
