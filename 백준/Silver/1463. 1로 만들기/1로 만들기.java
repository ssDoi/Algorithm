import java.io.*;
import java.util.*;
public class Main {

	static int m[];
	public static int f(int n) {
		if(n==2 || n==3) {
			return m[n] =1;
		}else if(n==1) {
			return 0;
		}
		int min =Integer.MAX_VALUE;
		if(m[n] ==0) {
			if(n%3==0) {
				min= Math.min(f(n/3) +1 , min);
			}
			if (n%2==0){
				min= Math.min(f(n/2) +1 , min);
			}
			min= Math.min(f(n-1) +1 , min);
			m[n] = min;
		}
		return m[n];
	}
	public static void main(String[] args) throws Exception{
		Scanner sc  =new Scanner(System.in);
		int n = sc.nextInt();
		m = new int[n+1];

		System.out.println(f(n));

	}

}