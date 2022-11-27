import {combineReducers, configureStore} from "@reduxjs/toolkit"
import resultTableSlice from "./resultTableSlice";

const rootReducer = combineReducers({
    resultTable: resultTableSlice
})

export const tableStore = configureStore({
    reducer: rootReducer
})