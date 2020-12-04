package de.csiem.awsuploader.controller;

import de.csiem.awsuploader.model.FaceDto;
import de.csiem.awsuploader.service.ImageRecognitionService;
import de.csiem.awsuploader.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/image")

public class ImageController {

    private final ImageUploadService imageService;
    private final ImageRecognitionService imageRecognitionService;

    @Autowired
    public ImageController(ImageUploadService imageService, ImageRecognitionService imageRecognitionService) {
        this.imageService = imageService;
        this.imageRecognitionService = imageRecognitionService;
    }

    @PostMapping
    public String uploadImage(@RequestParam("image") MultipartFile file) throws IOException, InterruptedException {
        return imageService.uploadImage(file);
    }

    @PostMapping("/analyze")
    public FaceDto doFaceRecognition(@RequestParam("image") MultipartFile file) throws IOException, InterruptedException {
        return imageRecognitionService.doImageRecognition(file);
    }

}
