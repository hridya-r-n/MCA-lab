import Pack.Prime;
import java.io.DataInputStream;
class PrimeNum{
public static void main(String args[]){
try{
DataInputStream in=new DataInputStream(System.in);
Prime p=new Prime();
int n;
int flag=0;
System.out.println("Enter number:");
n=Integer.parseInt(in.readLine());
flag=p.checkPrime(n);
if(flag!=1)
System.out.println(n+" is prime");
else
System.out.println(n+" is not a prime");
}catch(Exception e){}
}
}