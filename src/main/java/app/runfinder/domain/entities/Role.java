package app.runfinder.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String role;

    // @JsonIgnore annotation to stop endless loops from GET requests
    @JsonIgnore
    // @OneToMany annotates a one to many relation between two tables
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<AppUser> appUsers;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role roleId(Long roleId) {
        setRoleId(roleId);
        return this;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role role(String role) {
        setRole(role);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " roleId='" + getRoleId() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
