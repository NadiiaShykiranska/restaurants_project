package javapackage.models;

import javapackage.controllers.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DBModel {
    public static final String NAME = "name";
    public static final String RATING = "rating";
    public static final String DESC = "desc";

    public List<Restaurant> selectOrderedReviews(String column){
        String desc = (column.equals(NAME))?"":DESC;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List result = session.createSQLQuery(
                "select * from restaurants order by "+column+" "+desc).list();
        List<Restaurant> listReviews = parseResults(result);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return listReviews;
    }

    public Restaurant selectRestaurant(String restaurantID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List result = session.createSQLQuery(
                "select * from restaurants where id = :restaurantID")
                .setString("restaurantID", restaurantID).list();
        Restaurant restaurantReview = (Restaurant)parseResults(result).get(0);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return restaurantReview;
    }

    public List<Restaurant> getMatches(String pattern){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List result = session.createSQLQuery(
                "select * from restaurants where name regexp :pattern")
                .setString("pattern", pattern).list();
        List<Restaurant> listReviews = parseResults(result);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return listReviews;
    }

    public List<Restaurant> addNewRestaurantAndGetUpdatedList(Restaurant restaurant){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(
                "insert into restaurants (name, longitude, latitude, review, cuisine, interior, service, rating, date) values (:name, :longitude, :latitude, :review, :cuisine, :interior, :service, :rating, :date)")
                .setString("name", restaurant.getName())
                .setString("longitude", String.valueOf(restaurant.getLongitude()))
                .setString("latitude", String.valueOf(restaurant.getLatitude()))
                .setString("review", restaurant.getReview())
                .setString("cuisine", String.valueOf(restaurant.getCuisine()))
                .setString("interior", String.valueOf(restaurant.getService()))
                .setString("service", String.valueOf(restaurant.getInterior()))
                .setString("rating", String.valueOf(restaurant.getRating()))
                .setString("date", String.valueOf(restaurant.getDate()))
                .executeUpdate();
        List result = session.createSQLQuery(
                "select * from restaurants order by name").list();
        List<Restaurant> listRestaurants = parseResults(result);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return listRestaurants;
    }

    public List<Restaurant> updateReviewAndGetUpdatedList(String id, Restaurant restaurant){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(
                "update restaurants SET name = :name, date = :date, longitude = :longitude, latitude = :latitude, cuisine = :cuisine, interior = :interior, service = :service, review = :review, rating = :rating where id = :id")
                .setString("name", restaurant.getName())
                .setString("date", restaurant.getDate().toString())
                .setString("longitude", String.valueOf(restaurant.getLongitude()))
                .setString("latitude", String.valueOf(restaurant.getLatitude()))
                .setString("cuisine", String.valueOf(restaurant.getCuisine()))
                .setString("interior", String.valueOf(restaurant.getInterior()))
                .setString("service", String.valueOf(restaurant.getService()))
                .setString("review", restaurant.getReview())
                .setString("rating", String.valueOf(restaurant.getRating()))
                .setString("id", id)
                .executeUpdate();
        List result = session.createSQLQuery(
                "select * from restaurants order by name").list();
        List<Restaurant> listReviews = parseResults(result);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return listReviews;
    }

    private List<Restaurant> parseResults(List<Object> results){
        List<Restaurant> listRestaurants = new LinkedList<>();
        for (Object object: results) {
            Object[] o = (Object[]) object;
            listRestaurants.add(new Restaurant(
                    Integer.parseInt(o[0].toString()),
                    String.valueOf(o[1]),
                    Double.parseDouble(o[2].toString()),
                    Double.parseDouble(o[3].toString()),
                    Long.parseLong(o[4].toString()),
                    String.valueOf(o[5]),
                    Byte.parseByte(o[6].toString()),
                    Byte.parseByte(o[7].toString()),
                    Byte.parseByte(o[8].toString()),
                    Double.parseDouble(o[9].toString())));
        }
        return listRestaurants;
    }

}
