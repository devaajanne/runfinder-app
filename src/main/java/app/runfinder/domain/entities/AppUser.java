package app.runfinder.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

// We use @Entity annotation to annotate this class as an entity
// Entities are tables in relational databases
@Entity

// @Table annotation renames the entity's table in relational database
@Table(name = "app_users")
public class AppUser {

    // @Id annotates this attribute as the table's id column
    @Id
    // @GeneratedValue generates unique primary keys for all entity objects
    // GenerationType.IDENTITY auto-increments the id value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column renames the column in the relational database
    // Not always needed; used to have more control over the database colum naming
    @Column(name = "app_user_id")
    private Long appUserId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // @ManyToOne annotes a many to one relation between two tables
    @ManyToOne
    // @JoinColumn annotes which columns are joined
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "password")
    private String password;

    // Empty constructor
    public AppUser() {
    }

    // Constructor with attributes
    public AppUser(String username, String firstName, String lastName, String email, Role role, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Getters and setters

    public Long getAppUserId() {
        return this.appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public AppUser appUserId(Long appUserId) {
        setAppUserId(appUserId);
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AppUser username(String username) {
        setUsername(username);
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public AppUser firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AppUser lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUser email(String email) {
        setEmail(email);
        return this;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AppUser role(Role role) {
        setRole(role);
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUser password(String password) {
        setPassword(password);
        return this;
    }

    // toString method
    @Override
    public String toString() {
        return "{" +
                " appUserId='" + getAppUserId() + "'" +
                ", username='" + getUsername() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                ", role='" + getRole() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }
}
