package de.csiem.awsuploader.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageUploadService {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    public String uploadImage(MultipartFile file) throws IOException, InterruptedException{

        Regions clientRegion = Regions.EU_CENTRAL_1;
        String bucketName = "image-upload-demo-repo";

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        try {
            AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest request = new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
            s3client.putObject(request);

            return file.getName();
        } catch (AmazonServiceException e){
            e.printStackTrace();
        } catch (SdkClientException e){
            e.printStackTrace();
        }
        return "";
    }
}
