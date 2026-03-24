import { useState } from 'react';
import Column from './Column';

function KanbanBoard({ columns, cards, onAddCard, onMoveCard, onDeleteCard, onUpdateCard }) {
  const [draggedCard, setDraggedCard] = useState(null);
  const [dragOverColumn, setDragOverColumn] = useState(null);

  const handleDragStart = (e, card) => {
    setDraggedCard(card);
    e.dataTransfer.effectAllowed = 'move';
    e.dataTransfer.setData('text/plain', card.id);
    e.target.style.opacity = '0.5';
  };
  //setData(format,data)  //택배상자에 물건을 넣는 과정 
  //.effectAllowed 어떤동작이 근ㅇ한지 설정 move(이동) , copy(복사) , link (연결) 
  //e.dataTransfer - 브라우저 표준방식 (안정적)

  //draggedCard : 복잡한 객체 데이터 전체 다룰때 id, 카드에내용(content), 날짜 정보를 참조해서
  //카드의 배경색변경 - 실시간 피드백을 주기좋음
  const handleDragEnd = (e) => {
    e.target.style.opacity = '1';
    setDraggedCard(null);
    setDragOverColumn(null);
  };

  const handleDragOver = (e, columnId) => {
    e.preventDefault();
    e.dataTransfer.dropEffect = 'move';
    setDragOverColumn(columnId);
  };

  const handleDragLeave = () => {
    setDragOverColumn(null);
  };

  const handleDrop = (e, targetColumnId) => {
    e.preventDefault();
    const cardId = e.dataTransfer.getData('text/plain');
    if (cardId && draggedCard && draggedCard.columnId !== targetColumnId) {
      onMoveCard(cardId, targetColumnId);
    }
    //getData(format) : 목적지에 도착해서 상자를열어 물건을 꺼낸다.
    setDraggedCard(null);
    setDragOverColumn(null);
  };

  return (
    <main className="board">
      {columns.map((column) => (
        <div
          key={column.id}
          className={`column-wrapper ${dragOverColumn === column.id ? 'drag-over' : ''}`}
          onDragOver={(e) => handleDragOver(e, column.id)}
          onDragLeave={handleDragLeave}
          onDrop={(e) => handleDrop(e, column.id)}
        >
          <Column
            column={column}
            cards={cards.filter((c) => c.columnId === column.id)}
            onAddCard={onAddCard}
            onDeleteCard={onDeleteCard}
            onUpdateCard={onUpdateCard}
            onDragStart={handleDragStart}
            onDragEnd={handleDragEnd}
            isDragging={draggedCard?.columnId === column.id}
          />
        </div>
      ))}
    </main>
  );
}

export default KanbanBoard;
