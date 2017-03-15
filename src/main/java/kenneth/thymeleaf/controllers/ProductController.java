package kenneth.thymeleaf.controllers;

import kenneth.thymeleaf.bean.ProductBean;
import kenneth.thymeleaf.models.Product;
import kenneth.thymeleaf.services.ProductService;
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
 * Created by kenneth on 3/14/17.
 */
@Controller
@RequestMapping("/products")
@SessionAttributes("products")
public class ProductController {

    @Autowired
    ProductService productService;

    private Product prepareModel(ProductBean productBean)
    {
        Product product = new Product();
        product.setId(null);
        product.setName(productBean.getName());
        product.setDescription(productBean.getDescription());
        product.setPrice(productBean.getPrice());

        return product;
    }

    private List<ProductBean> prepareListofBean(List<Product> products)
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

    private ProductBean prepareProductBean(Product product)
    {
        ProductBean bean = new ProductBean();
        bean.setId(product.getId());
        bean.setName(product.getName());
        bean.setDescription(product.getDescription());
        bean.setPrice(product.getPrice());

        return bean;
    }

    @RequestMapping("/view")
    public String getProduct()
    {
        return "product_show";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model)
    {
        model.addAttribute("product", new Product());
        return "product_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute ProductBean productBean, Model model)
    {
        System.out.println("Invoking save product");
        System.out.println(productBean);
        Product product = prepareModel(productBean);
        productService.create(product);

        model.addAttribute("product", productBean);
        return new ModelAndView("redirect:/product/add");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute ProductBean productBean)
    {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("product", prepareProductBean(productService.findById(productBean.getId())));
        model.put("products", prepareListofBean(productService.findAll()));
        return new ModelAndView("product_add", model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(ProductBean productBean)
    {
        Map<String, Object> model = new HashMap<String, Object>();
        productService.deleteById(productBean.getId());
        model.put("product", null);
        model.put("products",prepareListofBean(productService.findAll()));

        return new ModelAndView("product_add", model);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listAll()
    {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("products", prepareListofBean(productService.findAll()));
        return new ModelAndView("products", model);
    }



}
