import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Largest extends JApplet implements ActionListener{
JTextField jtf1,jtf2,jtf3;
JButton jb;
JLabel jl1,jl2,jl3;
int a,b;
public void init(){
Container contentPane=getContentPane();
contentPane.setLayout(new FlowLayout());
jl1=new JLabel("First Number:");
jl2=new JLabel("Second Number:");
jl3=new JLabel("Largest:");
jtf1=new JTextField(20);
jtf2=new JTextField(20);
jtf3=new JTextField(20);
jb=new JButton("Compare");
contentPane.add(jl1);
contentPane.add(jtf1);
contentPane.add(jl2);
contentPane.add(jtf2);
contentPane.add(jb);
contentPane.add(jl3);
contentPane.add(jtf3);
jb.addActionListener(this);
}
public void actionPerformed(ActionEvent ae){
a=Integer.parseInt(jtf1.getText());
b=Integer.parseInt(jtf2.getText());
if(a>b)
jtf3.setText(String.valueOf(a));
else
jtf3.setText(String.valueOf(b));
}
}
