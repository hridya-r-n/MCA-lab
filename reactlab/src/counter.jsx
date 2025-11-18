// Create a React class component called Counter that displays a number and has two buttons: Increment and Decrement. 
// The component should manage its own state and update the displayed number when the buttons are clicked.
import React, { useState } from "react";
import './App.css';
function Counter() {
    const [count, setCount] = useState(0)
    return (
        <div>
            <h1>{count}</h1>
            <div class="res">
                <button onClick={() => setCount((count) => count + 1)}>
                Increment
            </button>
            <button onClick={() => setCount((count) => count - 1)}>
                Decrement
            </button>
            </div>
        </div>

    )
}
export default Counter;