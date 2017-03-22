package kenneth.thymeleaf.controllers;

import kenneth.thymeleaf.bean.ProductBean;
import kenneth.thymeleaf.models.Product;
import kenneth.thymeleaf.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenneth on 3/14/17.
 */
@Controller
public class IndexController {

    @Autowired
    ProductService productService;

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
        model.addAttribute("products", prepareListofBean(productService.findAllActive()));
        model.addAttribute("name", this.name);
        model.addAttribute("bannerTitle", this.bannerTitle);
        return "index";
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
