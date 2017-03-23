package kenneth.thymeleaf.services;

import kenneth.thymeleaf.models.Role;

import java.util.List;

/**
 * Created by kenneth on 3/21/17.
 */
public interface RoleService {

    List <Role> findByRole(String role);
    List<Role> findAll();
    Role findById(Long id);
    Role create(Role role);
    Role edit(Role role);
    void deleteById(Long id);
}
