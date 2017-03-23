package kenneth.thymeleaf.services;

import kenneth.thymeleaf.models.Role;
import kenneth.thymeleaf.models.User;
import kenneth.thymeleaf.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kenneth on 3/23/17.
 */
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null)
        {
            LOGGER.debug("Email not found.");
            return null;
        }
        System.out.println("user from input: " + user);
        LOGGER.debug("user from input " + user);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private Set<GrantedAuthority> getAuthorities(User user)
    {
            Set<GrantedAuthority> authorities = new HashSet<>();

            for(Role role : user.getRoles())
            {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
                authorities.add(grantedAuthority);
            }
            LOGGER.debug("user's authority is/are" + authorities.toString());
            return authorities;
    }


}
