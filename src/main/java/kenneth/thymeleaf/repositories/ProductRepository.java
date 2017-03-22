package kenneth.thymeleaf.repositories;

import kenneth.thymeleaf.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kenneth on 3/15/17.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query(value = "SELECT * FROM products WHERE ACTIVE = 1", nativeQuery = true)
    List<Product> findAllByActive();
}
