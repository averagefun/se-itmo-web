import React from 'react';
import {useDispatch, useSelector} from "react-redux";

import Canvas from "./canvas/Canvas";
import Form from "./form/Form";
import Table from "./table/Table";

// css styles
import '../../css/general.css'
import '../../css/main.css'
import {updateTable} from "../../store/resultTableSlice";

const TOKEN = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIn0.WHU8XOkr5fjiLbDno2Q1gp_5bfZJPl_6bJMBNJeBHqBJ-WcLqoOENsWwuvOsj4yBPLtHMC6VK_BsEyvTl43FTg';
let isInitialize = false

function Main() {
    const dispatch = useDispatch();

    const getResultData = () => {
        return fetch('http://localhost:8080/lab4/api/results', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'AUTHORIZATION': TOKEN
            }}).then((res) => {
            if (res.ok) {
                return res.json()
            }
        })
    }

    if (!isInitialize) {
        getResultData().then(tableData => dispatch(updateTable(tableData)));
        isInitialize = true;
    }

    return (
        <main className="main">
            <div className="container">
                <h1 className="main__title">Лабораторная работа #4, вариант 21321</h1>

                <div className="main__row">
                    <div className="main__left-block">
                        <Canvas rDefault={1}/>
                        <Form rDefault={1}/>
                    </div>

                    <div className="main__table-block">
                        <Table resultList={useSelector(state => state.resultTable)} />
                    </div>
                </div>
            </div>
        </main>
    )
}

export default Main