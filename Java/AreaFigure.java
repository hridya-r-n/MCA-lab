import java.io.DataInputStream;
abstract class Figure{
double length,breadth,height;
abstract double area();
}
class Rectangle extends Figure{
void getData(double l,double b){
length=l;
breadth=b;
}
double area(){
return(length*breadth);
}
}
class Triangle extends Figure{
void getData(double l,double h){
length=l;
height=h;
}
double area(){
return(0.5*length*height);
}
}
class AreaFigure{
public static void main(String args[]){
try{
DataInputStream in=new DataInputStream(System.in);
int ch;
double a,b;
do{
System.out.println("Menu");
System.out.println("1.Rectangle");
System.out.println("2.Triangle");
System.out.println("3.Exit");
System.out.println("Enter Your  Choice:");
ch=Integer.parseInt(in.readLine());
switch(ch){
case 1:Rectangle r=new Rectangle();
System.out.println("Enter length of rectangle:");
a=Double.parseDouble(in.readLine());
System.out.println("Enter breadth of rectangle:");
b=Double.parseDouble(in.readLine());
r.getData(a,b);
System.out.println("Area:"+r.area());
break;
case 2:Triangle t=new Triangle();
System.out.println("Enter base length of triangle:");
a=Double.parseDouble(in.readLine());
System.out.println("Enter height of triangle:");
b=Double.parseDouble(in.readLine());
t.getData(a,b);
System.out.println("Area:"+t.area());
break;
case 3:break;
default:System.out.println("Invalid Choice!");
}
}while(ch!=3);
}catch(Exception e){}
}
}