import React from 'react'
import './App.css';

import styled from 'styled-components/macro'
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Redirect
} from "react-router-dom";

import ImageUploadPage from "./pages/ImageUploadPage";
import Home from "./pages/Home";
import FaceAnalyzePage from "./pages/FaceAnalyzePage";


function App() {

    return (
        <Router>
            <WrapperStyled>
                <Switch>
                    <Route path="/home" component={Home}/>
                    <Route path="/upload" component={ImageUploadPage}/>
                    <Route path="/face" component={FaceAnalyzePage}/>
                    <Route path="/"><Redirect to="/home"/> </Route>
                </Switch>
            </WrapperStyled>
        </Router>
    )
}

const WrapperStyled = styled.div`
display: flex;
flex-direction: column;
align-items: center;
background-color: #333;
height: 100vh;
color: whitesmoke;
overflow: scroll;
`

export default App;
