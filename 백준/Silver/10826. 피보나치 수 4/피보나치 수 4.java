import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	
	static int fibo[];
	public static void main(String[] args) throws Exception{
		Scanner sc  = new Scanner(System.in);
		int n = sc.nextInt();
		BigInteger fibo[] = new BigInteger[n+1];
		if(n==0) {
			System.out.println(0);
			return;
		}else if(n==1||n==2) {
			System.out.println(1);
			return;
		}
		fibo[0] = new BigInteger("0");
		fibo[1] = new BigInteger("1");
		fibo[2] = new BigInteger("1");
		for(int i=3; i <=n;i++) {
			fibo[i] = fibo[i-1].add(fibo[i-2]);
		}
		System.out.println(fibo[n]);

	}
}