import React from 'react'
import Header from './components/header/Header'
import Auth from "./components/auth/Auth";
import Footer from "./components/footer/Footer";

// css styles
import './css/base.css'

function App() {
    return (
        <div className="wrapper">
            <Header/>
            <Auth/>
            <Footer/>
        </div>
    )
}

export default App;
