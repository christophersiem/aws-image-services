import React, {useState} from 'react';
import ImageUpload from "../components/ImageUpload";
import {analyzeImage} from "../service/upload-service";
import Heading from "../commons/Heading";
import styled from "styled-components/macro";

export default function FaceAnalyzePage() {
    const [image, setImage] = useState();
    const [faceData, setFaceData] = useState()

    return (
        <WrapperStyled>
            <Heading heading={"This is my Super Duper Face Analyzer"}/>
            <ImageUpload handleImage={handleImage} upload={upload} image={image}/>
            {faceData &&
            <div>
                {faceData && <ImageStyled src={faceData.signedUrl} alt=""/>}
                <p>You are {faceData?.gender?.value} and {getAge()} years old</p>
                {faceData.emotions?.map(emotion => <p>{emotion.type}: {Math.round(emotion.confidence * 100) / 100}%</p>)}
            </div>}
        </WrapperStyled>
    )

    function handleImage(event) {
        const file = event.target.files[0];
        setImage(file);
    }

    function upload() {
        analyzeImage(image).catch(e => console.error(e)).then(setFaceData)
    }

    function getAge() {
        const age = (faceData.ageRange?.low + faceData?.ageRange?.high) / 2
        return age.toString();
    }
};

const WrapperStyled = styled.div`
display: grid;
grid-template-columns: 1fr;
justify-items: center;
gap: 10px 0;
`
const ImageStyled = styled.img`
max-width: 90%;
height: auto;
`

