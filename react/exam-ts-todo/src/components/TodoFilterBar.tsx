import type { TodoFilter } from "../types/todo";

export interface TodoFilterBarProps {
  filter: TodoFilter;
  onChange: (filter: TodoFilter) => void;
  activeCount: number;
  completedCount: number;
}

const FILTERS : {value :TodoFilter; label : string } [] = [ //버튼 정보르 데이터 관리
  { value: 'all', label: '전체' },
  { value: 'active', label: '할 일' },
  { value: 'completed', label: '완료' },
];


function TodoFilterBar({filter, onChange,activeCount, completedCount}:TodoFilterBarProps ) {
    //filter -> 현재 선택된 필터
    // onChange -> 필터 변경합수
    //activeCount -> 미완료 갯수
    //completedCount -> 완료개수
  return (
    <div className='filter-bar'>
        {FILTERS.map(({value, label})=>( //배열돌면서 버튼 생성 - 전체,할 일, 완료
         <button key={value}
          type='button' className={filter === value ? 'filter active' : 'filter'} //강조
         onClick={() => onChange(value)}> 
         {/* 클릭하여 부모로 값을 전달 */}
            {label} 
            {value === 'active' ? `(${activeCount})`: null}
            {value === 'completed' ? ` (${completedCount})` : null}
        </button>   
) )}
    </div>
  )
}

export default TodoFilterBar
