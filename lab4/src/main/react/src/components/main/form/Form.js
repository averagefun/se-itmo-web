import React from 'react';
import {useStateWithCallbackLazy} from 'use-state-with-callback';

import {useDispatch} from "react-redux";
import {addResult, clearTable} from "../../../store/resultTableSlice";
import {validateTextInput} from "../../../utils/validation";

import ListBox from "./list_box/ListBox";

const TOKEN = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIn0.WHU8XOkr5fjiLbDno2Q1gp_5bfZJPl_6bJMBNJeBHqBJ-WcLqoOENsWwuvOsj4yBPLtHMC6VK_BsEyvTl43FTg';

function Form({rDefault}) {
    const dispatch = useDispatch();

    const xDefault = 0, yDefault = 0;
    const [inputValues, setInputValues] = useStateWithCallbackLazy({
        x: {value: xDefault, validity: true},
        y: {value: yDefault, validity: false},
        r: {value: rDefault, validity: true}
    });

    const [isSubmitAvailable, setSubmitAvailable] = React.useState(false);

    function toggleSubmitButton(inputValues) {
        if (inputValues.x.validity && inputValues.y.validity && inputValues.r.validity) {
            setSubmitAvailable(true);
        } else {
            setSubmitAvailable(false);
        }
    }

    function validateInput(inputField) {
        const validityResult = validateTextInput(inputField);

        setInputValues({...inputValues,
            [inputField.name]: {value: parseFloat(inputField.value), validity: validityResult}
        }, toggleSubmitButton);
    }

    function validateListBoxItem(listBox) {
        setInputValues({...inputValues,
            [listBox.name]: {value: parseFloat(listBox.value), validity: true}
        }, toggleSubmitButton);
    }

    const submitHandler = (event) => {
        event.preventDefault();

        fetch('http://localhost:8080/lab4/api/results', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'AUTHORIZATION': TOKEN
            },
            body: JSON.stringify({
                x: inputValues.x.value,
                y: inputValues.y.value,
                r: inputValues.r.value
            })
        }).then((res) => {
            if (res.ok) {
                return res
                    .json()
                    .then(newResult => dispatch(addResult(newResult)))
            }
        })
    }

    const clearFields = () => {

    }

    const clearHandler = (event) => {
        event.preventDefault();
        clearFields();
        fetch('http://localhost:8080/lab4/api/results', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'AUTHORIZATION': TOKEN
            }
        }).then((res) => {
            if (res.ok) {
                dispatch(clearTable());
            }
        })
    }

    return (
        <form className="form" method="get">
            <label className="form__label">X</label>
            <div className="form__row">
                <ListBox name="x"
                         items={generateItems(-2, 2, 0.5)}
                         selected={xDefault}
                         onInput={event => validateListBoxItem(event.target)}/>
            </div>

            <label className="form__label">Y</label>
            <div className="form__row">
                <input name="y"
                       required className="form__number-input"
                       type="number" step="any" min="-5" max="3"
                       placeholder="Введите значение [-5 ... 3]"
                       onInput={event => validateInput(event.target)}/>
            </div>

            <label className="form__label">R</label>
            <div className="form__row">
                <ListBox name="r" items={generateItems(-2, 2, 0.5)}
                         selected={rDefault}
                         onInput={event => validateListBoxItem(event.target)}/>
            </div>

            <div className="form__row">
                <button className="form__big-btn" type="submit"
                        disabled={!isSubmitAvailable}
                        onClick={event => submitHandler(event)}>Отправить</button>
                <button className="form__big-btn" type="reset"
                        onClick={event => clearHandler(event)}>Очистить</button>
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
