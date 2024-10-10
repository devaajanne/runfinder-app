package app.runfinder.domain.entities;

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
    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "city")
    private String city;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipcode")
    private List<RunningGroup> runningGroups;

    public Zipcode() {
    }

    public Zipcode(String zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Zipcode zipcode(String zipcode) {
        setZipcode(zipcode);
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
                " zipcodeId='" + getZipcode() + "'" +
                ", city='" + getCity() + "'" +
                "}";
    }
}
