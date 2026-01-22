import { useNavigate } from "react-router-dom";
import {useState,useEffect} from "react"
import "./App.css"
function Employee(){
    const [employee,setEmployee]=useState([])
    const [form,setForm]=useState({name:"",email:"",dept:""})
    const [editId,setEditId]=useState(null)
    const navigate=useNavigate()
    const fetchEmployees=()=>{
        fetch("http://localhost:5000/employee").then(res=>res.json()).then(data=>setEmployee(data))
    }
    useEffect(()=>{
        fetchEmployees()
    },[]);
    const handleChange=e=>{
        setForm({...form,[e.target.name]:e.target.value})
    }
    const submit=()=>{
        if(editId){
            fetch(`http://localhost:5000/employee/${editId}`,{
                method:"PUT",
                headers:{"Content-Type":"application/json"},
                body:JSON.stringify(form)
            }).then(()=>{
                setEditId(null)
                setForm({name:"",email:"",dept:""})
                fetchEmployees()
            })
        }else{
            fetch("http://localhost:5000/employee",{
                method:"POST",
                headers:{"Content-Type":"application/json"},
                body:JSON.stringify(form)
            }).then(()=>{
                setForm({name:"",email:"",dept:""})
                fetchEmployees()
            })
        }
    }
    const editEmployee=em=>{
        setEditId(em.id)
        setForm({name:em.name,email:em.email,dept:em.dept})
    }
    const deleteEmployee=id=>{
        fetch(`http://localhost:5000/employee/${id}`,{method:"DELETE"}).then(fetchEmployees())
    }
    return(
        <div>
            <h2>Add Details</h2>
            <div>Name:<input type="text" name="name" value={form.name} placeholder="Name" onChange={handleChange}/></div>
            <div>Email:<input type="text" name="email" value={form.email} placeholder="Email" onChange={handleChange}/></div>
            <div>Department:<input type="text" name="dept" value={form.dept} placeholder="Department" onChange={handleChange}/></div>
            <br></br>
            <div><button type="button" onClick={submit}>{editId?"Update":"Add"}</button></div>
            <br></br>
            <h2>Employee</h2>
            <hr></hr>
            {employee.map(em=>(
                <div key={em.id}>
                    <p>{em.name} | {em.email} | {em.dept} </p>
                    <div>
                        <button type="button" onClick={()=>editEmployee(em)}>Edit</button>
                        <button type="button" onClick={()=>deleteEmployee(em.id)}>Delete</button>
                    </div>
                </div>
            ))}
            <hr></hr>
            <button type="button" onClick={()=>navigate(-1)}>Back</button>
        </div>
    )
}
export default Employee;