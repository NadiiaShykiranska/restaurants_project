package javapackage.dao;

import javapackage.models.RestaurantReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Nadiia on 10.10.2015.
 */
@Transactional
public interface RestaurantReviewDao extends CrudRepository<RestaurantReview,Long>{
    public RestaurantReview findByName(String name);
}
