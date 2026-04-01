import type { Todo,TodoFilter } from '../types/todo';


export  function filterTodos(todos:Todo[], filter:TodoFilter) { //화면용데이터 - 지금 보여줄 목록
  switch (filter) {
    case 'active' :
        return todos.filter((t) => !t.done); // done 이 false (아직 안끝난거) - 미완료
    case 'completed':
      return todos.filter((t) => t.done); //done true 인것만 (완료된것) - 완료
    default:
      return todos;    //all 전체반환
  }
}
//filter 값에 따라 보여줄 Todo목록만 골라서 반환
// todos //전체할 일 목록 
//filter //현재필터 상태('all','active','completed')

/** 완료 / 미완료 개수 */
export function countByDone(todos:Todo[]) : { active:number; completed:number } {
  return todos.reduce( //reduce 개수집계 - 통계함수 -배열 한 번돌면서 집계
    (acc, t) => { //acc 는 누적값 ,t, 현재 todo 초기값{ active: 0, completed: 0 }
      if (t.done) acc.completed += 1;
      else acc.active += 1;
      return acc;
    },
    { active: 0, completed: 0 }
  );
}
