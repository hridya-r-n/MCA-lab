import java.util.Scanner;
public class Fibonacci{
    public static int fibonacci(int n){
        if(n==1){
            return 0;
        }else if(n==2){
            return 1;
        }else{
            return fibonacci(n-1)+fibonacci(n-2);
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n:");
        int n=sc.nextInt();
        if(n>0){
            System.out.println(n+"th fibonacci number is "+fibonacci(n));
        }else{
            System.out.println("Negative number!");
        }
        sc.close();
    }
}