import React, {useState} from 'react'
import './App.css';
import {uploadImage} from "./service/upload-service";
import styled from 'styled-components/macro'
import ImageUpload from "./components/ImageUpload";

function App() {
    const [image, setImage] = useState();
    const [imageUrl, setImageUrl] = useState();
    return (
        <WrapperStyled>
            <HeadingStyled>This is my Super Duper Uploader</HeadingStyled>
            <ImageUpload
                handleImage={handleImage}
                upload={upload}
                image={image}
            />
            <p>{imageUrl && "Image successfully uploaded"}</p>
        </WrapperStyled>

    )

    function handleImage(event) {
        const file = event.target.files[0];
        setImage(file);
    }

    function upload() {
        uploadImage(image).catch(e => console.error(e)).then(setImageUrl)
    }

}

const HeadingStyled = styled.h1`
text-align: center;
font-size: 1.5rem;
`
const WrapperStyled = styled.div`
display: flex;
flex-direction: column;
align-items: center;
align-content: space-around;

`

export default App;
