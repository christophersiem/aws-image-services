import React from 'react';
import styled from 'styled-components/macro'

export default function ImageUpload({handleImage, upload, image}) {
    return (
        <>
            <InputStyled type={"file"} title={" "} accept={"image/*"} onChange={handleImage} />
            <button disabled={!image} onClick={upload}>Upload</button>
        </>
    )
}

const InputStyled = styled.input`
margin: auto;
`



