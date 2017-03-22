package kenneth.thymeleaf.services;

import kenneth.thymeleaf.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 3/15/17.
 */
@Service
public interface UserService {

    List<User> findAll();
    User findUserByEmail(String email);
    User findById(Long id);
    User create(User user);
    User edit(User user);
}
