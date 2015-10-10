package javapackage.controllers;

import javapackage.dao.RestaurantReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private RestaurantReviewDao restaurantReviewDao;

    @RequestMapping(value="/")
    public String greetingwwww() {
        return "mainPage";
    }

}
