import java.io.*;
import java.util.*;
public class Main {

	static int m[];
	/*public static f(int n) {
		if(m[n] == 0) {
			m[n] ==
		}
		return m[n];
	}*/
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		for(int i=0 ; i<n;i++) {
			st =new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			/*int d[] = new int[e+1];
			for(int j=w; j<=e ;j++) {
				if(w==e) d[j]=1;
				else d[j] = d[j-1] +
			}*/
			System.out.println(C(e,Math.min(w, e-w) ));
			
			
		}

	}
	public static long C(int n, int r) {
		long k=1;
		long d=1;
		int c=0;
		for(int i=n; c<r;i--,c++) {
			k=k*i;
		}
		for(int i=1; i<=r;i++) {
			d=d*i;
		}
		return k/d;
	}

}