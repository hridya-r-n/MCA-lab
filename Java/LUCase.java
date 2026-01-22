class Lower extends Thread{
public void run(){
char c='a';
try{
for(int i=0;i<5;i++){
System.out.println(c);
c++;
sleep(2000);
}
}catch(Exception e){}
}
}
class Upper extends Thread{
public void run(){
char c='A';
try{
for(int i=0;i<5;i++){
System.out.println(c);
c++;
sleep(2000);
}
}catch(Exception e){}
}
}
class LUCase{
public static void main(String args[]){
Lower l=new Lower();
Upper u=new Upper();
u.start();
l.start();
}
}