package app.runfinder.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import app.runfinder.domain.dto.RunGroupDTO;

@Entity
@Table(name = "run_groups")
public class RunGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "run_group_id")
    private Long runGroupId;

    @Column(name = "run_group_name")
    @Size(min = 4, max = 50, message = "Run group name must be between 5 and 50 characters long")
    private String runGroupName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "run_start_date")
    // @FutureOrPresent(message = "Run group start date must be today or in the
    // future")
    private LocalDate runStartDate;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "run_start_time")
    private LocalTime runStartTime;

    @Column(name = "start_address")
    @NotEmpty(message = "The run group must have a start address")
    private String startAddress;

    @ManyToOne
    @JoinColumn(name = "zipcode")
    private Zipcode zipcode;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "appUserId")
    private AppUser addedByAppUser;

    @OneToMany(mappedBy = "runGroup")
    Set<RunGroupSignUp> runGroupSignUps;

    public RunGroup() {
    }

    public RunGroup(String runGroupName, LocalDate runStartDate, LocalTime runStartTime, String startAddress,
            Zipcode zipcode, LocalDateTime deletedAt, AppUser addedByAppUser) {
        this.runGroupName = runGroupName;
        this.runStartDate = runStartDate;
        this.runStartTime = runStartTime;
        this.startAddress = startAddress;
        this.zipcode = zipcode;
        this.deletedAt = deletedAt;
        this.addedByAppUser = addedByAppUser;
    }

    public Long getRunGroupId() {
        return this.runGroupId;
    }

    public void setRunGroupId(Long runGroupId) {
        this.runGroupId = runGroupId;
    }

    public RunGroup runGroupId(Long runGroupId) {
        setRunGroupId(runGroupId);
        return this;
    }

    public String getRunGroupName() {
        return this.runGroupName;
    }

    public void setRunGroupName(String runGroupName) {
        this.runGroupName = runGroupName;
    }

    public RunGroup runGroupName(String runGroupName) {
        setRunGroupName(runGroupName);
        return this;
    }

    public LocalDate getRunStartDate() {
        return this.runStartDate;
    }

    public void setRunStartDate(LocalDate runStartDate) {
        this.runStartDate = runStartDate;
    }

    public RunGroup runStartDate(LocalDate runStartDate) {
        setRunStartDate(runStartDate);
        return this;
    }

    public LocalTime getRunStartTime() {
        return this.runStartTime;
    }

    public void setRunStartTime(LocalTime runStartTime) {
        this.runStartTime = runStartTime;
    }

    public RunGroup runStartTime(LocalTime runStartTime) {
        setRunStartTime(runStartTime);
        return this;
    }

    public String getStartAddress() {
        return this.startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public RunGroup startAddress(String startAddress) {
        setStartAddress(startAddress);
        return this;
    }

    public Zipcode getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
    }

    public RunGroup zipcode(Zipcode zipcode) {
        setZipcode(zipcode);
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public RunGroup deletedAt(LocalDateTime deletedAt) {
        setDeletedAt(deletedAt);
        return this;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void restore() {
        this.deletedAt = null;
    }

    public AppUser getAddedByAppUser() {
        return this.addedByAppUser;
    }

    public void setAddedByAppUser(AppUser addedByAppUser) {
        this.addedByAppUser = addedByAppUser;
    }

    public RunGroup addedByAppUser(AppUser addedByAppUser) {
        setAddedByAppUser(addedByAppUser);
        return this;
    }

    public RunGroupDTO toDTO() {
        return new RunGroupDTO(
                this.runGroupId,
                this.runGroupName,
                this.runStartDate,
                this.runStartTime,
                this.startAddress,
                this.zipcode.getZipcode(),
                this.zipcode.getCity(),
                this.addedByAppUser.getAppUserId());
    }

    @Override
    public String toString() {
        return "{" +
                " runGroupId='" + getRunGroupId() + "'" +
                ", runGroupName='" + getRunGroupName() + "'" +
                ", runStartDate='" + getRunStartDate() + "'" +
                ", runStartTime='" + getRunStartTime() + "'" +
                ", startAddress='" + getStartAddress() + "'" +
                ", zipcode='" + getZipcode() + "'" +
                ", deletedAt='" + getDeletedAt() + "'" +
                ", addedByAppUser='" + getAddedByAppUser() + "'" +
                "}";
    }
}