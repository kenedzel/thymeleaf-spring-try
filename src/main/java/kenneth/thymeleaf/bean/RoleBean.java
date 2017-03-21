package kenneth.thymeleaf.bean;

/**
 * Created by kenneth on 3/21/17.
 */
public class RoleBean {

    private Long role_id;

    private String role;

    public RoleBean() {
    }

    public RoleBean(Long role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "RoleBean{" +
                "role_id=" + role_id +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
