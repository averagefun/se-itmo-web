import React from 'react';
import ListBox from "./list_box/ListBox";

function Form() {
    return (
        <form className="form" method="get">
            <label className="form__label">X</label>
            <div className="form__row">
                <ListBox name="x" items={generateItems(-2, 2, 1)}/>
            </div>

            <label className="form__label">Y</label>
            <div className="form__row">
                <input name="y"
                       required className="form__number-input"
                       type="number" step="any" min="-5" max="3"
                       placeholder="Введите значение [-5 ... 3]"/>
            </div>

            <label className="form__label">R</label>
            <div className="form__row">
                <ListBox name="r" items={generateItems(-2, 2, 1)}/>
            </div>

            <div className="form__row">
                <button className="form__big-btn" disabled type="submit">Отправить</button>
                <button className="form__big-btn" type="reset">Очистить</button>
            </div>
        </form>
    )
}

export default Form

function generateItems(min, max, step) {
    const xItems = [];
    let id = 0;
    for (let x = min; x <= max; x+=step) {
        xItems.push({
            id: id,
            value: x
        });
        id++;
    }
    return xItems;
}