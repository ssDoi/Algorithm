import java.io.*;
import java.util.*;

public class Main {

	static int people[];
	static int p[];
	static ArrayList<Node> graph;
	static int n;
	static int min;
	static boolean valid;
	static boolean ex;
	public static class Node{
		int people;
		ArrayList<Integer> adjust;
		public Node(int people) {
			adjust = new ArrayList<>();
			this.people = people;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		st =new StringTokenizer(br.readLine());
		graph = new ArrayList<>();
		min = Integer.MAX_VALUE;
		for(int i=0; i<n;i++) {
			graph.add(new Node(Integer.parseInt(st.nextToken())));
		}
		for(int i=0; i<n;i++) {
			st =new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for(int j=0; j<k;j++) {
				graph.get(i).adjust.add(Integer.parseInt(st.nextToken())-1);
			}
		}

		for(int i=1;i<=n/2;i++) {
			boolean[] visited = new boolean[n];
			comb(0,i,visited);
		}
		if(valid) {
			System.out.println(min);
		}else {
			System.out.println(-1);
		}
		
		

	}
	public static boolean check(boolean visited[], int current,int cnt, int r,ArrayList<Integer> arr) {
		
		visited[current] = true;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(current);
		while(!q.isEmpty()) {
			int index =q.poll();
			ArrayList<Integer> adjust  = graph.get(index).adjust;
			for(int i=0; i<adjust.size();i++) {
				if(!visited[adjust.get(i)] &&arr.contains(adjust.get(i))) {
					q.offer(adjust.get(i));
					visited[adjust.get(i)] =true;
				}
			}
			if(q.isEmpty()) {
				boolean isOver = true;
				for(int num : arr) {
					if(!visited[num]) {
						isOver=false;
						break;
					}
				}
				if(isOver) {
					return true;
				}
			}
		}
		
		return false;
	}
	public static void comb(int cnt, int r, boolean[] visited) {
		if(r==0) {
			ArrayList<Integer> arr1 = new ArrayList<>();
			ArrayList<Integer> arr2 = new ArrayList<>();
			for(int i=0; i<n;i++) {
				if(visited[i]) {
					arr1.add(i);
				}else {
					arr2.add(i);
				}
			}
			if(check(new boolean[n],arr1.get(0),1,arr1.size(), arr1) &&
					check(new boolean[n],arr2.get(0),1,arr2.size(),arr2)) {
				valid= true;
				int sum1=0;
				int sum2=0;
				for(int num : arr1) {
					sum1+=graph.get(num).people;
				}
				for(int num : arr2) {
					sum2+=graph.get(num).people;
				}
				min = Math.min(min, Math.abs(sum1-sum2));
			}
			return;
		}
		if(cnt==n) {
			return;
		}
		visited[cnt] = true;
		comb(cnt+1,r-1,visited);
		visited[cnt] = false;
		comb(cnt+1,r,visited);
	}

}