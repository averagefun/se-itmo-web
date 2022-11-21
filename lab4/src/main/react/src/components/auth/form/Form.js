import React from 'react'

function Form() {
    return (
        <form className="form form__auth" method="post">
            <label className="form__label">Логин</label>
            <div className="form__row">
                <input name="login"
                       required className="form__number-input"
                       type="text" placeholder=""/>
            </div>

            <label className="form__label">Пароль</label>
            <div className="form__row">
                <input name="password"
                       required className="form__number-input"
                       type="password" placeholder=""/>
            </div>

            <div className="form__row">
                <button className="form__big-btn" disabled type="submit">Войти</button>
                <button className="form__big-btn" type="reset">Регистрация</button>
            </div>
        </form>
    )
}

export default Form;
