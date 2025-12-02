import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {BrowserRouter,Route,Routes} from "react-router-dom"
// import StudentList from './list'
// import Counter from './counter'
import Home from './home.jsx'
import Contact from './contact.jsx'
import About from './About.jsx'
function App() {
  const [count, setCount] = useState(0)

  return (
    <>  
     <div class='card'>
    <BrowserRouter>
   
    <nav class='nav-bar'>
      <a href='/'>Home</a> | <a href='/about'>About</a> | <a href='/contact'>Contact</a>
    </nav>
    <Routes>
      <Route path='/' element={<Home/>}/>
      <Route path='/about' element={<About/>}/>
      <Route path='/contact' element={<Contact/>}/>
    </Routes>
    
    </BrowserRouter>
    </div>
       {/* <div className="card">
         <StudentList/> 
         <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button> 
        <Counter/>
      </div> 
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>  */}
    </>
  )
}

export default App
