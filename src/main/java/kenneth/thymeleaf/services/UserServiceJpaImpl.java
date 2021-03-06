package kenneth.thymeleaf.services;

import kenneth.thymeleaf.models.Role;
import kenneth.thymeleaf.models.User;
import kenneth.thymeleaf.repositories.RoleRepository;
import kenneth.thymeleaf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by kenneth on 3/15/17.
 */
@Service
@Primary
public class UserServiceJpaImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);

//        user.setRoles(new HashSet<>(roleRepository.findByRole("NORMAL")));
        // check return type of role repository on tu
        user.setRoles(new HashSet<>(roleRepository.findByRole("NORMAL")));
        return null;
//        return this.userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepository.save(user);
    }
}
