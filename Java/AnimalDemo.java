import java.util.Scanner;
abstract class Animal{
    private String species;
    protected int age;
    protected float weight;
    String color;
    Animal(String sp,int a,float w,String c){
        species=sp;
        age=a;
        weight=w;
        color=c;
    }
    Animal(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter species:");
        species=sc.next();
        System.out.println("Enter age:");
        age=sc.nextInt();
        System.out.println("Enter weight:");
        weight=sc.nextFloat();
        System.out.println("Enter color:");
        color=sc.next();
    }
    Animal(Animal a){
        this.species=a.species;
        this.age=a.age;
        this.weight=a.weight;
        this.color=a.color;
    }
    abstract void display();
}
class Dog extends Animal{
    String breed;
    String name;
    String owner;
    Dog(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter breed:");
        breed=sc.next();
        System.out.println("Enter name:");
        name=sc.next();
        System.out.println("Enter owner's name:");
        owner=sc.next();
    }
    Dog(String sp,int a,float w,String c,String br,String n,String o){
        super(sp,a,w,c);
        breed=br;
        name=n;
        owner=o;
    }
    void display(){
        System.out.println("Name:"+name);
        // System.out.println("Species:Dog");
        System.out.println("Age:"+age);
        System.out.println("Weight:"+weight);
        System.out.println("Color:"+color);
        System.out.println("Breed:"+breed);
        System.out.println("Owner:"+owner);
    }
}
class Cat extends Animal{
    String name;
    String eyeColor;
    int tailLength;
    Cat(String sp,int a,float w,String c,String n,String eye,int tail){
        super(sp,a,w,c);
        name=n;
        eyeColor=eye;
        tailLength=tail;
    }
    Cat(Cat c){
        this.name=c.name;
        this.eyeColor=c.eyeColor;
        this.tailLength=c.tailLength;
    }
    void display(){
        System.out.println("Name:"+name);
        System.out.println("Age:"+age);
        System.out.println("Weight:"+weight);
        System.out.println("Color:"+color);
        System.out.println("Eye color:"+eyeColor);
        System.out.println("Tail Length:"+tailLength);
    }
}
class AnimalDemo{
    public static void main(String args[]){
        Dog d1=new Dog();
        Dog d2=new Dog("Dog",4,14,"Black","Doberman","Tim","John");
        Animal a=new Cat("Feline",5,13,"Ginger","Leo","Black",17);
        Cat c = new Cat("Feline", 2, 4, "White", "Kitty", "Blue", 10);
        Cat c1=c;
        System.out.println("Dogs");
        d1.display();
        System.out.println();
        d2.display();
        System.out.println("Cat");
        a.display();
        System.out.println("Clone of the cat:");
        c1.display();
    }
}