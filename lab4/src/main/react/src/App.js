import React from 'react'
import Header from './components/header/Header'
import Auth from "./components/auth/Auth";
import Footer from "./components/footer/Footer";

// css styles
import './css/base.css'
import Main from "./components/main/Main";

function App() {
    return (
        <div className="wrapper">
            <Header/>
            <Main/>
            <Footer/>
        </div>
    )
}

export default App;
