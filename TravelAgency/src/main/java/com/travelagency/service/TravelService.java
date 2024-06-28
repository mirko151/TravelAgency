package com.travelagency.service;

import com.travelagency.model.Travel;
import com.travelagency.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TravelService {
    @Autowired
    private TravelRepository travelRepository;

    public Travel saveTravel(Travel travel) {
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
        String folder = "uploads/";
        byte[] bytes = image.getBytes();
        Path path = Paths.get(folder + image.getOriginalFilename());
        Files.write(path, bytes);
        return path.toString();
    }
}
