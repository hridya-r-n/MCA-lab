import { useState } from 'react'

import './App.css'
import { BrowserRouter,Route,Routes,Link} from 'react-router-dom'
import Home from './home'
import Students from './students'
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        
      </div>
      <div className="card">
       <BrowserRouter>
        <Link to="/">Home</Link> | <Link to="/students">Students</Link>
        <Routes>
          <Route path="/" element={<Home/>}/>
        <Route path="/students" element={<Students/>}/>
        </Routes>
        </BrowserRouter>
      </div>
      
    </>
  )
}

export default App
