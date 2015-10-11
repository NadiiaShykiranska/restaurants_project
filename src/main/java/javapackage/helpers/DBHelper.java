package javapackage.helpers;

import javapackage.controllers.HibernateUtil;
import javapackage.models.RestaurantReview;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DBHelper {

    public static final String RATING = "rating";
    public static final String CUISINE = "cuisine";
    public static final String INTERIOR = "interior";
    public static final String SERVICE = "service";

    public List<RestaurantReview> selectOrderedReviews(String columnName){
        List<RestaurantReview> listReviews = new LinkedList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select * from restaurants order by "+columnName+" desc";

        for (Object result  : session.createSQLQuery(sql).list()) {
            Object[] o = (Object[]) result;
            listReviews.add(new RestaurantReview(
                        Integer.parseInt(o[0].toString()),
                        String.valueOf(o[1]),
                        String.valueOf(o[2]),
                        String.valueOf(o[3]),
                        Short.parseShort(o[4].toString()),
                        Short.parseShort(o[5].toString()),
                        Short.parseShort(o[6].toString()),
                        Double.parseDouble(o[7].toString())));
        }
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return listReviews;
    }

}
