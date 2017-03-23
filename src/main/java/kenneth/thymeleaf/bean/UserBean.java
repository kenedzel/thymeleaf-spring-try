package kenneth.thymeleaf.bean;

import kenneth.thymeleaf.models.Role;

import java.util.Set;

/**
 * Created by kenneth on 3/15/17.
 */
public class UserBean {

    private Long user_id;

    private String name;

    private String email;

    private String password;

    private Set<Role> roles;

    public UserBean() {
    }

    public UserBean(Long user_id, String name, String email, String password, Set<Role> roles) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
