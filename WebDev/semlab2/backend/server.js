const express=require("express")
const cors=require("cors")
const mysql=require("mysql")

const app=express()
app.use(express.json())
app.use(cors())

const con=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'',
    database:'company'
})
con.connect((err)=>{
    if(err) throw err;
    console.log("connected")
})
// con.query("CREATE DATABASE IF NOT EXISTS company",(err)=>{
//     if(err)throw err
//     console.log("Database created or exists")
// })
con.query("CREATE TABLE IF NOT EXISTS employee(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(50),email VARCHAR(100),dept VARCHAR(60))",(err)=>{
    if(err)throw err
    console.log("Table created or exists")
})

app.get("/employee",(req,res)=>{
    con.query("SELECT *FROM employee",(err,results)=>{
        if(err) return res.send(err)
        res.json(results)
    })
})
app.post("/employee",(req,res)=>{
    const {name,email,dept}=req.body;
    console.log(req.body)
    con.query("INSERT INTO employee(name,email,dept) VALUES(?,?,?)",[name,email,dept],(err)=>{
        if(err) return res.send(err)
        res.json({success:true})
    })
})
app.put("/employee/:id",(req,res)=>{
    const {name,email,dept}=req.body;
    const id=req.params.id
    con.query("UPDATE employee SET name=?,email=?,dept=? WHERE id=?",[name,email,dept,id],(err)=>{
        if(err)return res.send(err)
        res.json({successs:true})
    })
})
app.delete("/employee/:id",(req,res)=>{
    const id=req.params.id
    con.query("DELETE FROM employee WHERE id=?",[id],(err)=>{
        if(err) return res.send(err)
        res.json({sucess:true})
    })
})
app.listen(5000,()=>{
    console.log("Server running on port 5000")
})