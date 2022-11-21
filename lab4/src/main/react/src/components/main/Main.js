import React from 'react';

import Canvas from "./canvas/Canvas";
import Form from "./form/Form";
import Table from "./table/Table";

// css styles
import '../../css/general.css'
import '../../css/main.css'

const resultList = [
    {x: 1, y: 2, r: 2, isHit: true},
    {x: -1, y: -2, r: 1, isHit: true},
    {x: 3, y: -1, r: 1.5, isHit: true}
]

function Main() {
    return (
        <main className="main">
            <div className="container">
                <h1 className="main__title">Лабораторная работа #4, вариант 21321</h1>

                <div className="main__row">
                    <div className="main__left-block">
                        <Canvas />
                        <Form />
                    </div>

                    <div className="main__table-block">
                        <Table resultList={resultList} />
                    </div>
                </div>
            </div>
        </main>
    )
}

export default Main