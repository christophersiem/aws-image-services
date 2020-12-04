package de.csiem.awsuploader.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import de.csiem.awsuploader.utils.AmazonS3ClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class ImageUploadService {

    private final AmazonS3ClientUtils amazonS3ClientUtils;

    @Value("${aws.access.key}")
    private String accessKey;
    @Value("${aws.secret.key}")
    private String secretKey;
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    public ImageUploadService(AmazonS3ClientUtils amazonS3ClientUtils) {
        this.amazonS3ClientUtils = amazonS3ClientUtils;
    }

    public String uploadImage(MultipartFile file) throws IOException, InterruptedException{

        Regions clientRegion = Regions.EU_CENTRAL_1;

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        try {
            AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest request = new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
            s3client.putObject(request);

            return getSignedImageUrl(file.getOriginalFilename());
        } catch (AmazonServiceException e){
            e.printStackTrace();
        } catch (SdkClientException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getSignedImageUrl(String imageName) {
        AmazonS3 s3Client = amazonS3ClientUtils.getS3Client();
        Date expiration = getExpirationTime();
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, imageName)
                .withMethod(HttpMethod.GET).withExpiration(expiration);
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();

    }
    private Date getExpirationTime() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}
