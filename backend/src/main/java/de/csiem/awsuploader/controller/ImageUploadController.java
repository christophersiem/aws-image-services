package de.csiem.awsuploader.controller;

import de.csiem.awsuploader.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/image")

public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @Autowired
    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @PostMapping
    public String uploadImage(@RequestParam ("image")MultipartFile file) throws IOException,InterruptedException {
        return imageUploadService.uploadImage(file);
    }

}
