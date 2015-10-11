package javapackage.controllers;

import javapackage.dao.RestaurantReviewDao;
import javapackage.helpers.DBHelper;
import javapackage.models.RestaurantReview;
import org.json.JSONException;
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
        model.addAttribute("reviews",dbHelper.selectOrderedReviews(DBHelper.RATING, DBHelper.DESC));
        return "mainPage";
    }

    @RequestMapping(value="/admin")
    public String displayRestaurantsReview(Model model) {
        model.addAttribute("reviews",dbHelper.selectOrderedReviews(DBHelper.NAME));
        return "adminPage";
    }

}
