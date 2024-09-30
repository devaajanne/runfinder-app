package app.runfinder.domain;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "runningGroups")
public class RunningGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "runningGroupName")
    private String runningGroupName;

    @Column(name = "runStart")
    private LocalDateTime runStart;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "startAddress")
    private String startAddress;

    @Column(name = "zipcode")
    private Zipcode zipcode;

    public RunningGroup() {
    }

    public RunningGroup(String runningGroupName, LocalDateTime runStart, Duration duration, String startAddress,
            Zipcode zipcode) {
        this.runningGroupName = runningGroupName;
        this.runStart = runStart;
        this.duration = duration;
        this.startAddress = startAddress;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RunningGroup id(Long id) {
        setId(id);
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

    public LocalDateTime getRunStart() {
        return this.runStart;
    }

    public void setRunStart(LocalDateTime runStart) {
        this.runStart = runStart;
    }

    public RunningGroup runStart(LocalDateTime runStart) {
        setRunStart(runStart);
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
                " id='" + getId() + "'" +
                ", runningGroupName='" + getRunningGroupName() + "'" +
                ", runStart='" + getRunStart() + "'" +
                ", duration='" + getDuration() + "'" +
                ", startAddress='" + getStartAddress() + "'" +
                ", zipcode='" + getZipcode() + "'" +
                "}";
    }
}
