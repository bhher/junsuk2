import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage';

import React from 'react'
import Layout from './components/Layout';
import JoinPage from './pages/JoinPage';
import HomePage from './pages/HomePage';


function App() {
  return (
    <Routes>    
        <Route path="/" element={<Layout />} >
          <Route index element={<HomePage />} />
          <Route path='login' element={<LoginPage/>}/>
          <Route path='join' element={<JoinPage/>}/>
        </Route>
    </Routes>
  )
}

export default App