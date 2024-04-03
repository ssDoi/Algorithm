import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<ArrayList<Integer>> studentsTaller;
	static ArrayList<ArrayList<Integer>> studentsLower;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		studentsTaller = new ArrayList<>();
		studentsLower = new ArrayList<>();
		for(int i=0;i<n+1;i++) {
			studentsTaller.add(new ArrayList<Integer>());
			studentsLower.add(new ArrayList<Integer>());
		}
		for(int i=0 ; i <m;i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			studentsTaller.get(s1).add(s2);
			studentsLower.get(s2).add(s1);
		}
		int cnt=0;
		for(int i=1;i<n+1;i++) {
			boolean[] visited = new boolean[n+1];
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(i);
			//System.out.println(i + "인경우:");
			visited[i]=true;
			boolean taller = true;
			while(!q.isEmpty()) {
				int s =q.poll();
				if(taller) {
					for(int os : studentsTaller.get(s)) {
						if(!visited[os]) {
							//System.out.print(os + " ");
							q.add(os);
							visited[os] = true;
						}
					}
				}else {
					for(int os : studentsLower.get(s)) {
						if(!visited[os]) {
							//System.out.print(os + " ");
							q.add(os);
							visited[os] = true;
						}
					}
				}
				
				if(q.isEmpty() && taller) {
					q.offer(i);
					taller = false;
				}
			}
			boolean isValid = true;
			for(int k=1; k<n+1;k++) {
				if(!visited[k]) {
					isValid= false;
					break;
				}
			}
			//System.out.println("\n Valid is" + isValid);
			if(isValid) {
				cnt++;
			}
		}
		System.out.println(cnt);
		

	}

}