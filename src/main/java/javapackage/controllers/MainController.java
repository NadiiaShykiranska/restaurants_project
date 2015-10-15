package javapackage.controllers;

import javapackage.models.DBModel;
import javapackage.models.RestaurantReview;
import org.json.*;
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
    DBModel dbModel;

    @RequestMapping(value="/")
    public String displayRestaurantsOrderedByRating(Model model) {
        model.addAttribute("reviews",dbModel.selectOrderedReviews(DBModel.RATING));
        return "mainPage";
    }

    @RequestMapping(value="/admin")
    public String displayRestaurantsReview(Model model) {
        model.addAttribute("reviews",dbModel.selectOrderedReviews(DBModel.NAME));
        return "adminPage";
    }

    @RequestMapping(value = "/getReview", method = RequestMethod.GET)
    @ResponseBody RestaurantReview getRestaurantReview(@RequestParam String restaurantName) {
        return dbModel.selectRestaurantReview(restaurantName);
    }

    @RequestMapping(value = "/getMatches", method = RequestMethod.GET)
    @ResponseBody List<RestaurantReview> getMatches(@RequestParam String pattern) {
        return dbModel.getMatches(pattern);
    }

    @RequestMapping(value = "/getSorted", method = RequestMethod.GET)
    @ResponseBody List<RestaurantReview> getSorted(@RequestParam String sortingValue) {
        return dbModel.selectOrderedReviews(sortingValue);
    }

    @RequestMapping(value = "/addNewReview", method = RequestMethod.GET)
    @ResponseBody List<RestaurantReview> addNewReview(@RequestParam String data) throws JSONException{
        JSONArray jsonArray = new JSONArray(data);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        RestaurantReview restaurantReview = new RestaurantReview(
                jsonObject.get("name").toString(),
                jsonObject.get("location").toString(),
                jsonObject.get("review").toString(),
                Byte.valueOf(jsonObject.get("cuisine").toString()),
                Byte.valueOf(jsonObject.get("interior").toString()),
                Byte.valueOf(jsonObject.get("service").toString()));
        return dbModel.addNewReviewAndGetUpdatedList(restaurantReview);
    }

    @RequestMapping(value = "/editReview", method = RequestMethod.GET)
    @ResponseBody List<RestaurantReview> editReview(@RequestParam String oldRestaurantName, @RequestParam String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        RestaurantReview restaurantReview = new RestaurantReview(
                jsonObject.get("name").toString(),
                jsonObject.get("location").toString(),
                jsonObject.get("review").toString(),
                Byte.valueOf(jsonObject.get("cuisine").toString()),
                Byte.valueOf(jsonObject.get("interior").toString()),
                Byte.valueOf(jsonObject.get("service").toString()));
        return  dbModel.updateReviewAndGetUpdatedList(oldRestaurantName, restaurantReview);
    }
}
