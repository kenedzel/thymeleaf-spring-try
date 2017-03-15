package kenneth.thymeleaf.repositories;

import kenneth.thymeleaf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kenneth on 3/15/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
