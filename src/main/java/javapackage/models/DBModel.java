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

    public Restaurant selectRestaurant(String restaurantName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List result = session.createSQLQuery(
                "select * from restaurants where name = :restaurantName")
                .setString("restaurantName", restaurantName).list();
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
                "insert into restaurants (name, location, review, cuisine, interior, service, rating) values(:name, :location, :review, :cuisine, :interior, :service, :rating)")
                .setString("cuisine", String.valueOf(restaurant.getCuisine()))
                .setString("interior", String.valueOf(restaurant.getService()))
                .setString("service", String.valueOf(restaurant.getInterior()))
                .setString("rating", String.valueOf(restaurant.getRating()))
                .setString("review", restaurant.getReview())
                .setString("location", restaurant.getLocation())
                .setString("name", restaurant.getName())
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

    public List<Restaurant> updateReviewAndGetUpdatedList(String oldName, Restaurant restaurant){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(
                "update restaurants SET name = :name, location = :location, cuisine = :cuisine, interior = :interior, service = :service, review = :review, rating = :rating where name = :oldName")
                .setString("oldName", oldName)
                .setString("cuisine", String.valueOf(restaurant.getCuisine()))
                .setString("interior", String.valueOf(restaurant.getService()))
                .setString("service", String.valueOf(restaurant.getInterior()))
                .setString("rating", String.valueOf(restaurant.getRating()))
                .setString("review", restaurant.getReview())
                .setString("location", restaurant.getLocation())
                .setString("name", restaurant.getName()).executeUpdate();
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
                    String.valueOf(o[2]),
                    String.valueOf(o[3]),
                    Byte.parseByte(o[4].toString()),
                    Byte.parseByte(o[5].toString()),
                    Byte.parseByte(o[6].toString())));
        }
        return listRestaurants;
    }

}
