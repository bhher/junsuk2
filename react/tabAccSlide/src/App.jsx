import React from 'react'
import TabMenu from "./components/TabMenu"
import AccordionMenu from "./components/AccordionMenu"

import "./App.css"
import Slider from './components/Slider'

function App() {
  return (
    <div className='app'>
      <div className="app-container">
        <h1>리액트 탭 & 아코디언 & 슬라이드
            <TabMenu />
            <AccordionMenu/>
            <Slider/>
        </h1>
      </div>
    </div>
  )
}

export default App