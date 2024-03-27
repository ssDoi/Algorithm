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
			for(int i=1; i<n+1; i++){
				list[i] = Integer.parseInt(st.nextToken());
			}
			

			ArrayList<Integer> d= new ArrayList<>();
			d.add(list[1]);
			for(int i=2; i<n+1; i++) {
				if(list[i] > d.get(d.size()-1)) {
					d.add(list[i]);
				}else {
					for(int j=0; j<d.size(); j++) {
						if(list[i] <= d.get(j)) {
							d.set(j, list[i]);
							break;
						}
					}
				}
				
			}
			System.out.println("#" + tc+" " + d.size());
		}
	}

}