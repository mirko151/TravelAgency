package com.travelagency.controller;

import com.travelagency.model.CustomTravelRequest;
import com.travelagency.model.Travel;
import com.travelagency.service.CustomTravelRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/custom-travel")
public class CustomTravelRequestController {

    @Autowired
    private CustomTravelRequestService customTravelRequestService;

    @GetMapping("/requests")
    public String listRequests(Model model) {
        model.addAttribute("requests", customTravelRequestService.getAllRequests());
        return "custom-travel-requests";
    }

    @PostMapping("/create-travel/{id}")
    public String createTravelFromRequest(@PathVariable("id") Long id) {
        CustomTravelRequest request = customTravelRequestService.getRequestById(id);
        if (request != null) {
            Travel travel = customTravelRequestService.createTravelFromRequest(request);
            return "redirect:/travel/details/" + travel.getId();
        }
        return "redirect:/custom-travel/requests";
    }

    @PostMapping("/reject-request/{id}")
    public String rejectRequest(@PathVariable("id") Long id, @RequestParam("comment") String comment) {
        CustomTravelRequest request = customTravelRequestService.getRequestById(id);
        if (request != null) {
            request.setManagerComment(comment);
            request.setStatus("REJECTED");
            customTravelRequestService.saveCustomTravelRequest(request);
        }
        return "redirect:/custom-travel/requests";
    }
}
