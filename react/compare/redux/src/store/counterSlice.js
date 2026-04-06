
import {createSlice} from "@reduxjs/toolkit"

// 카운터의 초기값 0 -> 전역상태 초기값 
const initialState ={
    value : 0,
};
//createSlice - 리덕스 핵심 3개
//name - 상태의 이름(구분) , state (데이터) , reducers(상태변경 로직 )
//createSlice = 상태+액션+리듀서를 한번에 만드는 도구
export const  counterSlice  = createSlice({
    name : "counter",
     initialState ,
    reducers:{
        increment: (state) => {
           state.value += 1;
        },
        decrement: (state) => {
           state.value -= 1;
        },
        reset: (state) => {
            state.value = 0;
        },
    },
});
export const { increment, decrement, reset } = counterSlice.actions;
// dispatch( increment) - >버튼클릭시 사용할 '명령어'
export default counterSlice.reducer;
//store 에 등록할 상태 관리함수
//버튼클릭 ->  dispatch( increment) -> reducers 실행 ->  state.value 1증가-> 화면 업데이트


//jsx 문법으로 html 변환하는 데  jsx없음 저장시 에러나면 .js확장자로 변환