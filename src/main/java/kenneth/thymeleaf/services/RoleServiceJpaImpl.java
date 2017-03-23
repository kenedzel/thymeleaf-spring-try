package kenneth.thymeleaf.services;

import kenneth.thymeleaf.models.Role;
import kenneth.thymeleaf.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 3/21/17.
 */
@Service
@Primary
public class RoleServiceJpaImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findByRole(String role) {
        return this.roleRepository.findByRole(role);
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return this.roleRepository.findOne(id);
    }

    @Override
    public Role create(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role edit(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        this.roleRepository.delete(id);
    }
}
