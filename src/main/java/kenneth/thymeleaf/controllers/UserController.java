package kenneth.thymeleaf.controllers;

import kenneth.thymeleaf.bean.ProductBean;
import kenneth.thymeleaf.bean.UserBean;
import kenneth.thymeleaf.models.Product;
import kenneth.thymeleaf.models.User;
import kenneth.thymeleaf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by kenneth on 3/15/17.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;

    private User prepareModel(UserBean userBean)
    {
        User user = new User();
        user.setUser_id(userBean.getUser_id());
        user.setName(userBean.getName());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());

        return user;
    }

    private List<UserBean> prepareListofBean(List<User> users)
    {
        List<UserBean> beans = null;
        if(users != null && !users.isEmpty())
        {
            beans = new ArrayList<UserBean>();
            UserBean bean = null;
            for(User user : users)
            {
                bean = new UserBean();
                bean.setUser_id(user.getUser_id());
                bean.setName(user.getName());
                bean.setEmail(user.getEmail());
                bean.setPassword(user.getPassword());
                beans.add(bean);
            }
        }
        return beans;
    }

    private UserBean prepareUserBean(User user)
    {
        UserBean bean = new UserBean();
        bean.setUser_id(user.getUser_id());
        bean.setName(user.getName());
        bean.setEmail(user.getEmail());
        bean.setPassword(user.getPassword());

        return bean;
    }


    @RequestMapping("/view")
    public String getUser()
    {
        return "user_show";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model)
    {
        model.addAttribute("user", new User());
        return "user_add";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute UserBean userBean, Model model)
    {
        User user = prepareModel(userBean);
        userService.create(user);
        model.addAttribute("user", userBean);

        return new ModelAndView("redirect:/user/add");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute UserBean userBean)
    {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("user", prepareUserBean(userService.findById(userBean.getUser_id())));
        model.put("users", prepareListofBean(userService.findAll()));

        return new ModelAndView("product_add", model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@ModelAttribute UserBean userBean)
    {
        Map<String, Object> model = new HashMap<String, Object>();

        userService.deleteById(userBean.getUser_id());
        model.put("product", null);
        model.put("products",prepareListofBean(userService.findAll()));

        return new ModelAndView("product_add", model);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView listAll()
    {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("users", prepareListofBean(userService.findAll()));

        return new ModelAndView("users", model);
    }

}
