package app.runfinder.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "runningGroups")
public class RunGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runningGroupId")
    private Long runGroupId;

    @Column(name = "runningGroupName")
    @NotEmpty(message = "The run group must have a name")
    private String runGroupName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "runStartDate")
    @FutureOrPresent(message = "Run group start date must be today or in the future")
    private LocalDate runStartDate;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "runStartTime")
    private LocalTime runStartTime;

    @Column(name = "startAddress")
    @NotEmpty(message = "The run group must have a start address")
    private String startAddress;

    @ManyToOne
    @JoinColumn(name = "zipcode")
    private Zipcode zipcode;

    public RunGroup() {
    }

    public RunGroup(String runGroupName, LocalDate runStartDate, LocalTime runStartTime, String startAddress,
            Zipcode zipcode) {
        this.runGroupName = runGroupName;
        this.runStartDate = runStartDate;
        this.runStartTime = runStartTime;
        this.startAddress = startAddress;
        this.zipcode = zipcode;
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

    @Override
    public String toString() {
        return "{" +
                " runGroupId='" + getRunGroupId() + "'" +
                ", runGroupName='" + getRunGroupName() + "'" +
                ", runStartDate='" + getRunStartDate() + "'" +
                ", runStartTime='" + getRunStartTime() + "'" +
                ", startAddress='" + getStartAddress() + "'" +
                ", zipcode='" + getZipcode() + "'" +
                "}";
    }

}