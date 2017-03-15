package kenneth.thymeleaf.services;


import kenneth.thymeleaf.models.Product;
import kenneth.thymeleaf.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 3/15/17.
 */
@Service
@Primary
public class ProductServiceJpaImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findOne(id);
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product edit(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.delete(id);
    }
}
