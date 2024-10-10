package app.runfinder.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "runningGroups")
public class RunningGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "runningGroupId")
    private Long runningGroupId;

    @Column(name = "runningGroupName")
    private String runningGroupName;

    @Column(name = "runStartDate")
    private LocalDate runStartDate;

    @Column(name = "runStartTime")
    private LocalTime runStartTime;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "startAddress")
    private String startAddress;

    @ManyToOne
    @JoinColumn(name = "zipcode")
    private Zipcode zipcode;

    public RunningGroup() {
    }

    public RunningGroup(String runningGroupName, LocalDate runStartDate, LocalTime runStartTime, Duration duration,
            String startAddress, Zipcode zipcode) {
        this.runningGroupName = runningGroupName;
        this.runStartDate = runStartDate;
        this.runStartTime = runStartTime;
        this.duration = duration;
        this.startAddress = startAddress;
        this.zipcode = zipcode;
    }

    public Long getRunningGroupId() {
        return this.runningGroupId;
    }

    public void setRunningGroupId(Long runningGroupId) {
        this.runningGroupId = runningGroupId;
    }

    public RunningGroup runningGroupId(Long runningGroupId) {
        setRunningGroupId(runningGroupId);
        return this;
    }

    public String getRunningGroupName() {
        return this.runningGroupName;
    }

    public void setRunningGroupName(String runningGroupName) {
        this.runningGroupName = runningGroupName;
    }

    public RunningGroup runningGroupName(String runningGroupName) {
        setRunningGroupName(runningGroupName);
        return this;
    }

    public LocalDate getRunStartDate() {
        return this.runStartDate;
    }

    public void setRunStartDate(LocalDate runStartDate) {
        this.runStartDate = runStartDate;
    }

    public RunningGroup runStartDate(LocalDate runStartDate) {
        setRunStartDate(runStartDate);
        return this;
    }

    public LocalTime getRunStartTime() {
        return this.runStartTime;
    }

    public void setRunStartTime(LocalTime runStartTime) {
        this.runStartTime = runStartTime;
    }

    public RunningGroup runStartTime(LocalTime runStartTime) {
        setRunStartTime(runStartTime);
        return this;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public RunningGroup duration(Duration duration) {
        setDuration(duration);
        return this;
    }

    public String getStartAddress() {
        return this.startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public RunningGroup startAddress(String startAddress) {
        setStartAddress(startAddress);
        return this;
    }

    public Zipcode getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
    }

    public RunningGroup zipcode(Zipcode zipcode) {
        setZipcode(zipcode);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " runningGroupId='" + getRunningGroupId() + "'" +
                ", runningGroupName='" + getRunningGroupName() + "'" +
                ", runStartDate='" + getRunStartDate() + "'" +
                ", runStartTime='" + getRunStartTime() + "'" +
                ", duration='" + getDuration() + "'" +
                ", startAddress='" + getStartAddress() + "'" +
                ", zipcode='" + getZipcode() + "'" +
                "}";
    }

}