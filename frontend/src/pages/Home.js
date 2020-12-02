import React from 'react';
import Heading from "../commons/Heading";
import {useHistory} from "react-router-dom";

export default function Home() {
    const history = useHistory();
    return (
        <>
            <Heading heading={"Welcome on my Super Duper Page"}/>
            <p>Soon you will be able to check out different AWS Services here</p>
            <button onClick={() => history.push("/upload")}>1. Upload a Picture to AWS</button>
        </>
    )
};
