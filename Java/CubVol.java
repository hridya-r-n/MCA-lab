class Rectangle{
    double length;
    double width;
    Rectangle(double l,double w){
        length=l;
        width=w;
    }
}
interface Measurable{
    double volume();
}
interface Printable{
    void display();
}
class Cuboid extends Rectangle implements Measurable,Printable{
    double height;
    Cuboid(double l,double w,double h){
        super(l,w);
        height=h;
    }
    public void display(){
        System.out.println("Cuboid-Length:"+length+" Width:"+width+" Heigth:"+height);
    }
    public double volume(){
        return length*width*height;
    }
}
class CubVol{
    public static void main(String args[]){
        Cuboid c=new Cuboid(4,6,3);
        c.display();
        System.out.println("Volume:"+c.volume());
    }
}