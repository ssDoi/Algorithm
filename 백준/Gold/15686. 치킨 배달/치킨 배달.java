import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<int[]> shopList;
	static ArrayList<int[]> homeList; 
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int map[][] = new int[n][n];
		shopList = new ArrayList<int[]>();
		homeList = new ArrayList<>();
		for(int i=0; i <n;i++) {
			st =new StringTokenizer(br.readLine());
			for(int j=0; j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] ==2) {
					shopList.add(new int[] {i,j});
				}else if(map[i][j]==1) {
					homeList.add(new int[] {i,j});
				}
			}
		}
		boolean visited[] =new boolean[shopList.size()];
		comb(0,visited,m,m);
		System.out.println(min);

	}
	public static void comb(int cnt, boolean visited[],int m, int r) {
		if(r==0) {
			ArrayList<int[]> selected = new ArrayList<>();
			for(int i=0; i<shopList.size();i++) {
				if(visited[i]) {
					selected.add(shopList.get(i));
				}
			}
			int cDistance = 0;
			for(int i=0; i<homeList.size();i++) {
				int hDistance=Integer.MAX_VALUE;
				for(int j=0; j<selected.size(); j++) {
					hDistance=Math.min(hDistance, Math.abs(selected.get(j)[0] - homeList.get(i)[0]) + Math.abs(selected.get(j)[1] - homeList.get(i)[1]));
				}
				cDistance+=hDistance;
			}
			min = Math.min(min, cDistance);
			return;
		}
		if(cnt == shopList.size()) {
			return;
		}
		visited[cnt]=true;
		comb(cnt+1,visited,m,r-1);
		visited[cnt]=false;
		comb(cnt+1,visited,m,r);
		
	}

}