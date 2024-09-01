package com.travelagency.controller;

import com.travelagency.model.Travel;
import com.travelagency.model.TransportMode;
import com.travelagency.model.Accommodation;
import com.travelagency.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/travel")
public class TravelController {

    private static final Logger logger = LoggerFactory.getLogger(TravelController.class);

    @Autowired
    private TravelService travelService;

    @GetMapping("/all")
    public String listAllTravels(Model model) {
        logger.info("Fetching all travels");
        List<Travel> travels = travelService.getAllTravels();
        model.addAttribute("travels", travels);
        logger.info("Returning view: travels.jsp");
        return "travels";
    }

    @GetMapping("/add")
    public String showAddTravelForm(Model model) {
        logger.info("Showing form to add new travel");
        model.addAttribute("travel", new Travel());
        model.addAttribute("transportModes", TransportMode.values());
        model.addAttribute("accommodations", Accommodation.values());
        logger.info("Returning view: add-travel.jsp");
        return "add-travel";
    }

    @PostMapping("/add")
    public String addTravel(@ModelAttribute("travel") Travel travel, BindingResult result, @RequestParam("image") MultipartFile image) {
        if (result.hasErrors()) {
            logger.warn("Validation errors while adding travel");
            return "add-travel";
        }
        try {
            logger.info("Saving new travel: {}", travel.getDestinationName());
            travelService.saveTravel(travel, image);
        } catch (IOException e) {
            logger.error("Error while saving travel", e);
            return "add-travel";
        }
        logger.info("Redirecting to /travel/all");
        return "redirect:/travel/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditTravelForm(@PathVariable("id") int id, Model model) {
        logger.info("Fetching travel to edit with id: {}", id);
        Travel travel = travelService.getTravelById(id);
        if (travel == null) {
            logger.error("Travel with id {} not found", id);
            return "redirect:/travel/all";
        }
        model.addAttribute("travel", travel);
        model.addAttribute("transportModes", TransportMode.values());
        model.addAttribute("accommodations", Accommodation.values());
        logger.info("Returning view: edit-travel.jsp");
        return "edit-travel";
    }

    @PostMapping("/edit")
    public String editTravel(@ModelAttribute("travel") Travel travel, BindingResult result, @RequestParam("image") MultipartFile image) {
        if (result.hasErrors()) {
            logger.warn("Validation errors while editing travel");
            return "edit-travel";
        }
        try {
            logger.info("Updating travel: {}", travel.getDestinationName());
            travelService.saveTravel(travel, image);
        } catch (IOException e) {
            logger.error("Error while updating travel", e);
            return "edit-travel";
        }
        logger.info("Redirecting to /travel/all");
        return "redirect:/travel/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteTravel(@PathVariable("id") int id) {
        logger.info("Deleting travel with id: {}", id);
        travelService.deleteTravel(id);
        logger.info("Redirecting to /travel/all");
        return "redirect:/travel/all";
    }

    @GetMapping("/search")
    public String searchTravels(@RequestParam(required = false) TransportMode transportMode,
                                @RequestParam(required = false) BigDecimal minPrice,
                                @RequestParam(required = false) BigDecimal maxPrice,
                                @RequestParam(required = false) Integer nights,
                                Model model) {
        logger.info("Searching travels with filters: transportMode={}, minPrice={}, maxPrice={}, nights={}",
                transportMode, minPrice, maxPrice, nights);
        List<Travel> travels = travelService.searchTravels(transportMode, minPrice, maxPrice, nights);
        model.addAttribute("travels", travels);
        logger.info("Returning view: travels.jsp");
        return "travels";
    }
}
