package kenneth.thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kenneth on 3/14/17.
 */
@Controller
public class IndexController {

    public String name="Default Name";

    public String bannerTitle = "Thymeleaf";
    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("name", this.name);
        model.addAttribute("bannerTitle", this.bannerTitle);
        return "index";
    }
}
