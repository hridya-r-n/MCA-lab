import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import {BrowserRouter,Routes,Route,Link} from "react-router-dom"
import Home from "./home.jsx"
import Employee from "./employee.jsx"
function App() {

  return (
    <>
      <div>
       <BrowserRouter>

       <Link to='/'>Home</Link> | <Link to='/employee'>Employee</Link>
       
       <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/employee' element={<Employee/>}/>
       </Routes>
       </BrowserRouter>
      </div>
    </>
  )
}

export default App
