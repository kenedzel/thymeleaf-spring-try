package kenneth.thymeleaf.services;

import org.springframework.stereotype.Service;

/**
 * Created by kenneth on 3/21/17.
 */
@Service
public interface SecurityService {

    String findLoggedInUsername();
    void autologin(String username, String password);
}
