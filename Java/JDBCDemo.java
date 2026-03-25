import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class JDBCDemo {
    public static void main(String args[]){
        String url="jdbc:oracle:thin:@//localhost:1521/xe";
        String user="system";
        String password="manager";
        String create="CREATE TABLE Employee(id INT PRIMARY KEY,name VARCHAR(30),salary DECIMAL(10,2))";
        String ins="INSERT INTO Employee(id,name,salary) VALUES(?,?,?)";
        String sel="SELECT * FROM Employee";
        String selq="Select name,salary From Employee WHERE salary=?";
        String upd="UPDATE Employee SET salary=? WHERE name=?";
        String del="DELETE FROM Employee WHERE id=?";
        try(Connection con=DriverManager.getConnection(url,user,password);
        Statement Create=con.createStatement();
        PreparedStatement Insert=con.prepareStatement(ins);
        PreparedStatement SelectQ=con.prepareStatement(selq);
        Statement Sel=con.createStatement();
        PreparedStatement Update=con.prepareStatement(upd);
        PreparedStatement Delete=con.prepareStatement(del);
        ){
            System.out.println("Connection established successfully");
            // try {
            //     Create.executeUpdate(create);
            //     System.out.println("Table 'employees' created successfully.");
            // } catch (SQLException e) {
            //     System.out.println("Table 'employees' already exists or error occurred:"+e.getMessage());
            // }
            // Insert.setInt(1,1);
            // Insert.setString(2,"John");
            // Insert.setDouble(3,50000);
            // int rowsins=Insert.executeUpdate();
            // System.out.println(rowsins+" rows inserted");
            // Insert.setInt(1,2);
            // Insert.setString(2,"Tom");
            // Insert.setDouble(3,45000);
            // rowsins=Insert.executeUpdate();
            // System.out.println(rowsins+" rows inserted");
            // Insert.setInt(1,3);
            // Insert.setString(2,"Anu");
            // Insert.setDouble(3,55000);
            // rowsins=Insert.executeUpdate();
            // System.out.println(rowsins+" rows inserted");

            ResultSet rs=Sel.executeQuery(sel);
            System.out.println("Employees:");
            while(rs.next()){
                int id=rs.getInt("id");
                String n=rs.getString("name");
                Double sal=rs.getDouble("salary");
                System.out.println("ID:"+id+" Name:"+n+" Salary:"+sal);
            }

            Update.setDouble(1,65000);
            Update.setString(2,"Tom");
            int rowsup=Update.executeUpdate();
            System.out.println(rowsup+" rows updated");

            SelectQ.setDouble(1,65000);
            ResultSet rsel=SelectQ.executeQuery();
            while(rsel.next()){
                String n=rsel.getString("name");
                Double sal=rsel.getDouble("salary");
                System.out.println("Name:"+n+" Salary:"+sal);
            }


        }catch(SQLException e){
            System.out.println("DataBase error:"+e.getMessage());
        }
    }
}
