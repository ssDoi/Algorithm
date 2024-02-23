import java.io.*;
import java.util.*;

public class Main {
	static int sushi[];
	static boolean stype[];
	static int max=0;
	static Map<Integer, Integer> eat;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//초밥벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken());	//초밥의 가짓수 
		int k = Integer.parseInt(st.nextToken());	//연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken());	//쿠폰번호
		sushi = new int[n];
		for (int i = 0; i < n; i++) {
			sushi[i]= Integer.parseInt(br.readLine());
		}
		stype = new boolean[d+1];
		int cnt=0;
		//ArrayList<Integer> eat = new ArrayList<>();
		eat = new HashMap<Integer, Integer>();
		for(int i=1; i<d+1;i++) {
			eat.put(i, 0);
		}
		for(int j=0; j<k; j++) {
			eat.put(sushi[j%n], eat.get(sushi[j%n])+1);
			if(stype[sushi[j%n]]) continue;
			stype[sushi[j%n]] =true;
			cnt++;
		}
		for(int i=k; i<n+k;i++) {
			eat.put(sushi[(i-k)%n], eat.get(sushi[(i-k)%n])-1);
			if(eat.get(sushi[(i-k)%n])==0) {
				cnt--;
				stype[sushi[(i-k)%n]] =false;
			}
			eat.put(sushi[i%n], eat.get(sushi[i%n])+1);
			//System.out.println(eat.toString());
			if(!stype[sushi[i%n]]) {
				stype[sushi[i%n]] =true;
				cnt++;
			}
			if(stype[c]) {
				max= Math.max(max, cnt);
			}else {
				max= Math.max(max, cnt+1);
			}
			
		}

			System.out.println(max);
	}
}