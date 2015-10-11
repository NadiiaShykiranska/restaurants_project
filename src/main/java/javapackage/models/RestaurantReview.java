package javapackage.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "restaurants")
public class RestaurantReview {

    public RestaurantReview(Integer id, String name, String location, String review, Short cuisine, Short interior, Short service, Double rating){
        this.id=id;
        this.name = name;
        this.location = location;
        this.review = review;
        this.cuisine = cuisine;
        this.interior = interior;
        this.service = service;
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

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
    @Column(name = "rating", nullable = false)
    private double rating;

    @NotNull
    @Column(name = "interior", nullable = false)
    private short interior;

    @NotNull
    @Column(name = "review", nullable = false)
    private String review;

    @Column(name = "location", nullable = true)
    private String location;

    public void setId(int id) {
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

    public void setRating(double rating) {
        this.rating = rating;}

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

    public double getRating() {
        return rating;}

    public String getReview() {
        return review;
    }

    public String getLocation() {
        return location;
    }
}
