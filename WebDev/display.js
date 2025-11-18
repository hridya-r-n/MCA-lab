const con = require("./connection");
sel=`SELECT * FROM CollegeDB.students WHERE course='Computer Science'`;
con.query(sel,(err,results)=>{
    if(err)throw err;
    console.table(results);
    // con.end();
})

// Display all students enrolled in "Computer Science".
// Update the course of a student with id = 2 to "Data Science". Delete a student record with id = 3.
update=`UPDATE CollegeDB.students SET course='Data Science' WHERE id=2`
con.query(update,(err)=>{
    if(err)throw err;
    console.log('Table updated successfully');
})
sel=`SELECT * FROM CollegeDB.students`;
con.query(sel,(err,results)=>{
    if(err)throw err;
    console.table(results);
    // con.end();
})
del=`DELETE FROM CollegeDB.students WHERE id=3`
con.query(del,(err)=>{
    if(err)throw err;
    console.log('Deleted successfully');
})
sel=`SELECT * FROM CollegeDB.students`;
con.query(sel,(err,results)=>{
    if(err)throw err;
    console.table(results);
    con.end();
})
