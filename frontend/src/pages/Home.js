import React from 'react';
import Heading from "../commons/Heading";
import {useHistory} from "react-router-dom";


export default function Home() {
    const history = useHistory();
    return (
        <div>
            <Heading heading={"Welcome on my Super Duper AWS Page"}/>
            <p>Soon you will be able to check out different AWS Services here</p>
            <button onClick={() => history.push("/upload")}>1. Upload a Picture to AWS</button>
            <button onClick={() => history.push("/face")}>2. Analyze a face</button>
        </div>
    )
};


