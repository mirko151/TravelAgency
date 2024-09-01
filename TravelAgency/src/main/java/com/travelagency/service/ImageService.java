package com.travelagency.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private static final String UPLOAD_DIR = "uploads/";
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final String[] ALLOWED_TYPES = {"image/jpeg", "image/png"};

    public String saveImage(MultipartFile image) throws IOException {
        if (!isValidImage(image)) {
            throw new IllegalArgumentException("Nevažeći tip datoteke ili veličina. Dozvoljeni tipovi: JPG, PNG. Maksimalna veličina: 5MB.");
        }

        String uniqueFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
        Files.write(path, image.getBytes());
        return path.toString();
    }

    private boolean isValidImage(MultipartFile image) {
        return image.getSize() <= MAX_FILE_SIZE && isAllowedType(image.getContentType());
    }

    private boolean isAllowedType(String contentType) {
        for (String type : ALLOWED_TYPES) {
            if (type.equals(contentType)) {
                return true;
            }
        }
        return false;
    }
}
