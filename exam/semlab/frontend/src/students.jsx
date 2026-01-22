import { useState,useEffect } from "react";
import { useFetcher } from "react-router-dom";


function Students(){
    const [students,setStudents]=useState([])
    const [form,setForm]=useState({name:"",email:"",age:""})
    const [editId,setEditId]=useState(null)
    const fetchStudents=()=>{
        fetch("http://localhost:5000/students").then(res=>res.json()).then(data=>setStudents(data))
    }
    useEffect(()=>{
        fetchStudents()
    },[]);
    const handleChange=e=>{
        setForm({...form,[e.target.name]:e.target.value})
    };
    const submit=()=>{
        if(editId){
            console.log("submit")
            fetch(`http://localhost:5000/students/${editId}`,{
                method:"PUT",
                headers: {"Content-Type":"application/json"},
                body:JSON.stringify(form)
            }).then(()=>{
                setEditId(null)
                setForm({name:"",email:"",age:""})
                fetchStudents()
            });
        }
        else{
            fetch("http://localhost:5000/students",{
                method:"POST",
                headers: {"Content-Type":"application/json"},
                body:JSON.stringify(form)
            }).then(()=>{
                setForm({name:"",email:"",age:""})
                fetchStudents()
            });
        }
    }
    const editStudent=s=>{
        setEditId(s.id)
        setForm({name:s.name,email:s.email,age:s.age})
    };
    const deleteStudent=id=>{
        fetch(`http://localhost:5000/students/${id}`,{
                method:"DELETE"}).then(()=>{
                fetchStudents()
            });
    };
    return(
        <div>
            <h2>Add Details</h2>
            <div>Name:<input type="text" name="name" placeholder="Name" value={form.name} onChange={handleChange}/></div><br/>
            <div>Email:<input type="text" name="email" placeholder="Email" value={form.email} onChange={handleChange}/></div><br/>
            <div>Age:<input type="text" name="age" placeholder="Age" value={form.age} onChange={handleChange}/></div><br/>
            
            <button type="button"onClick={submit}>
                {editId?"Update":"Add"}
            </button>
            <br></br>
            <h2>Students</h2>
            <hr></hr>

            {students.map(s=>(
                <div key={s.id} className="list" >
                    {s.name} | {s.email} | {s.age}
                    <div>
                    <button type="button" onClick={()=>editStudent(s)}>Edit</button>
                   
                    <button type="button" onClick={()=>deleteStudent(s.id)}>Delete</button>
                    </div>
                    <hr></hr>
                </div>
            ))}
        </div>
    )
}
export default Students