import React from 'react'
import Header from './components/Header'
import Footer from "./components/Footer";
import Main from "./components/Main";

// css styles
import './css/base.css'

function App() {
    return (
        <div className="wrapper">
            <div className="header">
                <div className="container">
                    <Header />
                </div>
            </div>

            <div className="main">
                <div className="container">
                    <Main />
                </div>
            </div>

            <div className="footer">
                <div className="container">
                    <Footer />
                </div>
            </div>
        </div>
)
}

export default App;
