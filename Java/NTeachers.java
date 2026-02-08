import java.util.Scanner;
class Person{
    String name;
    String gender;
    String address;
    int age;
    Person(String n,String g,String ad,int a){
        name=n;
        gender=g;
        address=ad;
        age=a;
    }
    void display(){
        System.out.println("Name:"+name);
        System.out.println("Gender:"+gender);
        System.out.println("Address:"+address);
        System.out.println("Age:"+age);
    }
}
class Employee extends Person{
    int empid;
    String company;
    String qual;
    double salary;
    Employee(String n,String g,String ad,int a,int e,String c,String q,double s){
        super(n,g,ad,a);
        empid=e;
        company=c;
        qual=q;
        salary=s;
    }
    void display(){
        super.display();
        System.out.println("Employee ID:"+empid);
        System.out.println("Company:"+company);
        System.out.println("Qualification:"+qual);
        System.out.println("Salary:"+salary);
    }
}
class Teacher extends Employee{
    int tid;
    String subject;
    String dept;
    Teacher(String n,String g,String ad,int a,int e,String c,String q,double s,int t,String sub,String d){
        super(n,g,ad,a,e,c,q,s);
        tid=t;
        subject=sub;
        dept=d;
    }
    void display(){
        super.display();
        System.out.println("Teacher ID:"+tid);
        System.out.println("Subject:"+subject);
        System.out.println("Department"+dept);
    }
}
class NTeachers{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of teachers:");
        int num=sc.nextInt();
        sc.nextLine();
        Teacher[] t=new Teacher[num];
        for (int i=0;i<num;i++){
            System.out.println("Enter details of teacher-"+(i+1)+":");
            System.out.println("Enter name of the teacher:");
            String n=sc.nextLine();
            System.out.println("Enter gender");
            String g=sc.nextLine();
            System.out.println("Enter address:");
            String ad=sc.nextLine();
            System.out.println("Enter age:");
            int a=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Employee id of the teacher:");
            int e=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter company name:");
            String c=sc.nextLine();
            System.out.println("Enter qualification of the teacher:");
            String q=sc.nextLine();
            System.out.println("Enter the salary of the teacher");
            double s=sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter Teacher Id of the teacher:");
            int tid=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter subject taken:");
            String sub=sc.nextLine();
            System.out.println("Enter department name:");
            String d=sc.nextLine();
            
            t[i]=new Teacher(n,g,ad,a,e,c,q,s,tid,sub,d);
        }
        for(int i=0;i<num;i++){
            System.out.println();
            System.out.println("Teacher-"+(i+1)+":");
            t[i].display();
        }
    }
}