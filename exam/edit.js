const con=require('./connection1')
// ins=`INSERT INTO Exam.product(pid,pname,qty,price) VALUES(4,'sling bag',3,1500)`
// con.query(ins,(err)=>{
//     if (err)throw err
//     console.log('Values inserted')
// })

sel=`SELECT *FROM Exam.product`
con.query(sel,(err,results)=>{
    if(err)throw err
    console.table(results)
})

upd=`UPDATE Exam.product SET qty=5 WHERE pname='shoe'`
con.query(upd,(err)=>{
    if(err)throw err
})

sel=`SELECT *FROM Exam.product WHERE price>600`
con.query(sel,(err,results)=>{
    if(err)throw err
    console.table(results)
})
con.end()