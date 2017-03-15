package kenneth.thymeleaf.services;

import kenneth.thymeleaf.models.User;
import kenneth.thymeleaf.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 3/15/17.
 */
@Service
@Primary
public class UserServiceJpaImpl implements UserService{

    UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.delete(id);
    }
}
