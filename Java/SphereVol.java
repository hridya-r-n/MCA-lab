import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SphereVol extends JApplet implements ActionListener{
JTextField jtf1,jtf2;
JButton b;
JLabel l1,l2;
public void init(){
Container contentPane=getContentPane();
contentPane.setLayout(new FlowLayout());
jtf1=new JTextField(20);
jtf2=new JTextField(20);
l1=new JLabel("Radius:");
l2=new JLabel("Volume:");
b=new JButton("Result");
contentPane.add(l1);
contentPane.add(jtf1);
contentPane.add(b);
contentPane.add(l2);
contentPane.add(jtf2);
b.addActionListener(this);
}
public void actionPerformed(ActionEvent ae){
double r=Double.parseDouble(jtf1.getText());
jtf2.setText(String.valueOf((4*3.14*r*r*r)/3));
}
}