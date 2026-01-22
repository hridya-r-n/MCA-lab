import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class RevNum extends JApplet implements ActionListener{
JTextField jtf1,jtf2;
JButton b;
JLabel l1,l2;
int r,n;
int d=0;
public void init(){
Container contentPane=getContentPane();
contentPane.setLayout(new FlowLayout());
jtf1=new JTextField(20);
jtf2=new JTextField(20);
l1=new JLabel("Number:");
l2=new JLabel("Reversed Number:");
b=new JButton("Reverse");
contentPane.add(l1);
contentPane.add(jtf1);
contentPane.add(b);
contentPane.add(l2);
contentPane.add(jtf2);
b.addActionListener(this);
}
public void actionPerformed(ActionEvent ae){
n=Integer.parseInt(jtf1.getText());
while(n>0){
d=n%10;
r=r*10+d;
n=n/10;
}
jtf2.setText(String.valueOf(r));
}
}
