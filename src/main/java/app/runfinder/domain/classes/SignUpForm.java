package app.runfinder.domain.classes;

public class SignUpForm {

    private String username = "";

    private String firstName = "";

    private String lastName = "";

    private String email = "";

    private String role = "USER";

    private String password = "";

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

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SignUpForm role(String role) {
        setRole(role);
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
                ", role='" + getRole() + "'" +
                ", password='" + getPassword() + "'" +
                ", passwordCheck='" + getPasswordCheck() + "'" +
                "}";
    }
}
