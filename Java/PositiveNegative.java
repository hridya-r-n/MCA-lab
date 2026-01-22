import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class PositiveNegative extends JApplet implements ActionListener{
JTextField jtf1,jtf2;
JButton jb;
JLabel jl1,jl2;
int n;
String str;
public void init(){
Container contentPane=getContentPane();
contentPane.setLayout(new FlowLayout());
jl1=new JLabel("Number:");
jl2=new JLabel("Result:");
jtf1=new JTextField(20);
jtf2=new JTextField(20);
jb=new JButton("Check");
contentPane.add(jl1);
contentPane.add(jtf1);
contentPane.add(jb);
contentPane.add(jl2);
contentPane.add(jtf2);
jb.addActionListener(this);
}
public void actionPerformed(ActionEvent ae){
n=Integer.parseInt(jtf1.getText());
if(n<0){
jtf2.setText("Negative number");
}
else{
jtf2.setText("Positive number");
}
//jtf2.setText(str);
}
}
