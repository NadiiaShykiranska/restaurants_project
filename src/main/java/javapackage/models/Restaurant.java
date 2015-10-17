package javapackage.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    public Restaurant(){
    }

    public Restaurant(Integer id, String name, Double longitude, Double latitude, Long date, String review, byte cuisine, byte interior, byte service, double rating){
        this.id=id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.review = review;
        this.cuisine = cuisine;
        this.interior = interior;
        this.service = service;
        this.date = date;
        this.rating = rating;
    }

    public Restaurant(String name, Double longitude, Double latitude, String review, byte cuisine, byte interior, byte service){
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.review = review;
        this.cuisine = cuisine;
        this.interior = interior;
        this.service = service;
        this.rating = cuisine * 0.4 + interior * 0.3 + service * 0.3;
        Calendar calendar = new GregorianCalendar();
        this.date = calendar.getTimeInMillis();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "date", nullable = false)
    private Long date;

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

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getId() {
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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Long getDate() {
        return date;
    }
}
