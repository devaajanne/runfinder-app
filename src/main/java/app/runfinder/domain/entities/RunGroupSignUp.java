package app.runfinder.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "runSignUps")
public class RunGroupSignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runSignUpId")
    private Long runSignUpId;

    @ManyToOne
    @JoinColumn(name = "appUser")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "runningGroup")
    private RunGroup runningGroup;

    public RunGroupSignUp() {
    }

    public RunGroupSignUp(AppUser appUser, RunGroup runningGroup) {
        this.appUser = appUser;
        this.runningGroup = runningGroup;
    }

    public Long getRunSignUpId() {
        return this.runSignUpId;
    }

    public void setRunSignUpId(Long runSignUpId) {
        this.runSignUpId = runSignUpId;
    }

    public RunGroupSignUp runSignUpId(Long runSignUpId) {
        setRunSignUpId(runSignUpId);
        return this;
    }

    public AppUser getAppUser() {
        return this.appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public RunGroupSignUp appUser(AppUser appUser) {
        setAppUser(appUser);
        return this;
    }

    public RunGroup getRunningGroup() {
        return this.runningGroup;
    }

    public void setRunningGroup(RunGroup runningGroup) {
        this.runningGroup = runningGroup;
    }

    public RunGroupSignUp runningGroup(RunGroup runningGroup) {
        setRunningGroup(runningGroup);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " runSignUpId='" + getRunSignUpId() + "'" +
                ", appUser='" + getAppUser() + "'" +
                ", runningGroup='" + getRunningGroup() + "'" +
                "}";
    }
}
