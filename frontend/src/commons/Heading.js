import React from 'react';
import styled from "styled-components/macro";

export default function Heading({heading}) {
    return (
        <HeadingStyled>{heading}</HeadingStyled>
    )
};

const HeadingStyled = styled.h1`
text-align: center;
font-size: 1.5rem;
`
