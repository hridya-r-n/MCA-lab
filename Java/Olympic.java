import java.awt.*;
import java.applet.*;
public class Olympic extends Applet{
public void paint(Graphics g){
g.setColor(Color.blue);
g.drawOval(10,10,30,30);
g.setColor(Color.yellow);
g.drawOval(30,25,30,30);
g.setColor(Color.black);
g.drawOval(50,10,30,30);
g.setColor(Color.green);
g.drawOval(70,25,30,30);
g.setColor(Color.red);
g.drawOval(90,10,30,30);
}
}