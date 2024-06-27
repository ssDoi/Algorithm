import java.io.*;
import java.util.*;


public class Main {

	static int arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		p(new boolean[n],n,m,new int[m],0);
		
		

	}
	
	public static void p(boolean[] visited,int n,int m,int[] a, int cnt) {
		if(cnt==m) {
			for(int i=0;i<m;i++) {
				System.out.print(arr[a[i]]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(visited[i]) continue;
			visited[i]= true;
			a[cnt]=i;
			p(visited,n,m,a,cnt+1);
			visited[i] = false;
		}
		
	}

}