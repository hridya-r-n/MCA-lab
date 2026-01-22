class GMThread extends Thread{
public void run(){
for(int i=0;i<10;i++){
try{
System.out.println("Good Morning");
sleep(1000);
}catch(Exception e){}
}
}
}
class HelloThread extends Thread{
public void run(){
for(int i=0;i<10;i++){
try{
System.out.println("Hello");
sleep(2000);
}catch(Exception e){}
}
}
}
class WelcomeThread extends Thread{
public void run(){
for(int i=0;i<10;i++){
try{
System.out.println("Welcome");
sleep(3000);
}catch(Exception e){}
}
}
}
class GMWelHel{
public static void main(String args[]){
GMThread g=new GMThread();
HelloThread h=new HelloThread();
WelcomeThread w=new WelcomeThread();
g.start();
h.start();
w.start();
}
}