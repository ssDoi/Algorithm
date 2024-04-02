import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static int p = 1000000000;
	public static Map<Long,Long> fibos;
	public static void main(String[] args) throws Exception {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a =Long.parseLong(st.nextToken());
		long b =Long.parseLong(st.nextToken());
		//fibo값 재사용을 위한 map
		fibos = new HashMap<Long, Long>();
		//초기값 저장
		fibos.put((long) 0, (long) 0);
		fibos.put((long) 1, (long) 1);
		fibos.put((long) 2, (long) 1);
		if(fibo(b+2) < fibo(a+1)) { 
			System.out.println((p+(fibo(b+2) - fibo(a+1)))%p);
		}else {
			System.out.println(((fibo(b+2) - fibo(a+1)))%p);
		}
	}

	public static long fibo(long n) {
		//0,1,2,일 때 해당 값 리턴
		if(n==0) {
			return fibos.get(n);
		}else if(n==1 || n==2) {
			return fibos.get(n);
		}
		//키에 해당하는 값이 있으면 값을 리턴
		if(fibos.get(n) != null) {
			return fibos.get(n);
		}
		//n이 짝수인경우 f(n)^2 +2*f(n-1)*f(n)
		if(n%2 ==0) {
			long f = fibo(n/2)%p;
			long f2 = fibo(n/2-1)%p;
			fibos.put(n,(f*f)%p +(2*f2*f)%p);
		//n이 홀수 인 경우 f(n+1)^2*f(n)^2
		}else {
			long f = fibo(n/2+1)%p;
			long f2 = fibo(n/2)%p;
			fibos.put(n,(f*f)%p + (f2*f2)%p);
		}
		return fibos.get(n);
	}
}