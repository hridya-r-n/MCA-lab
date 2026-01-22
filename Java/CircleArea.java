import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class CircleArea extends JApplet implements ActionListener{
JTextField jtf1,jtf2;
JButton jb;
JLabel jl1,jl2;
double r,pi=3.14,a;
public void init(){
Container contentPane=getContentPane();
contentPane.setLayout(new FlowLayout());
jl1=new JLabel("Radius:");
jl2=new JLabel("Area:");
jtf1=new JTextField(20);
jtf2=new JTextField(20);
jb=new JButton("Area");
contentPane.add(jl1);
contentPane.add(jtf1);
contentPane.add(jl2);
contentPane.add(jtf2);
contentPane.add(jb);
jb.addActionListener(this);
}
public void actionPerformed(ActionEvent ae){
r=Double.parseDouble(jtf1.getText());
a=pi*r*r;
jtf2.setText(String.valueOf(a));
}
}