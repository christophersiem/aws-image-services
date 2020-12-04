import { createGlobalStyle } from 'styled-components';

export default createGlobalStyle`
  :root {

   --orange-main: #ec7211;
   
   --size-xs: 4px;
   --size-s: 8px;
   --size-m: 12px;
   --size-l: 16px;
   --size-xl: 24px;
   --size-xxl: 32px;
  }

  * {
    box-sizing: border-box;
  }
  
  html, body {
    margin: 0;
    font-family: 'Nunito', sans-serif;
  }
  `
