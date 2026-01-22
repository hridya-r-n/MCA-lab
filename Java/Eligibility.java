import java.io.DataInputStream;
class Student{
int rollno;
String name;
double attendance;
void readValue(int r,String n,double at){
rollno=r;
name=n;
attendance=at;
}
void getValue(){
System.out.println("Roll No.:"+rollno);
System.out.println("Name:"+name);
System.out.println("Attendance:"+attendance);
}
}
interface Department{
final String course="S5 BCA";
abstract void getCourse();
}
class Exam extends Student implements Department{
int atmarks;
public void getCourse(){
System.out.println("Course:"+course);
}
void calcAttendance(){
if(attendance>=90)
atmarks=5;
else if(attendance>=85)
atmarks=4;
else if(attendance>=80)
atmarks=3;
else if(attendance>=75)
atmarks=2;
else
atmarks=1;
}
void boolEligible(){
if(atmarks>=2)
System.out.println("Eligible for examination");
else
System.out.println("Not eligible for examination");
}
}
class Eligibility{
public static void main(String args[]){
try{
DataInputStream in=new DataInputStream(System.in);
Exam e=new Exam();
System.out.println("Enter Roll no.:");
int r=Integer.parseInt(in.readLine());
System.out.println("Enter your name:");
String n=in.readLine();
System.out.println("Enter Attendance:");
double at=Double.parseDouble(in.readLine());
e.readValue(r,n,at);
e.calcAttendance();
System.out.println("------------------Student Eligibility Details------------------");
e.getValue();
e.getCourse();
e.boolEligible();
}catch(Exception e){}
}
}