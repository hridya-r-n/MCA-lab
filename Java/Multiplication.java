import java.util.Scanner;
public class Multiplication{
    public void table(int n){
        for(int i=1;i<=10;i++){
            System.out.println(i+" * "+n+" = "+(i*n));
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n:");
        int n=sc.nextInt();
        Multiplication m=new Multiplication();
        System.out.println("Multiplication table of "+n);
        m.table(n);
        sc.close();
    }
}