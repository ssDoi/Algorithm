import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {
	public static Map<Long,BigInteger> fibos;
	public static BigInteger a2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n =Long.parseLong(br.readLine());
		fibos = new HashMap<Long, BigInteger>();
		fibos.put((long) 0, new BigInteger("0"));
		fibos.put((long) 1, new BigInteger("1"));
		fibos.put((long) 2, new BigInteger("1"));
		a2= new BigInteger("2");
		System.out.println(fibo(n));
	}
	
	public static BigInteger fibo(long n) {
		if(n==0) {
			return fibos.get(n);
		}else if(n==1 || n==2) {
			return fibos.get(n);
		}
		if(fibos.get(n) != null) {
			return fibos.get(n);
		}
		if(n%2 ==0) {
			BigInteger f = fibo(n/2);
			BigInteger f2 = fibo(n/2-1);
			fibos.put(n,(f.multiply(f).add(f.multiply(f2).multiply(a2))));
		}else {
			fibos.put(n,fibo(n+1).subtract(fibo(n-1)));
		}
		return fibos.get(n);
	}

}