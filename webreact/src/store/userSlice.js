import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
    name: 'user',
    initialState: {
        user: {
            bebra: 'bebra'
        }
    },
    reducers: {
        set: (state, action) => {
            //state.user = action.payload
            state.user = {
                hera: "herya"
            }
        },
        unset: (state) => {
            state.user = null
        }
    }
})

export const {set, unset} = userSlice.actions

export default userSlice.reducer
