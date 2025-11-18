const mysql=require('mysql');
const con=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:''
});

con.connect((err)=>{
    if(err){
        console.error('Error connecting',err.stack);
        return;
    }
    console.log('Connected to MySQL server');
});
module.exports=con;