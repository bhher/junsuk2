import { useCallback, useEffect, useState } from 'react';

function Card({ card, onDelete, onUpdate, onDragStart, onDragEnd }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editText, setEditText] = useState(card.text);

useEffect (()=>{
      setEditText(card.text);
},[card.text]);
//card.text : 부모(App) 들고있는 원본데이터
//editText : 사용자가 키보드로 치고있는임시데이터
//이두가지를 동기화시키기위해 존재 


  const handleSave = useCallback( () => {
    const trimmed = editText.trim();
    if (trimmed && trimmed !== card.text) {
      onUpdate(card.id, trimmed);
    } else if (!trimmed) {
      setEditText(card.text);
    } 
    setIsEditing(false);
  },[editText, card.id, card.text, onUpdate ]);

  const handleKeyDown = useCallback((e) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      handleSave();
    }
    if (e.key === 'Escape') {
      setEditText(card.text);
      setIsEditing(false);
    }
  },[handleSave, card.text]);

const handleDoubleClick =useCallback(()=>{
   setIsEditing(true);
},[]);

 const handleDelete = useCallback(() => {
    onDelete(card.id);
  }, [onDelete, card.id]);

const handleDragStart = useCallback((e) => onDragStart(e, card),
[onDragStart, card]);

  if (isEditing) {
    return (
      <div className="card card-editing">
        <textarea
          value={editText}
          onChange={(e) => setEditText(e.target.value)}
          onBlur={handleSave}
          onKeyDown={handleKeyDown}
          autoFocus
          rows={3}
        />
      </div>
    );
  }

  return (
    <div
      className="card"
      draggable
      onDragStart={handleDragStart}
      onDragEnd={onDragEnd}
    >
      <p className="card-text" onDoubleClick={handleDoubleClick}>
        {card.text}
      </p>
      <button
        className="card-delete"
        onClick={handleDelete}
        aria-label="삭제"
      >
        ×
      </button>
    </div>
  );
}

export default Card;
