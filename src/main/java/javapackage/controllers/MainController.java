package javapackage.controllers;

import javapackage.dao.RestaurantReviewDao;
import javapackage.helpers.DBHelper;
import javapackage.models.RestaurantReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    DBHelper dbHelper;

    @Autowired
    private RestaurantReviewDao restaurantReviewDao;

    @RequestMapping(value="/")
    public String displayRestaurantsOrderedByRating(Model model) {
        model.addAttribute("reviews",dbHelper.selectOrderedReviews(DBHelper.RATING, true));
        return "mainPage";
    }

    @RequestMapping(value="/admin")
    public String displayRestaurantsReview(Model model) {
        model.addAttribute("reviews",dbHelper.selectOrderedReviews(DBHelper.NAME, false));
        return "adminPage";
    }

    @RequestMapping(value = "/getReview", method = RequestMethod.GET)
    @ResponseBody RestaurantReview getRestaurantReview(@RequestParam String restaurantName) {
        return dbHelper.selectRestaurantReview(restaurantName);
    }

    @RequestMapping(value = "/getMatches", method = RequestMethod.GET)
    @ResponseBody List<RestaurantReview> getMatches(@RequestParam String pattern) {
        return dbHelper.getMatches(pattern);
    }

    @RequestMapping(value = "/getSorted", method = RequestMethod.GET)
    @ResponseBody List<RestaurantReview> getSorted(@RequestParam String sortingValue) {
        return dbHelper.selectOrderedReviews(sortingValue, true);
    }

}
