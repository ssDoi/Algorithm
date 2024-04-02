import java.io.*;
import java.util.*;

public class Main {
	public static int p = 1000000;
	public static Map<Long,Long> fibos;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n =Long.parseLong(br.readLine());
		fibos = new HashMap<Long, Long>();
		fibos.put((long) 0, (long) 0);
		fibos.put((long) 1, (long) 1);
		fibos.put((long) 2, (long) 1);
		
		System.out.println(fibo(n)%p);
	}
	
	public static long fibo(long n) {
		if(n==0) {
			return fibos.get(n);
		}else if(n==1 || n==2) {
			return fibos.get(n);
		}
		if(fibos.get(n) != null) {
			return fibos.get(n);
		}
		if(n%2 ==0) {
			long f = fibo(n/2)%p;
			long f2 = fibo(n/2-1)%p;
			fibos.put(n,(f*f)%p +(2*f2*f)%p);
		}else {
			long f = fibo(n/2+1)%p;
			long f2 = fibo(n/2)%p;
			fibos.put(n,(f*f)%p + (f2*f2)%p);
		}
		return fibos.get(n);
	}

}