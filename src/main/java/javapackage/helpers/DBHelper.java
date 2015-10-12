package javapackage.helpers;

import javapackage.controllers.HibernateUtil;
import javapackage.models.RestaurantReview;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class DBHelper {
    public static final String NAME = "name";
    public static final String RATING = "rating";
    public static final String CUISINE = "cuisine";
    public static final String INTERIOR = "interior";
    public static final String SERVICE = "service";
    public static final String DESC = "desc";

    public List<RestaurantReview> selectOrderedReviews(String columnName, Boolean isDesc){
        String desc = isDesc?DESC:"";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List result = session.createSQLQuery(
                "select * from restaurants order by :columnName :desc")
                .setString("columnName", columnName)
                .setString("desc", desc).list();
        List<RestaurantReview> listReviews = parseResults(result);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return listReviews;
    }

    public RestaurantReview selectRestaurantReview(String restaurantName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List result = session.createSQLQuery(
                "select * from restaurants where name = :restaurantName")
                .setString("restaurantName", restaurantName).list();
        RestaurantReview restaurantReview = (RestaurantReview)parseResults(result).get(0);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return restaurantReview;
    }

    public void updateReview(RestaurantReview restaurantReview){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(
                "update restaurants SET cuisine = :cuisine, interior = :interior, service = :service, review = :review,  rating= :cuisine*0.4+:interior*0.3+:service*0.3 where name = :name")
                .setString("cuisine", String.valueOf(restaurantReview.getCuisine()))
                .setString("interior", String.valueOf(restaurantReview.getService()))
                .setString("service", String.valueOf(restaurantReview.getInterior()))
                .setString("review", restaurantReview.getReview())
                .setString("name", restaurantReview.getName());
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    private List<RestaurantReview> parseResults(List<Object> results){
        List<RestaurantReview> listReviews = new LinkedList<>();
        for (Object object: results) {
            Object[] o = (Object[]) object;
            listReviews.add(new RestaurantReview(
                    Integer.parseInt(o[0].toString()),
                    String.valueOf(o[1]),
                    String.valueOf(o[2]),
                    String.valueOf(o[3]),
                    Byte.parseByte(o[4].toString()),
                    Byte.parseByte(o[5].toString()),
                    Byte.parseByte(o[6].toString()),
                    Double.parseDouble(o[7].toString())));
        }
        return listReviews;
    }
}
