const con=require('./connection')
con.query('CREATE DATABASE IF NOT EXISTS CollegeDB',
        (err,result)=>{
            if(err)throw err;
            console.log('Database CollegeDB created successfully or already exists');
            con.end();
});
cr_tb=`CREATE TABLE IF NOT EXISTS CollegeDB.students(id INT PRIMARY KEY,name VARCHAR(50),age INT,course VARCHAR(30))`;
con.query(cr_tb,(err)=>{
    if(err)throw err;
    console.log('Table students created successfully');
})
