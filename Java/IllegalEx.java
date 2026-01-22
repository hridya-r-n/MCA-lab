import java.io.DataInputStream;
class IllegalArgumentException extends Exception{
public IllegalArgumentException(){
super();
}
}
class MultZero{
public void multiply(int a,int b) throws IllegalArgumentException{
try{
if(a==0||b==0)
throw new IllegalArgumentException();
else
System.out.println("Product= "+(a*b));
}catch(IllegalArgumentException ae){
System.out.println("Answer is zero");
}
}
}
class IllegalEx{
public static void main(String args[]){
try{
DataInputStream in=new DataInputStream(System.in);
MultZero m=new MultZero();
int a,b;
System.out.println("Enter 1rst number:");
a=Integer.parseInt(in.readLine());
System.out.println("Enter 2nd number:");
b=Integer.parseInt(in.readLine());
m.multiply(a,b);
}catch(Exception e){}
}
}