//Write a java application program to print odd and even numbers less than 100 using multithreading
class Even extends Thread{
public void run(){
for(int i=0;i<100;i++)
if(i%2==0)
System.out.println(i);
}
}
class Odd extends Thread{
public void run(){
for(int i=0;i<100;i++)
if(i%2!=0)
System.out.println(i);
}
}
class OddEven{
public static void main(String args[]){
try{
Even e=new Even();
Odd o=new Odd();
System.out.println("Even");
e.start();

e.sleep(10);
System.out.println("Odd");
o.start();

}catch(Exception e){}
}
}