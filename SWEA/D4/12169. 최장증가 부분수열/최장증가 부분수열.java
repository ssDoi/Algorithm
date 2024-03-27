import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++)
		{
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] list = new int[n+1];
			int LIS[] = new int[n+1];
			for(int i=1; i<n+1; i++){
				list[i] = Integer.parseInt(st.nextToken());
			}
			int max = 0;
			for(int i=1; i<n+1;i++){
				LIS[i] =1;
				for(int j=1; j<=i-1; j++){
					if(list[i] > list[j] && LIS[i] < LIS[j] +1){
						LIS[i] = LIS[j] +1;
					}
					max = Math.max(LIS[i], max);
				}
			}
			System.out.println("#" + tc+" " + max);
		}
	}

}