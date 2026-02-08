import java.lang.Math;
import javax.swing.*;
import java.awt.*;
interface Printable{
    void display();
    void show();
}
interface Computable{
    double area();
    double perimeter();
    double volume();
}
interface Drawable{
    void draw();
}
class Rectangle implements Printable,Computable,Drawable{
    double length;
    double width;
    Rectangle(double l,double w){
        length=l;
        width=w;
    }
    public void display(){
        System.out.println("Rectangle-Length:"+length+" Width:"+width);
    }
    public void show(){}
    public double area(){
        return length*width;
    }
    public double perimeter(){
        return 2*(length+width);
    }
    public double volume(){ return 0;}
    public void draw(){
        JFrame jf=new JFrame("Drawing Rectangle");
        jf.setSize(600,600);
        jf.add(new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.drawRect(50,50,(int)length,(int)width);
            }
        });
        jf.setVisible(true);
    }
}
class Sphere implements Printable,Computable,Drawable{
    double radius;
    Sphere(double r){
        radius=r;
    }
    public void display(){}
    public void show(){
        System.out.println("Sphere-Radius:"+radius);
    }
    public double area(){
        return (4*Math.PI*radius*radius);
    }
    public double volume(){
        return ((4/3)*Math.PI*radius*radius*radius);
    }
    public double perimeter(){
        return (2*Math.PI*radius);
    }
    public void draw(){
        JFrame jf=new JFrame("Drawing Sphere");
        jf.setSize(400,400);
        jf.add(new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                int d=(int)radius*2;
                g.setColor(Color.RED);
                g.drawOval(50,50,d,d);
            }
        });
        jf.setVisible(true);
    }
}
class RectSphere{
    public static void main(String args[]){
        Rectangle r=new Rectangle(100,60);
        r.display();
        r.draw();
        System.out.println("Area:"+r.area());
        System.out.println("Perimeter:"+r.perimeter());
        Sphere s=new Sphere(100);
        s.show();
        s.draw();
        System.out.println("Area"+s.area());
        System.out.println("Perimeter:"+s.perimeter());
        System.out.println("Volume:"+s.volume());
    }
}