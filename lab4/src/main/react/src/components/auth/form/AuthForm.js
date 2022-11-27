import React from 'react'
import {useStateWithCallbackLazy} from 'use-state-with-callback';

import {validateTextInput} from "../../../utils/validation";

function AuthForm({ loginURL, registerURL }) {

    const [inputValues, setInputValues] = useStateWithCallbackLazy({
        username: {value: '', validity: false},
        password: {value: '', validity: false}
    });

    const [isButtonsAvailable, setButtonsAvailable] = React.useState(false);

    function toggleButtons(inputValues) {
        if (inputValues.username.validity && inputValues.password.validity) {
            setButtonsAvailable(true);
        } else {
            setButtonsAvailable(false);
        }
    }

    function validateInput(inputField) {
        const validityResult = validateTextInput(inputField)

        setInputValues({...inputValues,
            [inputField.name]: {value: inputField.value, validity: validityResult}
        }, toggleButtons);
    }

    function loginHandler(event) {
        event.preventDefault();
        const body = {
            'username': inputValues.username.value,
            'password': inputValues.password.value
        }
        ajaxPost(event, body, loginURL);
    }

    function registerHandler(event) {
        event.preventDefault();
        const body = {
            'username': inputValues.username.value,
            'password': inputValues.password.value
        }
        ajaxPost(event, body, registerURL);
    }

    return (
        <form className="form form__auth">
            <label className="form__label">Логин</label>
            <div className="form__row">
                <input name="username" onInput={event => validateInput(event.target)}
                       required className="form__number-input"
                       type="text" defaultValue=""/>
            </div>

            <label className="form__label">Пароль</label>
            <div className="form__row">
                <input name="password" onInput={event => validateInput(event.target)}
                       required className="form__number-input"
                       type="password" defaultValue=""/>
            </div>

            <div className="form__row">
                <button className="form__big-btn" disabled={!isButtonsAvailable} type="sign_in"
                        onClick={event => loginHandler(event)}>Войти
                </button>
                <button className="form__big-btn" disabled={!isButtonsAvailable} type="sign_up"
                        onClick={event => registerHandler(event)}>Регистрация
                </button>
            </div>
        </form>
    )
}

export default AuthForm;

function ajaxPost(event, body, target) {
    console.log(body)

    const xhr = new XMLHttpRequest();
    xhr.open('POST', target);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onloadend = () => {
        if (xhr.status === 200) {
            console.log('Success');
        } else {
            console.log('Status: ', xhr.status);
        }
    };

    xhr.send(JSON.stringify(body));
}