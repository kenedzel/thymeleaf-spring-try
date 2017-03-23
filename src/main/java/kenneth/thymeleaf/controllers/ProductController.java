package kenneth.thymeleaf.controllers;

import kenneth.thymeleaf.bean.ProductBean;
import kenneth.thymeleaf.models.Product;
import kenneth.thymeleaf.services.ProductService;
import kenneth.thymeleaf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        product.setId(productBean.getId());
        product.setName(productBean.getName());
        product.setDescription(productBean.getDescription());
        product.setPrice(productBean.getPrice());
        product.setActive(productBean.isActive());

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
                bean.setActive(product.isActive());
                System.out.println(bean);
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
        bean.setActive(product.isActive());

        return bean;
    }

    @RequestMapping(value = "/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Model model)
    {
        model.addAttribute("product", productService.findById(id));
        return "products/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model)
    {
        model.addAttribute("product", new Product());
        return "products/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@ModelAttribute ProductBean productBean, Model model)
    {
        System.out.println("Invoking save product");
        System.out.println(productBean);
        Product product = prepareModel(productBean);
        productService.create(product);
        System.out.println(product);
        model.addAttribute("product", productBean);
        return "redirect:/products/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") Long id, @ModelAttribute ProductBean productBean)
    {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("product", prepareProductBean(productService.findById(productBean.getId())));
        model.put("products", prepareListofBean(productService.findAll()));
        return new ModelAndView("products/update", model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable(value = "id") Long id, @ModelAttribute ProductBean productBean, Model model)
    {
        System.out.println(productBean);
        model.addAttribute("product",prepareProductBean(productService.findById(id)));
        Product product = prepareModel(productBean);
        System.out.println(product);
        productService.edit(product);
        System.out.println("Invoking update product.");
        return "redirect:/products/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id") Long id, @ModelAttribute ProductBean productBean)
    {
        System.out.println("invoking delete ");
        Map<String, Object> model = new HashMap<>();
        System.out.println("delete: " + productBean);
        System.out.println("Idddddddddddd: " + id);
        productService.deleteById(id);
        model.put("product", null);
        model.put("products",prepareListofBean(productService.findAll()));

        return new ModelAndView("redirect:/products/", model);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listAll()
    {
        Map<String, Object> model = new HashMap<>();
        model.put("products", prepareListofBean(productService.findAll()));
        return new ModelAndView("products/index", model);
    }



}
