import java.util.Scanner;
public class Factorial{
    public static int findFactorial(int n){
        if(n==0){
            return 1;
        }else{
            return n*findFactorial(n-1);
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n=sc.nextInt();
        if(n<0){
            System.out.println("Factorial not defined for negative numbers!");
        }else{
            System.out.println("Factorial of "+n+" is "+findFactorial(n));
        }
        sc.close();
    }
}