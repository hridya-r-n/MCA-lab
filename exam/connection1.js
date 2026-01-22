const mysql=require('mysql')
const con=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:''
});

con.connect((err)=>{
    if(err){
        console.errror('Error Connecting ',err.stack)
    }
    console.log('Connected successfully')
})
module.exports=con