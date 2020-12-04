package de.csiem.awsuploader.model;

import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.Beard;
import com.amazonaws.services.rekognition.model.Emotion;
import com.amazonaws.services.rekognition.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceDto {

    private AgeRange ageRange;
    private Gender gender;
    private Beard hasBeard;
    private List<Emotion> emotions;
    private String signedUrl;

}
