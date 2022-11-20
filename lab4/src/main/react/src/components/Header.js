import React from 'react';
import PropTypes from 'prop-types'

// css styles
import '../css/header.css'

function Header() {
    return (
        <div className="header__row">
            <div className="header__title">
                <a href="">Andrew B, P32081</a>
            </div>
            <div className="header__menu menu">
                <div className="menu__icon">
                    <span/>
                </div>
                <div className="menu__body">
                    <ul className="menu__list">
                        <li className="menu__item">
                            <a>Start</a>
                        </li>
                        <li className="menu__item">
                            <a>Main</a>
                        </li>
                        <li className="menu__item">
                            <a href="https://github.com/averagefun/ITMO-WEB-LABS.git"
                               target="_blank">GitHub</a></li>
                    </ul>
                </div>
            </div>
        </div>
    )
}

Header.propTypes = {
}

export default Header