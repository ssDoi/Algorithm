import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int cnt=0;
		boolean isValid = false;
		int min=Integer.MAX_VALUE;
		int pn=n;
		for(int i=0; i<=n/5;i++) {
			if(pn-i*5 >= 0) {
				pn=pn-i*5;
				cnt+=i;
			}
			if(pn%3==0) {
				cnt+=pn/3;
				min = Math.min(cnt, min);
				isValid=true;
			}
			cnt=0;
			pn = n;
		}
		if(isValid) {
			System.out.println(min);
		}else {
			System.out.println(-1);
		}
		
		
		
	}

}