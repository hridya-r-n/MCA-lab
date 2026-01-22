import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class SimpleCalc extends JApplet implements ActionListener{
JTextField t1,t2,t3;
JLabel l1,l2,l3;
double a,b;
String str;
JButton b1,b2,b3,b4;
public void init(){
Container contentPane=getContentPane();
contentPane.setLayout(new FlowLayout());
l1=new JLabel("First Number:");
l2=new JLabel("Second Number:");
l3=new JLabel("Result:");
b1=new JButton("Add");
b2=new JButton("Subtract");
b3=new JButton("Multiply");
b4=new JButton("Divide");
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
t1=new JTextField(20);
t2=new JTextField(20);
t3=new JTextField(20);
contentPane.add(l1);
contentPane.add(t1);
contentPane.add(l2);
contentPane.add(t2);
contentPane.add(l3);
contentPane.add(t3);
contentPane.add(b1);
contentPane.add(b2);
contentPane.add(b3);
contentPane.add(b4);
}
public void actionPerformed(ActionEvent ae){
str=ae.getActionCommand();
if(str.equals("Add")){
a=Double.parseDouble(t1.getText());
b=Double.parseDouble(t2.getText());
t3.setText(String.valueOf(a+b));
}
if(str.equals("Subtract")){
a=Double.parseDouble(t1.getText());
b=Double.parseDouble(t2.getText());
t3.setText(String.valueOf(a-b));
}
if(str.equals("Multiply")){
a=Double.parseDouble(t1.getText());
b=Double.parseDouble(t2.getText());
t3.setText(String.valueOf(a*b));
}
if(str.equals("Divide")){
a=Double.parseDouble(t1.getText());
b=Double.parseDouble(t2.getText());
t3.setText(String.valueOf(a/b));
}
}
}