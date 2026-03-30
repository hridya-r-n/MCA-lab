import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookTable {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String url="jdbc:oracle:thin:@//localhost:1521/xe";
        String user="system";
        String password="manager";
        String create="CREATE TABLE Book(Bookid INT PRIMARY KEY,title VARCHAR(50),author VARCHAR(50),price DECIMAL(10,2))";
        String ins="INSERT INTO Book(Bookid,title,author,price) VALUES(?,?,?,?)";
        String sel="SELECT * FROM Book";
        String selb="Select * From Book WHERE title=?";
        String sela="Select * From Book WHERE author=?";
        String upd="UPDATE Book SET price=? WHERE Bookid=?";
        String del="DELETE FROM Book WHERE Bookid=?";
        try(Connection con=DriverManager.getConnection(url,user,password);
        Statement Create=con.createStatement();
        PreparedStatement Insert=con.prepareStatement(ins);
        PreparedStatement SelectB=con.prepareStatement(selb);
        PreparedStatement SelectA=con.prepareStatement(sela);
        Statement Sel=con.createStatement();
        PreparedStatement Update=con.prepareStatement(upd);
        PreparedStatement Delete=con.prepareStatement(del);
        ){
            System.out.println("Connection established successfully");
            // try {
            //     Create.executeUpdate(create);
            //     System.out.println("Table 'Book' created successfully.");
            // } catch (SQLException e) {
            //     System.out.println("Table 'Book' already exists or error occurred:"+e.getMessage());
            // }
            int ch=0;
            do{
                System.out.println("MENU: 1 Insert  2.Display All  3.Display-Title  4.Display-Author  5.Update price  6.Delete  7.Exit\nEnter your choice:");
                ch=sc.nextInt();sc.nextLine();
                switch(ch){
                    case 1:
                        System.out.println("Enter book id:");
                        int id=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter book title:");
                        String t=sc.nextLine();
                        System.out.println("Enter author of the book:");
                        String a=sc.nextLine();
                        System.out.println("Enter book price:");
                        Double p=sc.nextDouble();
                        sc.nextLine();
                        Insert.setInt(1,id);
                        Insert.setString(2,t);
                        Insert.setString(3,a);
                        Insert.setDouble(4,p);
                        int rowsins=Insert.executeUpdate();
                        System.out.println(rowsins+" rows inserted");
                        break;
                    case 2:
                        ResultSet rs=Sel.executeQuery(sel);
                        System.out.println("Books:");
                        while(rs.next()){
                            int i=rs.getInt("Bookid");
                            String t1=rs.getString("title");
                            String a1=rs.getString("author");
                            Double p1=rs.getDouble("price");
                            System.out.println("ID:"+i+" Title:"+t1+" Author:"+a1+" Price:"+p1);
                        }
                        break;
                    case 3:
                        System.out.println("Enter a book title:");
                        String title=sc.nextLine();
                        SelectB.setString(1,title);
                        ResultSet rt=SelectB.executeQuery();
                        while(rt.next()){
                            int i=rt.getInt("Bookid");
                            String t1=rt.getString("title");
                            String a1=rt.getString("author");
                            Double p1=rt.getDouble("price");
                            System.out.println("ID:"+i+" Title:"+t1+" Author:"+a1+" Price:"+p1);
                        }
                        break;
                    case 4:
                        System.out.println("Enter an author:");
                        String author=sc.nextLine();
                        SelectA.setString(1,author);
                        ResultSet ra=SelectA.executeQuery();
                        while(ra.next()){
                            int i=ra.getInt("Bookid");
                            String t1=ra.getString("title");
                            String a1=ra.getString("author");
                            Double p1=ra.getDouble("price");
                            System.out.println("ID:"+i+" Title:"+t1+" Author:"+a1+" Price:"+p1);
                        }
                        break;
                    case 5:
                        System.out.println("Enter book id to update");
                        int i=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter updated price:");
                        Double p1=sc.nextDouble();
                        Update.setInt(2,i);
                        Update.setDouble(1,p1);
                        int rows=Update.executeUpdate();
                        System.out.println(rows+" rows updated");
                        break;
                    case 6:
                        System.out.println("Enter Book id to be deleted:");
                        int i1=sc.nextInt();
                        sc.nextLine();
                        Delete.setInt(1,i1);
                        int rowsdel=Delete.executeUpdate();
                        System.out.println(rowsdel+" rows deleted");
                        break;
                    case 7:break;
                    default:System.out.println("Invalid Choice");
                }
            }while(ch!=7);
        }catch(SQLException e){System.out.println("Database Error:"+e.getMessage());}
    }
}
