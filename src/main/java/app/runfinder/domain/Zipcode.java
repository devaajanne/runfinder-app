package app.runfinder.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "zipcodes")
public class Zipcode {

    @Id
    @Column(name = "zipcodeId")
    private String zipcodeId;

    @Column(name = "city")
    private String city;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipcode")
    private List<RunningGroup> runningGroups;

    public Zipcode() {
    }

    public Zipcode(String zipcodeId, String city) {
        this.zipcodeId = zipcodeId;
        this.city = city;
    }

    public String getZipcodeId() {
        return this.zipcodeId;
    }

    public void setZipcodeId(String zipcodeId) {
        this.zipcodeId = zipcodeId;
    }

    public Zipcode zipcodeId(String zipcodeId) {
        setZipcodeId(zipcodeId);
        return this;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Zipcode city(String city) {
        setCity(city);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " zipcodeId='" + getZipcodeId() + "'" +
                ", city='" + getCity() + "'" +
                "}";
    }
}
