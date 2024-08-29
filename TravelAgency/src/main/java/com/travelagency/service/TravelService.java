package com.travelagency.service;

import com.travelagency.model.Travel;
import com.travelagency.model.TransportMode;
import com.travelagency.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

    public Travel saveTravel(Travel travel) {
        return travelRepository.save(travel);
    }

    public Travel saveTravel(Travel travel, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            travel.setImagePath(imagePath);
        }
        return travelRepository.save(travel);
    }

    public Travel getTravelById(int id) {
        return travelRepository.findById(id).orElse(null);
    }

    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    public void deleteTravel(int id) {
        travelRepository.deleteById(id);
    }

    public String saveImage(MultipartFile image) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
        Files.write(path, image.getBytes());
        return path.toString();
    }

    public List<Travel> searchTravels(TransportMode transportMode, BigDecimal minPrice, BigDecimal maxPrice, Integer nights) {
        Specification<Travel> spec = Specification.where(null);

        if (transportMode != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("transportMode"), transportMode));
        }
        if (minPrice != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (nights != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nights"), nights));
        }

        return travelRepository.findAll(spec);
    }

    public List<Travel> getActiveDiscountedTravels() {
        return travelRepository.findAll((root, query, criteriaBuilder) -> 
            criteriaBuilder.and(
                criteriaBuilder.isNotNull(root.get("discountPercentage")),
                criteriaBuilder.greaterThan(root.get("discountExpirationDate"), new Date())
            )
        );
    }

    public List<Travel> getRandomTravelsBySeason(int count, String season) {
        Pageable pageable = PageRequest.of(0, count);
        return travelRepository.findByCategoryName(season, pageable);
    }
}
