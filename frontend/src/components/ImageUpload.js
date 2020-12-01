import React from 'react';

export default function ImageUpload({handleImage, upload, image}) {
    return (
        <>
            <input type={"file"} accept="image/*" onChange={handleImage} />
            <button disabled={!image} onClick={upload}>Upload</button>
        </>
    )
}


