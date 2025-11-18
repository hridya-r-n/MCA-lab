const con = require("./connection");
ins=`INSERT INTO CollegeDB.STUDENTS(id,name,age,course) VALUES(6,'Harry',20,'Physics')`
con.query(ins,(err)=>{
    if(err)throw err;
    console.log('Data inserted created successfully');
    con.end();
});
sel=`SELECT * FROM CollegeDB.students`;
con.query(sel,(err,results)=>{
    if(err)throw err;
    console.table(results);
    con.end();
})