import java.awt.*;
import java.applet.*;
public class Traffic extends Applet{
public void init(){
setBackground(Color.cyan);
}
public void paint(Graphics g){
try{
g.setColor(Color.black);
g.fillRect(10,20,70,200);
g.setColor(Color.red);
g.fillOval(20,30,50,50);
g.setColor(Color.white);
g.fillOval(20,90,50,50);
g.fillOval(20,150,50,50);
Thread.sleep(2000);
g.setColor(Color.white);
g.fillOval(20,30,50,50);
g.fillOval(20,1500,50,50);
g.setColor(Color.yellow);
g.fillOval(20,90,50,50);
Thread.sleep(2000);
g.setColor(Color.white);
g.fillOval(20,30,50,50);
g.fillOval(20,90,50,50);
g.setColor(Color.green);
g.fillOval(20,150,50,50);
Thread.sleep(2000);
repaint();
}catch(Exception e){}
}
}