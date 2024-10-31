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
@Table(name = "run_group_sign_ups")
public class RunGroupSignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "run_group_sign_up_id")
    private Long runGroupSignUpId;

    @ManyToOne
    @JoinColumn(name = "app_user")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "run_group")
    private RunGroup runGroup;

    public RunGroupSignUp() {
    }

    public RunGroupSignUp(AppUser appUser, RunGroup runGroup) {
        this.appUser = appUser;
        this.runGroup = runGroup;
    }

    public Long getRunGroupSignUpId() {
        return this.runGroupSignUpId;
    }

    public void setRunGroupSignUpId(Long runGroupSignUpId) {
        this.runGroupSignUpId = runGroupSignUpId;
    }

    public RunGroupSignUp runGroupSignUpId(Long runGroupSignUpId) {
        setRunGroupSignUpId(runGroupSignUpId);
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

    public RunGroup getRunGroup() {
        return this.runGroup;
    }

    public void setRunGroup(RunGroup runGroup) {
        this.runGroup = runGroup;
    }

    public RunGroupSignUp runGroup(RunGroup runGroup) {
        setRunGroup(runGroup);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " runGroupSignUpId='" + getRunGroupSignUpId() + "'" +
            ", appUser='" + getAppUser() + "'" +
            ", runGroup='" + getRunGroup() + "'" +
            "}";
    }

}
