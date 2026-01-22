import java.awt.*;
import java.applet.*;
public class Star extends Applet{
public void paint(Graphics g){
int x1[]={40,100,70};
int y1[]={40,40,100};
int x2[]={70,40,100};
int y2[]={20,80,80};
g.drawPolygon(x1,y1,3);
g.drawPolygon(x2,y2,3);
}
}