package com.travelagency.service;

import com.travelagency.model.TransportMode;
import com.travelagency.model.Travel;
import com.travelagency.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TravelService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AuditService auditService;

    public Travel saveTravel(Travel travel, String managerEmail) {
        auditService.logActivity("Manager " + managerEmail + " created/updated travel: " + travel.getDestinationName());
        return travelRepository.save(travel);
    }

    public Travel saveTravel(Travel travel, MultipartFile image, String managerEmail) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            travel.setImagePath(imagePath);
        }

        if (travel.getDiscountValidUntil() != null && travel.getDiscountValidUntil().before(new Date())) {
            travel.setDiscountPrice(null);
            travel.setDiscountValidUntil(null);
        }

        auditService.logActivity("Manager " + managerEmail + " created/updated travel: " + travel.getDestinationName());
        return travelRepository.save(travel);
    }

    private String saveImage(MultipartFile image) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
        Files.write(path, image.getBytes());
        return path.toString();
    }

    public Travel getTravelById(int id) {
        return travelRepository.findById(id).orElse(null);
    }

    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    public void deleteTravel(int id, String managerEmail) {
        Travel travel = travelRepository.findById(id).orElse(null);
        if (travel != null && reservationService.findByTravelId((long) travel.getId()).isEmpty()) {
            travelRepository.deleteById(id);
            auditService.logActivity("Manager " + managerEmail + " deleted travel: " + travel.getDestinationName());
        }
    }

    public List<Travel> getActiveDiscountTravels() {
        return travelRepository.findActiveDiscountTravels(new Date());
    }

    public List<Travel> getSeasonalTravels(String season) {
        return travelRepository.findSeasonalTravels(season);
    }

    public Page<Travel> searchTravels(TransportMode transportMode, BigDecimal minPrice, BigDecimal maxPrice, Integer nights, Pageable pageable) {
        return travelRepository.findTravelsByCriteria(transportMode, minPrice, maxPrice, nights, pageable);
    }
}
