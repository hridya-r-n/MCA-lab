import java.io.DataInputStream;
interface Department{
final String deptName="BCA";
final String deptHead="Anu";
void showData();
}
class Hostel{
String hosname,hoslocation;
int noofrooms;
void readData(String hname,String hloc,int no){
hosname=hname;
hoslocation=hloc;
noofrooms=no;
}
void printData(){
System.out.println("Hostel Name:"+hosname);
System.out.println("Location:"+hoslocation);
System.out.println("No. of rooms:"+noofrooms);
}
}
class Student extends Hostel implements Department{
int regno;
String sname,elsub;
double avgmark;
public void showData(){
System.out.println("Departent Name:"+deptName);
System.out.println("Department Head:"+deptHead);
}
void getData(int r,String n,String es,double am){
regno=r;
sname=n;
elsub=es;
avgmark=am;
}
void displayData(){
System.out.println("Register no.:"+regno);
System.out.println("Name:"+sname);
System.out.println("Elective Subject:"+elsub);
System.out.println("Average Mark:"+avgmark);
}
}
class StudDetails{
public static void main(String args[]){
try{
DataInputStream in=new DataInputStream(System.in);
Student s=new Student();
System.out.println("Enter Student details");
System.out.println("Enter Register no.:");
int r=Integer.parseInt(in.readLine());
System.out.println("Enter Name:");
String n=in.readLine();
System.out.println("Enter Average Marks:");
double m=Double.parseDouble(in.readLine());
System.out.println("Enter Elective Suject:");
String e=in.readLine();
s.getData(r,n,e,m);
System.out.println("Enter Hostel Details");
System.out.println("Enter Hostel Name:");
String hn=in.readLine();
System.out.println("Enter Hostel Location:");
String hl=in.readLine();
System.out.println("Enter No of Rooms:");
int nr=Integer.parseInt(in.readLine());
s.readData(hn,hl,nr);
System.out.println("Student Details");
s.displayData();
System.out.println("Department Details");
s.showData();
System.out.println("Hostel Details");
s.printData();
}catch(Exception e){}
}
}