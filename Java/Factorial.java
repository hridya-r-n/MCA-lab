import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Factorial extends JApplet implements ActionListener{
JTextField jtf1,jtf2;
JButton jb;
JLabel jl1,jl2;
int n,f=1;
public void init(){
Container contentPane=getContentPane();
contentPane.setLayout(new FlowLayout());
jl1=new JLabel("Number:");
jl2=new JLabel("Factorial:");
jtf1=new JTextField(20);
jtf2=new JTextField(20);
jb=new JButton("Factorial");
contentPane.add(jl1);
contentPane.add(jtf1);
contentPane.add(jb);
contentPane.add(jl2);
contentPane.add(jtf2);
jb.addActionListener(this);
}
public void actionPerformed(ActionEvent ae){
n=Integer.parseInt(jtf1.getText());
if(n>1)
for(int i=n;i>=1;i--)
f=f*i;

jtf2.setText(String.valueOf(f));
}
}
