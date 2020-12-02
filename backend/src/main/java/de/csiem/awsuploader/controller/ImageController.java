package de.csiem.awsuploader.controller;

import de.csiem.awsuploader.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/image")

public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public String uploadImage(@RequestParam ("image")MultipartFile file) throws IOException,InterruptedException {
        return imageService.uploadImage(file);
    }

}
