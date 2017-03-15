package kenneth.thymeleaf.services;

import kenneth.thymeleaf.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 3/15/17.
 */
@Service
public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product create(Product product);
    Product edit(Product product);
    void deleteById(Long id);
}
