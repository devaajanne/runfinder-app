package app.runfinder.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "zipcodes")
public class Zipcode {

    @Id
    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "city")
    private String city;

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
                " zipcode='" + getZipcode() + "'" +
                ", city='" + getCity() + "'" +
                "}";
    }
}
