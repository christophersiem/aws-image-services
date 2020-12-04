package de.csiem.awsuploader.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import de.csiem.awsuploader.model.FaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageRecognitionService {

    @Value("${aws.bucket.name}")
    String bucket = "bucket";

    private final ImageUploadService imageUploadService;

    @Autowired
    public ImageRecognitionService(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    public FaceDto doImageRecognition(MultipartFile file) throws IOException, InterruptedException {

        String signedUrl = imageUploadService.uploadImage(file);

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image()
                        .withS3Object(new S3Object()
                                .withName(file.getOriginalFilename())
                                .withBucket(bucket)))
                .withAttributes(Attribute.ALL);

        try {
            DetectFacesResult result = rekognitionClient.detectFaces(request);
            List<FaceDetail> faceDetails = result.getFaceDetails();
            FaceDto analyzedFace = new FaceDto();
            for (FaceDetail face : faceDetails) {
                analyzedFace.setAgeRange(face.getAgeRange());
                analyzedFace.setEmotions(face.getEmotions());
                analyzedFace.setGender(face.getGender());
                analyzedFace.setHasBeard(face.getBeard());
                analyzedFace.setSignedUrl(signedUrl);
                return analyzedFace;
            }

        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
        return null;

    }
}
