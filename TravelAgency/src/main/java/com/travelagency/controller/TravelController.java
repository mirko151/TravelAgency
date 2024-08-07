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

import java.util.List;

@Controller
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping("/all")
    public String listAllTravels(Model model) {
        List<Travel> travels = travelService.getAllTravels();
        model.addAttribute("travels", travels);
        return "travels";
    }

    @GetMapping("/add")
    public String showAddTravelForm(Model model) {
        model.addAttribute("travel", new Travel());
        model.addAttribute("transportModes", TransportMode.values());
        model.addAttribute("accommodations", Accommodation.values());
        return "add-travel";
    }

    @PostMapping("/add")
    public String addTravel(@ModelAttribute("travel") Travel travel, BindingResult result, @RequestParam("image") MultipartFile image) {
        if (result.hasErrors()) {
            return "add-travel";
        }
        // Save the image to disk and set the path in the travel object
        travelService.saveTravel(travel);
        return "redirect:/travel/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditTravelForm(@PathVariable("id") int id, Model model) {
        Travel travel = travelService.getTravelById(id);
        model.addAttribute("travel", travel);
        model.addAttribute("transportModes", TransportMode.values());
        model.addAttribute("accommodations", Accommodation.values());
        return "edit-travel";
    }

    @PostMapping("/edit")
    public String editTravel(@ModelAttribute("travel") Travel travel, BindingResult result, @RequestParam("image") MultipartFile image) {
        if (result.hasErrors()) {
            return "edit-travel";
        }
        // Save the updated image to disk and set the path in the travel object
        travelService.saveTravel(travel);
        return "redirect:/travel/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteTravel(@PathVariable("id") int id) {
        travelService.deleteTravel(id);
        return "redirect:/travel/all";
    }
}
