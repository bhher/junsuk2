import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { increment, decrement, reset } from "../store/counterSlice";

export default function Counter() {
   const count = useSelector((state) => state.counter.value); //count읽어오기
    const dispatch = useDispatch(); //state 변경 ->redux 에서 액션실행 요청 하는 함수
  //"redux" 의 상태를 변경해 달하는 함수
  return (
    <div className="counter">
      <div className="counter-display">{count}</div>
      <div className="button-group">
        <button onClick={() => dispatch(decrement())}>-</button>
        <button onClick={() => dispatch(reset())}>Reset</button>
        <button onClick={() => dispatch(increment())}>+</button>
      </div>
    </div>
  );
}
// - 클릭
// dispatch(decrement()) -> reducer 실행 -> state.value -1 -> 화면 업데이트