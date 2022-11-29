import {createSlice} from "@reduxjs/toolkit";

const tokenSlice = createSlice({
    name: "token",
    initialState: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIn0.WHU8XOkr5fjiLbDno2Q1gp_5bfZJPl_6bJMBNJeBHqBJ-WcLqoOENsWwuvOsj4yBPLtHMC6VK_BsEyvTl43FTg',
    reducers: {
        updateToken(state, action) {
            return action.payload;
        }
    }
})

export default tokenSlice.reducer
export const {updateToken} = tokenSlice.actions