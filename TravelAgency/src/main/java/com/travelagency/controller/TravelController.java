package com.travelagency.controller;

import com.travelagency.model.Reservation;
import com.travelagency.model.Travel;
import com.travelagency.service.ReservationService;
import com.travelagency.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    private TravelService travelService;

    @Autowired
    private ReservationService reservationService;

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
    public String addTravel(@ModelAttribute("travel") Travel travel, BindingResult result, @RequestParam("image") MultipartFile image) throws IOException {
        if (result.hasErrors()) {
            return "add-travel";
        }
        if (!image.isEmpty()) {
            String imagePath = travelService.saveImage(image);
            travel.setImagePath(imagePath);
        }
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
    public String editTravel(@ModelAttribute("travel") Travel travel, BindingResult result, @RequestParam("image") MultipartFile image) throws IOException {
        if (result.hasErrors()) {
            return "edit-travel";
        }
        if (!image.isEmpty()) {
            String imagePath = travelService.saveImage(image);
            travel.setImagePath(imagePath);
        }
        travelService.saveTravel(travel);
        return "redirect:/travel/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteTravel(@PathVariable("id") int id) {
        travelService.deleteTravel(id);
        return "redirect:/travel/all";
    }

    @GetMapping("/reserve/{id}")
    public String showReserveTravelForm(@PathVariable("id") int id, Model model) {
        Travel travel = travelService.getTravelById(id);
        Reservation reservation = new Reservation();
        reservation.setTravel(travel);
        model.addAttribute("reservation", reservation);
        return "reserve-travel";
    }

    @PostMapping("/reserve")
    public String reserveTravel(@ModelAttribute("reservation") Reservation reservation, BindingResult result) {
        if (result.hasErrors()) {
            return "reserve-travel";
        }
        reservationService.saveReservation(reservation);
        return "redirect:/travel/all";
    }

    @GetMapping("/cancel-reservation/{id}")
    public String cancelReservation(@PathVariable("id") int id) {
        reservationService.cancelReservation(id);
        return "redirect:/user/profile";
    }
}
