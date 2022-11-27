import React from 'react';
import ReactDOM from 'react-dom/client';
import {Provider} from "react-redux";

import {tableStore} from "./store/store";
import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <Provider store={tableStore}>
    <App/>
    </Provider>
);

reportWebVitals();