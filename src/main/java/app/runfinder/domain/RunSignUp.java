package app.runfinder.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "runSignUps")
public class RunSignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "appUser")
    private AppUser appUser;

    @Column(name = "runningGroup")
    private RunningGroup runningGroup;

    public RunSignUp() {
    }

    public RunSignUp(AppUser appUser, RunningGroup runningGroup) {
        this.appUser = appUser;
        this.runningGroup = runningGroup;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RunSignUp id(Long id) {
        setId(id);
        return this;
    }

    public AppUser getAppUser() {
        return this.appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public RunSignUp appUser(AppUser appUser) {
        setAppUser(appUser);
        return this;
    }

    public RunningGroup getRunningGroup() {
        return this.runningGroup;
    }

    public void setRunningGroup(RunningGroup runningGroup) {
        this.runningGroup = runningGroup;
    }

    public RunSignUp runningGroup(RunningGroup runningGroup) {
        setRunningGroup(runningGroup);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", appUser='" + getAppUser() + "'" +
                ", runningGroup='" + getRunningGroup() + "'" +
                "}";
    }
}
