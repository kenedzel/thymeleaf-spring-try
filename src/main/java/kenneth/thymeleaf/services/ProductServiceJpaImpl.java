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
    public List<Product> findAllActive() {
        return this.productRepository.findAllByActive();
    }

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
        Product updatedProduct = productRepository.findOne(product.getId());

        System.out.println("PRODUCT FROM EDIT PAGE: " + product);
        updatedProduct.setId(product.getId());
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setActive(product.isActive());
        System.out.println("UPDATED PRODUCT: " +updatedProduct);
        return this.productRepository.save(updatedProduct);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.delete(id);
    }
}
