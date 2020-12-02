import React, {useState} from 'react';
import ImageUpload from "../components/ImageUpload";
import {uploadImage} from "../service/upload-service";
import Heading from "../commons/Heading";


export default function ImageUploadPage() {
    const [image, setImage] = useState();
    const [imageUrl, setImageUrl] = useState();
    const [showImage, setShowImage] = useState(false)

    return (
        <>
            <Heading heading={"This is my Super Duper Uploader"}/>
            <ImageUpload handleImage={handleImage} upload={upload} image={image}/>
            {imageUrl && <p>Image successfully uploaded to AWS</p>}
            <button disabled={!imageUrl} onClick={()=>setShowImage(!showImage)}>Show / Hide Image</button>
            {showImage && <img src={imageUrl} alt=""/>}
        </>
    )

    function handleImage(event) {
        const file = event.target.files[0];
        setImage(file);
    }

    function upload() {
        uploadImage(image).catch(e => console.error(e)).then(setImageUrl)
    }
};

