import java.io.*;
import java.util.*;
public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T;
		T=Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = Integer.parseInt(br.readLine());
			boolean OddMode = false;
			int cntOdd = 0;
			long max = 0;
			for(int i=0; i<n;i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				long distance = Math.abs(x) + Math.abs(y);
				max = Math.max(distance, max);
				if(distance%2!=0) {
					cntOdd++;
				}
			}
			if(cntOdd==n) {
				OddMode=true;
			}else if(cntOdd==0) {
				OddMode=false;
			}else {
				bw.append("#"+test_case +" " + -1 + "\n");
				continue;
			}
			long isum = 0;
			long i = 0;
			while(isum <= Long.MAX_VALUE) {
				
				isum = i*(i+1)/2;
				if(OddMode) {
					if(isum%2 !=0 && isum>=max)
					{
						bw.append("#"+test_case +" " + i + "\n");
						break;
					}
				}else {
					if(isum%2 ==0 && isum>=max)
					{
						bw.append("#"+test_case +" " + i + "\n");
						break;
					}
				}
				i++;
			}
		}
		bw.flush();
		br.close();
		bw.close();
		
	}
}