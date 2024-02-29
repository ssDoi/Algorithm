import java.io.*;
import java.util.*;


public class Main {

	static int weightArray[][];
	static int dp[][];
	static final int INF = 99999999;
	static int n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		weightArray = new int[n][n];
		dp = new int[n][(1<<n)-1];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				weightArray[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], INF);
		}

		System.out.println(dfs(0,1));

	}
	public static int dfs(int c, int visit) {
		if(visit == (1<<n)-1) {
			if(weightArray[c][0] ==0) return INF;
			return weightArray[c][0];
		}
		if(dp[c][visit] !=INF) {
			return dp[c][visit];
		}
		for(int i=0; i<n;i++) {
			if((visit &(1<<i)) ==0 && weightArray[c][i] !=0) {
				dp[c][visit] = Math.min(dp[c][visit], dfs(i,visit | (1<<i)) + weightArray[c][i]);
			}
		}
		return dp[c][visit];
	}

}