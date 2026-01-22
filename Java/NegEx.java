import java.io.DataInputStream;
class NegativeException extends Exception{
public NegativeException(){super();
}
}
class ExNegative{
public void readData(int n)throws NegativeException{
try{
if(n<0)
throw new NegativeException();
}catch(NegativeException ne){
System.out.println("You entered a negative number!");
System.exit(0);
}
}
}
class NegEx{
public static void main(String args[]){
try{

DataInputStream in=new DataInputStream(System.in);
ExNegative e=new ExNegative();
System.out.println("Enter limit of the array:");
int n=Integer.parseInt(in.readLine());
int a[]=new int[n];
System.out.println("Enter the numbers:");
for(int i=0;i<n;i++)
{a[i]=Integer.parseInt(in.readLine());
e.readData(a[i]);
}
System.out.println("The Array is:");
for(int i=0;i<n;i++)
System.out.println(a[i]);
}catch(Exception e){}
}
}