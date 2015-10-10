package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Nadiia on 10.10.2015.
 */
@Entity
@Table(name = "restaurants")
public class RestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "cuisine", nullable = false)
    private short cuisine;

    @NotNull
    @Column(name = "service", nullable = false)
    private short service;

    @NotNull
    @Column(name = "interior", nullable = false)
    private short interior;

    @NotNull
    @Column(name = "review", nullable = false)
    private String review;

    @Column(name = "location", nullable = true)
    private String location;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(short cuisine) {
        this.cuisine = cuisine;
    }

    public void setService(short service) {
        this.service = service;
    }

    public void setInterior(short interior) {
        this.interior = interior;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public short getCuisine() {
        return cuisine;
    }

    public short getService() {
        return service;
    }

    public short getInterior() {
        return interior;
    }

    public String getReview() {
        return review;
    }

    public String getLocation() {
        return location;
    }
}
