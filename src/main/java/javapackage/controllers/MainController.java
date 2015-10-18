package javapackage.controllers;

import javapackage.models.DBModel;
import javapackage.models.Restaurant;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    DBModel dbModel;

    @RequestMapping(value="/")
    public String displayMainPage(Model model) {
        model.addAttribute("restaurants",dbModel.selectOrderedReviews(DBModel.RATING));
        return "mainPage";
    }

    @RequestMapping(value="/sorted_by_{selectOption}")
    public String displayManePageSorted(@PathVariable String selectOption, Model model) {
        model.addAttribute("restaurants",dbModel.selectOrderedReviews(selectOption));
        return "mainPage";
    }

    @RequestMapping(value="/search={pattern}")
    public String displayManePageMatches(@PathVariable String pattern, Model model) {
        model.addAttribute("restaurants",dbModel.getMatches(pattern));
        return "mainPage";
    }

    @RequestMapping(value = "/getMatches", method = RequestMethod.GET)
    @ResponseBody List<Restaurant> getMatches(@RequestParam String pattern) {
        return dbModel.getMatches(pattern);
    }

    @RequestMapping(value="/restaurant_{restaurantID}")
    public String displayRestaurantPage(@PathVariable String restaurantID, Model model) {
        model.addAttribute("reviewDetails",dbModel.selectRestaurant(restaurantID));
        return "restaurantPage";
    }

    @RequestMapping(value="/admin")
    public String displayAdminPage(Model model) {
        model.addAttribute("reviews",dbModel.selectOrderedReviews(DBModel.NAME));
        return "adminPage";
    }

    @RequestMapping(value="/add_new_review")
    public String displayRestaurantEditPage(Model model) {
        return "restaurantEditPage";
    }

    @RequestMapping(value="/edit_{restaurantID}")
    public String displayAdminEditPage(@PathVariable String restaurantID, Model model) {
        model.addAttribute("restaurant",dbModel.selectRestaurant(restaurantID));
        return "restaurantEditPage";
    }

    @RequestMapping(value = "/addNewReview", method = RequestMethod.GET)
    @ResponseBody List<Restaurant> addNewReview(@RequestParam String restaurant) throws JSONException{
        JSONArray jsonArray = new JSONArray(restaurant);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        Restaurant restaurantReview = new Restaurant(
                jsonObject.get("name").toString(),
                Double.valueOf(jsonObject.get("longitude").toString()),
                Double.valueOf(jsonObject.get("latitude").toString()),
                jsonObject.get("review").toString(),
                Byte.valueOf(jsonObject.get("cuisine").toString()),
                Byte.valueOf(jsonObject.get("interior").toString()),
                Byte.valueOf(jsonObject.get("service").toString()));
        return dbModel.addNewRestaurantAndGetUpdatedList(restaurantReview);
    }

    @RequestMapping(value = "/editReview", method = RequestMethod.GET)
    @ResponseBody List<Restaurant> editReview(@RequestParam String id, @RequestParam String restaurant) throws JSONException {
        JSONArray jsonArray = new JSONArray(restaurant);
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        Restaurant restaurantReview = new Restaurant(
                jsonObject.get("name").toString(),
                Double.valueOf(jsonObject.get("longitude").toString()),
                Double.valueOf(jsonObject.get("latitude").toString()),
                jsonObject.get("review").toString(),
                Byte.valueOf(jsonObject.get("cuisine").toString()),
                Byte.valueOf(jsonObject.get("interior").toString()),
                Byte.valueOf(jsonObject.get("service").toString()));
        return  dbModel.updateReviewAndGetUpdatedList(id, restaurantReview);
    }
}