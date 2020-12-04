import React, {useState} from 'react';
import ImageUpload from "../components/ImageUpload";
import Heading from "../commons/Heading";
import styled from "styled-components/macro";
import { uploadImage} from "../service/upload-service";

export default function ImageUploadPage() {
    const [image, setImage] = useState();
    const [imageUrl, setImageUrl] = useState();
    const [showImage, setShowImage] = useState(false)

    return (
        <WrapperStyled>
            <Heading heading={"This is my Super Duper Image - to - AWS - Uploader"}/>
            <ImageUpload handleImage={handleImage} upload={upload} image={image}/>
            {imageUrl && <p>Image successfully uploaded to AWS</p>}
            <button disabled={!imageUrl} onClick={()=>setShowImage(!showImage)}>Show / Hide Image</button>
            {showImage && <ImageStyled src={imageUrl} alt=""/>}
        </WrapperStyled>
    )

    function handleImage(event) {
        const file = event.target.files[0];
        setImage(file);
    }

    function upload() {
        uploadImage(image).catch(e => console.error(e)).then(setImageUrl)
    }
};

const WrapperStyled = styled.div`
display: grid;
grid-template-columns: 1fr;
grid-template-rows: 1fr 1fr 0.5fr 0.5fr max-content;
justify-items: center;
gap: 10px 0;
`

const ImageStyled = styled.img`
max-width: 90%;
height: auto;
`
