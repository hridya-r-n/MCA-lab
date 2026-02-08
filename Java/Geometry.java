import java.util.Scanner;
import static java.lang.Math.PI;
class Geometry{
    int area(int s){
        return s*s;
    }
    int area(int l,int b){
        return l*b;
    }
    double area(double r){
        return (Math.PI*r*r);
    }
    public static void main(String args[]){
        Geometry g=new Geometry();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter side of the square:");
        int s=sc.nextInt();
        System.out.println("Area of the square:"+g.area(s));
        System.out.println("Enter length of the rectangle:");
        int l=sc.nextInt();
        System.out.println("Enter breadth of the rectangle:");
        int b=sc.nextInt();
        System.out.println("Area of the rectangle:"+g.area(l,b));
        System.out.println("Enter radius of the circle:");
        double r=sc.nextDouble();
        System.out.println("Area of the circle:"+g.area(r));
    }
}