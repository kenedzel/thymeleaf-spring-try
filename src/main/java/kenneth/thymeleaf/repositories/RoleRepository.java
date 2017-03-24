package kenneth.thymeleaf.repositories;

import kenneth.thymeleaf.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kenneth on 3/21/17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    List<Role> findByRole(String role);

}
