import java.io.DataInputStream;
import Pack.Prime;
class PrimeNumLimit{
public static void main(String args[]){
try{
DataInputStream in=new DataInputStream(System.in);
Prime p=new Prime();
int n1,n2;
int flag=0;
System.out.println("Enter starting Limit:");
n1=Integer.parseInt(in.readLine());
System.out.println("Enter endding Limit:");
n2=Integer.parseInt(in.readLine());
System.out.println("Prime numbers between"+n1+" and "+n2);
for(int i=n1;i<n2;i++){
flag=p.checkPrime(i);
if(flag!=1){
System.out.println(i);
}
}
}catch(Exception e){}
}
}