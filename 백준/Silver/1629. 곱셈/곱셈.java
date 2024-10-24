import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        int c=sc.nextInt();

        System.out.print(solution(a,b,c));
    }
    public static long solution(long a,long b,long c){
        long result;
        long half;
        if(b==0){
            return 1;
        }
        half = solution(a,b/2,c);
        result = (half*half)%c;

        if(b%2 !=0){
            result = (result*a)%c;
        }

        return result;
    }
}