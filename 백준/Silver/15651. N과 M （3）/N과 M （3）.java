import java.io.*;
import java.util.*;


public class Main {
	static BufferedWriter bw;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		perm(n,m,0,new int[m]);
		bw.flush();

	}
	public static void perm(int n, int m, int cnt, int arr[]) throws IOException {
		if(cnt==m) {
			for(int num : arr) {
				bw.append(num+ " ");
			}
			bw.append("\n");
			return;
		}
		
		for(int i=1; i<n+1;i++) {
			arr[cnt] = i;
			perm(n,m,cnt+1,arr);
		}
	}

}