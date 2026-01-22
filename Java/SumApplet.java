import java.awt.*;
import java.applet.*;
public class SumApplet extends Applet{
TextField tf1,tf2,tf3;
public void init(){
tf1=new TextField(20);
tf2=new TextField(20);
tf3=new TextField(20);
add(tf1);
add(tf2);
add(tf3);
}
public void paint(Graphics g){
int a=Integer.parseInt(tf1.getText());
int b=Integer.parseInt(tf2.getText());
tf3.setText(String.valueOf(a+b));
}
}