package app.runfinder.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "roleName")
    private String roleName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<AppUser> appUsers;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
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

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role roleName(String roleName) {
        setRoleName(roleName);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " roleId='" + getRoleId() + "'" +
            ", roleName='" + getRoleName() + "'" +
            "}";
    }
}
