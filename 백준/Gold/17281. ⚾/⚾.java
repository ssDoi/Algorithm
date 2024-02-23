import java.io.*;
import java.util.*;

public class Main {

	static int max;
	static int inning[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		StringTokenizer st;
		inning = new int[n][9];
		max = 0;
		for(int i=0; i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<9;j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean visited[]  = new boolean[9];
		int arr[] = new int[9];
		perm(0,visited,n,arr);
		System.out.println(max);
	}
	
	public static void perm(int cnt, boolean[] visited, int n, int arr[]) {
		if(cnt==9) {
			int score=0;
			if(arr[3]==0) {
				score = playBaseball(arr,n);
			}
			max = Math.max(max, score);
			return;
		}
		for(int i=0; i<9 ;i++) {
			if(visited[i]) continue;
			visited[i] =true;
			arr[cnt] = i;
			perm(cnt+1,visited,n,arr);
			visited[i] =false;
		}
	}
	public static int playBaseball(int[] arr, int n) {
		int score=0;
		int outCnt=0;
		int player=0;
		for(int i=0 ; i<n;i++) {
			int[] ground = new int[4];
			Arrays.fill(ground, 0);
			
			for(int j=player ; outCnt<3;j = ++j%9) {
				int action = inning[i][arr[j]];
				switch (action) {
				case 0:
					outCnt++;
					break;
				case 1:
					for(int k=2;k>=0;k--) {
						int tmp = ground[k+1];
						ground[k+1] = ground[k];
						ground[k] =tmp;
					}
					if(ground[3]==1) {
						ground[3]=0;
						score++;
					}
					ground[0] =1;
					break;
				case 2:
					for(int l=0; l<2;l++) {
						for(int k=2;k>=0;k--) {
							int tmp = ground[k+1];
							ground[k+1] = ground[k];
							ground[k] =tmp;
						}
						if(ground[3]==1) {
							ground[3]=0;
							score++;
						}
					}
					ground[1] =1;
					break;
				case 3:
					for(int l=0; l<3;l++) {
						for(int k=2;k>=0;k--) {
							int tmp = ground[k+1];
							ground[k+1] = ground[k];
							ground[k] =tmp;
						}
						if(ground[3]==1) {
							ground[3]=0;
							score++;
						}
					}
					ground[2] =1;
					break;
				case 4:
					for(int k=0;k<4;k++) {
						if(ground[k] ==1) {
							score++;
						}
					}
					Arrays.fill(ground, 0);
					score++;
					break;
				}
				if(outCnt>=3) {
					outCnt=0;
					player=(j+1)%9;
					break;
				}
			}
		}
		return score;
	}

}