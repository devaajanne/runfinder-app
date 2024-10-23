package app.runfinder.domain.classes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignUpForm {

    @Size(min = 4, max = 25, message = "Your username must be between 4 and 25 characters long")
    private String username = "";

    @NotEmpty(message = "Please type your first name")
    private String firstName = "";

    @NotEmpty(message = "Please type your last name")
    private String lastName = "";

    @NotEmpty(message = "Please provide a valid email address")
    @Email(message = "Please provide a valid email address")
    private String email = "";

    @Size(min = 8, message = "Your password must be at least 8 characters long")
    @NotEmpty(message = "Please type your password")
    private String password = "";

    @Size(min = 8, message = "Your password must be at least 8 characters long")
    @NotEmpty(message = "Please re-type your password")
    private String passwordCheck = "";

    public SignUpForm() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SignUpForm username(String username) {
        setUsername(username);
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public SignUpForm firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SignUpForm lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SignUpForm email(String email) {
        setEmail(email);
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUpForm password(String password) {
        setPassword(password);
        return this;
    }

    public String getPasswordCheck() {
        return this.passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public SignUpForm passwordCheck(String passwordCheck) {
        setPasswordCheck(passwordCheck);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", passwordCheck='" + getPasswordCheck() + "'" +
                "}";
    }
}
