import java.applet.*;
import java.awt.*;
public class House extends Applet{
public void paint(Graphics g){
int x[]={10,60,110};
int y[]={80,30,80};
g.drawRect(10,80,100,100);
g.drawPolygon(x,y,3);
g.setColor(Color.red);
g.fillRect(45,100,35,80);
}
}