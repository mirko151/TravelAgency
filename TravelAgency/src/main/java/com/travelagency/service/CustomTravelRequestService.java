package com.travelagency.service;

import com.travelagency.model.CustomTravelRequest;
import com.travelagency.model.Travel;
import com.travelagency.repository.CustomTravelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.List;

@Service
public class CustomTravelRequestService {

    @Autowired
    private CustomTravelRequestRepository customTravelRequestRepository;

    @Autowired
    private TravelService travelService;

    public CustomTravelRequest saveCustomTravelRequest(CustomTravelRequest request) {
        return customTravelRequestRepository.save(request);
    }

    public List<CustomTravelRequest> getAllRequests() {
        return customTravelRequestRepository.findAll();
    }

    public CustomTravelRequest getRequestById(Long id) {
        return customTravelRequestRepository.findById(id).orElse(null);
    }

    public Travel createTravelFromRequest(CustomTravelRequest request) {
        Travel travel = new Travel();
        travel.setDestinationName(request.getDestination());
        travel.setTransportMode(request.getTransportMode());
        travel.setNumberOfNights((int) ((request.getEndDate().getTime() - request.getStartDate().getTime()) / (1000 * 60 * 60 * 24)));
        travel.setPrice(calculatePrice(request));
        travel.setTotalSeats(request.getNumberOfPeople());
        travel.setAvailableSeats(request.getNumberOfPeople());
        // Dodavanje ostalih potrebnih parametara

        try {
            return travelService.saveTravel(travel, null, null); // null za MultipartFile i managerEmail jer nisu potrebni u ovom kontekstu
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Ili možete baciti runtime izuzetak ili upravljati greškom na drugi način
        }
    }

    public void approveRequest(Long requestId, String managerEmail) {
        CustomTravelRequest request = getRequestById(requestId);
        if (request != null) {
            Travel travel = createTravelFromRequest(request);
            try {
                travelService.saveTravel(travel, null, managerEmail);
            } catch (IOException e) {
                e.printStackTrace();
                // Upravlja se izuzetkom
            }
            request.setStatus("APPROVED");
            customTravelRequestRepository.save(request);
        }
    }

    public void rejectRequest(Long requestId, String managerComment) {
        CustomTravelRequest request = getRequestById(requestId);
        if (request != null) {
            request.setStatus("REJECTED");
            request.setManagerComment(managerComment);
            customTravelRequestRepository.save(request);
        }
    }

    private BigDecimal calculatePrice(CustomTravelRequest request) {
        // Osnovna logika za određivanje cene na osnovu parametara zahteva
        return BigDecimal.valueOf(request.getNumberOfPeople() * 100); // Primer cene
    }

    public List<CustomTravelRequest> getRequestsByUser(Long userId) {
        return customTravelRequestRepository.findByUserId(userId);
    }

    public void deleteRequest(Long requestId) {
        customTravelRequestRepository.deleteById(requestId);
    }
}
