import java.io.DataInputStream;
class MarksOutOfBoundsException extends Exception{
public MarksOutOfBoundsException(){super();}
}
class Result{
static void checkMarks(double m)throws MarksOutOfBoundsException{
try{
if(m<0||m>100)
throw new MarksOutOfBoundsException();
}catch(MarksOutOfBoundsException me){
System.out.println("Mark entered is invalid!");
System.exit(0);
}
}
public static void main(String args[]){
try{
String n,d;
int regno;
double m[]=new double[5];
double total=0;
DataInputStream in=new DataInputStream(System.in);
System.out.println("Enter Register No:");
regno=Integer.parseInt(in.readLine());
System.out.println("Enter Name:");
n=in.readLine();
System.out.println("Enter date(YY/MM/DD):");
d=in.readLine();
for(int i=1;i<=3;i++){
System.out.println("Enter the mark of subject "+i);
m[i]=Double.parseDouble(in.readLine());
checkMarks(m[i]);
total=total+m[i];
}
System.out.println("Result");
System.out.println("Register No.: "+regno);
System.out.println("Name: "+n);
System.out.println("Date : "+d);
for(int i=1;i<=3;i++){
System.out.println("Mark "+i+" : "+m[i]);
}
System.out.println("Total:"+total);
}catch(Exception e){}
}
}