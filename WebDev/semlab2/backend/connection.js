import mysql from "mysql"
const con=mysql.createConnnection({
    host:'localhost',
    user:'root',
    password:'',
})
con.connect((err=>{
    if(err) throw err
    console.log("Connected")
}))
export default con;