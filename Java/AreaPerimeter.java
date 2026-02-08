import java.util.Scanner;
import java.lang.Math;
interface Compute{
    public double area();
    public double perimeter();
}
class Circle implements Compute{
    double radius;
    Circle(double r){
        radius=r;
    }
    public double area(){
        return (Math.PI*radius*radius);
    }
    public double perimeter(){
        return (2*Math.PI*radius);
    }
}
class Rectangle implements Compute{
    double length;
    double width;
    Rectangle(double l,double w){
        length=l;
        width=w;
    }
    public double area(){
        return length*width;
    }
    public double perimeter(){
        return 2*(length+width);
    }
}
class AreaPerimeter{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int ch;
        do{
            System.out.println("Menu\n1.Circle(Area,Perimeter)\n2.Rectangle(Area,Perimeter)\n3.Exit");
            System.out.println("Enter your choice:");
            ch=sc.nextInt();
            switch(ch){
                case 1:System.out.println("Enter radius of the circle:");
                    double r=sc.nextDouble();
                    Circle c=new Circle(r);
                    System.out.println("Area:"+c.area());
                    System.out.println("Perimeter:"+c.perimeter());
                    break;
                case 2:System.out.println("Enter length of the rectangle:");
                    double l=sc.nextDouble();
                    System.out.println("Enter width of the rectangle:");
                    double w=sc.nextDouble();
                    Rectangle rect=new Rectangle(l,w);
                    System.out.println("Area"+rect.area());
                    System.out.println("Perimeter:"+rect.perimeter());
                    break;
                case 3:return;
                default:System.out.println("Invalid choice!\n");
            }
        }while(ch!=3);
    }
}