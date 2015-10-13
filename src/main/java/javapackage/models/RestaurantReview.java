package javapackage.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "restaurants")
public class RestaurantReview {

    public RestaurantReview(){
    }

    public RestaurantReview(Integer id, String name, String location, String review, byte cuisine, byte interior, byte service, Double rating){
        this.id=id;
        this.name = name;
        this.location = location;
        this.review = review;
        this.cuisine = cuisine;
        this.interior = interior;
        this.service = service;
        this.rating = rating;
    }

    public RestaurantReview(String name, String location, String review, byte cuisine, byte interior, byte service){
        this.name = name;
        this.location = location;
        this.review = review;
        this.cuisine = cuisine;
        this.interior = interior;
        this.service = service;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = true)
    private String location;

    @NotNull
    @Column(name = "review", nullable = false)
    private String review;

    @NotNull
    @Column(name = "cuisine", nullable = false)
    private byte cuisine;

    @NotNull
    @Column(name = "interior", nullable = false)
    private byte interior;

    @NotNull
    @Column(name = "service", nullable = false)
    private byte service;

    @NotNull
    @Column(name = "rating", nullable = false)
    private double rating;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(byte cuisine) {
        this.cuisine = cuisine;
    }

    public void setService(byte service) {
        this.service = service;
    }

    public void setInterior(byte interior) {
        this.interior = interior;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public String getLocation() {
        return location;
    }
}
