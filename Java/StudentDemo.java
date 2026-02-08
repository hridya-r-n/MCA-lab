import java.util.Scanner;
class Student{
    int rollno;
    String name;
    int marks;
    public static int count=0;
    static Scanner sc = new Scanner(System.in);
    public Student(){
        count++;
        // Scanner sc=new Scanner(System.in);
        System.out.println("Enter rollno of student "+count+" :");
        rollno=sc.nextInt();
        System.out.println("Enter name of student "+count+" :");
        name=sc.next();
        System.out.println("Enter mark of student "+count+" :");
        marks=sc.nextInt();
        // sc.close();
    }
    public Student(int r,String n,int m){
        count++;
        rollno=r;
        name=n;
        marks=m;
        
    }
    public Student(Student other){
        count++;
        this.rollno=other.rollno;
        this.name=other.name;
        this.marks=other.marks;
        
    }
    void display(){
        System.out.println(rollno+"\t"+name+"\t"+marks);
    }
    boolean compareStudent(Student other){
        return (this.marks==other.marks); 
    }
    public static void showCount(){
        System.out.println("Number of Students: "+count);
    }
}
class StudentDemo{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of students:");
        int n=sc.nextInt();
        Student s[]=new Student[n];
        System.out.println("Enter rollno of student 1:");
        int roll=sc.nextInt();
        System.out.println("Enter name of student 1:");
        String name=sc.next();
        System.out.println("Enter marks of student 1:");
        int mark=sc.nextInt();
        s[0]=new Student(roll,name,mark);
        for(int i=1;i<n;i++){
            s[i]=new Student();
        }
        if(s[0].compareStudent(s[1])){
            System.out.println("First and second students have same mark");
        }else{
            System.out.println("First and second students have different mark");
        }
        sortStudents(s);
        System.out.println("Rank List:");
        System.out.println(" Rollno|Name   |Marks");
        for(int i=0;i<n;i++){
            
            System.out.print((i+1)+". ");
            s[i].display();
        }
        Student.showCount();
        sc.close();
    }
    public static void sortStudents(Student s[]){
        for(int i=0;i<Student.count-1;i++){
            for(int j=i+1;j<Student.count;j++){
                if(s[i].marks<s[j].marks){
                    Student temp=s[i];
                    s[i]=s[j];
                    s[j]=temp;
                }
            }
        }
    }
}