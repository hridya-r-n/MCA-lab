class Zoo{
    String name;
    String[] region;
    Zoo(String n,String[] r){
        name=n;
        region=r;
    }
    class Enclosure{
        String name;
        String type;
        String[] animals;
        public Enclosure(String n,String t,String[] a){
            name=n;
            type=t;
            animals=a;
        }
        void display(){
            System.out.println();
            System.out.println("Enclosure:"+this.name);
            System.out.println("Type:"+type);
            System.out.println("Animals:");
            for(int i=0;i<animals.length;i++)
                System.out.println(animals[i]);
        }
    }
    static class Contact{
        static String address;
        static String phone;
        Contact(String a,String ph){
            address=a;
            phone=ph;
        }
        void display(){
            System.out.println("Address:"+address);
            System.out.println("Phone:"+phone);
        }
    }
    void displayZoo(){
        System.out.println("Name:"+name);
        System.out.println("Regions:");
        for(String r:region){
            System.out.println(r);
        }
        System.out.println();
        // Contact.display();
    }
    
}
class ZooDemo{
    public static void main(String args[]){
        String[] reg={"Area-1","Area-2","Area-3"};
        Zoo z=new Zoo("National Zoo",reg);
        Zoo.Contact c=new Zoo.Contact("2nd Street,Park lane","9827263738");
        String[] a1={"Lion","Deer","Zebra"};
        Zoo.Enclosure e1=z.new Enclosure("Grassland","Outdoor",a1);
        String[] a2={"Turtle","Fish","Crocodile"};
        Zoo.Enclosure e2=z.new Enclosure("Aquatic","Indoor",a2);
        String[] a3={"Parrot","Vulture","Eagle"};
        Zoo.Enclosure e3=z.new Enclosure("FlyZone","Indoor",a3);
        z.displayZoo();
        c.display();
        e1.display();
        e2.display();
        e3.display();
    }
}