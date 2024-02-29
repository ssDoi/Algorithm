
import java.io.*;
import java.util.*;


public class Main {

	static item[] items;
	static int max = 0;
	static int k,n;
	static int m[];
	public static class item implements Comparable<item> {
		int weight;
		int value;

		public item(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(item o) {

			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "item [weight=" + weight + ", value=" + value + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		items = new item[n+1];
		int sum = 0;
		int d[][] = new int[n+1][k+1];
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			items[i] = new item(w, v);
		}
		//Arrays.sort(items);
		for(int i = 1 ; i<n+1;i++) {
			for(int j=1; j<k+1 ; j++) {
				if(j>=items[i].weight) {
					d[i][j] =Math.max( d[i-1][j],items[i].value+d[i-1][j-items[i].weight]);
				}else {
					d[i][j] = d[i-1][j];
				}
			}
		}
		/*for(int[] row : d) {
			for(int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}*/
		//powerSet(0,new boolean[n],0,0);
		System.out.println(d[n][k]);
	}

}