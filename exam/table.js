// 2. Create a Product table with pid, pname, qty and price 
const con=require('./connection1')
cr_db=`CREATE DATABASE IF NOT EXISTS Exam`
con.query(cr_db,(err)=>{
    if(err)throw err
    console.log('Database created successfully')
})

cr_tb=`CREATE TABLE IF NOT EXISTS Exam.product(pid INT PRIMARY KEY,pname VARCHAR(30),qty INT,price FLOAT)`
con.query(cr_tb,(err)=>{
    if(err)throw err
    console.log('Table product created successfully')
})

sel=`SELECT *FROM Exam.product`
con.query(sel,(err,result)=>{
    if(err)throw err
    console.table(result)
})
con.end()