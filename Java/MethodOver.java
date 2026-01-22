import java.io.DataInputStream;
class AreaDemo{
float area(float l){
return(l*l);
}
float area(float l,float b){
return(l*b);
}
}
class MethodOver{
public static void main(String args[]){
try{
DataInputStream in=new DataInputStream(System.in);
float a;
AreaDemo ar=new AreaDemo();
int ch;
do{
System.out.println("Menu");
System.out.println("1.Square");
System.out.println("2.Rectangle");
System.out.println("3.Exit");
System.out.println("Enter Your Choice:");
ch=Integer.parseInt(in.readLine());
switch(ch){
case 1:
System.out.println("Enter side of square:");
float l=Float.parseFloat(in.readLine());
a=ar.area(l);
System.out.println("Area of the square: "+a);
break;
case 2:
System.out.println("Enter length:");
float l1=Float.parseFloat(in.readLine());
System.out.println("Enter breadth:");
float b=Float.parseFloat(in.readLine());
a=ar.area(l1,b);
System.out.println("Area of the Rectangle: "+a);
break;
case 3:break;
default:System.out.println("Invalid Choice");
}
}while(ch!=3);
}catch(Exception e){}
}
}
