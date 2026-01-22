const express=require("express");
const cors=require("cors");
const mysql=require("mysql");
const app=express()
app.use(cors())
app.use(express.json())
const con =mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'',
    database:'exam'
});
con.connect(err=>{
    if(err)throw err
    console.log('Connected');
});
app.get('/students',(req,res)=>{
    con.query("SELECT *FROM students",(err,results)=>{
        if(err) return res.send(err)
        res.json(results)
    });
});
app.post("/students",(req,res)=>{
    
    const { name,email,age}=req.body;
    con.query("INSERT INTO students(name,email,age) VALUES(?,?,?)",[name,email,age],(err)=>{
        if(err)return res.send(err)
        res.json({success:true})
    });
});
app.put("/students/:id",(req,res)=>{
    const {name,email,age}=req.body;
    const id=req.params.id;
    con.query("UPDATE students SET name=?,email=?,age=? WHERE id=?",[name,email,age,id],(err)=>{
        if(err)return res.send(err)
            res.json({success:true})
    });
});
app.delete("/students/:id",(req,res)=>{
    const id=req.params.id;
    con.query("DELETE FROM students WHERE id=?",[id],(err)=>{
        if(err)return res.send(err);
        res.json({success:true});
    });
});
app.listen(5000,()=>{
    console.log("Server running on port 5000");
});