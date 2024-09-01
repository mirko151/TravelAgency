package com.travelagency.controller;

import com.travelagency.model.Travel;
import com.travelagency.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TravelService travelService;

    @GetMapping("/")
    public String home(Model model) {
        List<Travel> discountTravels = travelService.getActiveDiscountTravels();
        if (!discountTravels.isEmpty()) {
            model.addAttribute("travels", discountTravels);
        } else {
            String season = getSeason();
            model.addAttribute("travels", travelService.getSeasonalTravels(season));
        }
        return "home";
    }

    private String getSeason() {
        Date date = new Date();
        int month = date.getMonth();
        if (month >= 5 && month <= 8) {
            return "Letovanje";
        } else if (month == 11 || month <= 2) {
            return "Zimovanje";
        } else {
            return "Van sezonska ponuda";
        }
    }
}
