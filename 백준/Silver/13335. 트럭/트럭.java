import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제:Main_13335_트럭
 * 결과:맞았습니다
 * 실행시간:540ms
 * 메모리:13352kb
 * 아이디어: 큐를 쓰면 될 것 같다.
 * 해결시간:1시간이상
 */

public class Main {
	


	public static void main(String[] args) throws IOException {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] bd = new int[w+1];
		Queue<Integer> trucks = new ArrayDeque<>();
		for(int i=0; i <n;i++) {
			trucks.offer(Integer.parseInt(st.nextToken()));
		}
		int cnt=0;
		int weightInBridge = 0;
		while(!trucks.isEmpty() || weightInBridge !=0) {
			
			/*if(trucks.peek()+weightInBridge<= l) {
				weightInBridge+=trucks.poll();
				if(trucks.isEmpty()) {
					cnt+=w+1;
				}else {
					cnt++;
				}
			}else {
				weightInBridge=0;
				cnt+=w-1;
			}*/
			for(int i=w; i >0; i--) {
				int tmp = bd[i-1];
				bd[i-1] =bd[i];
				bd[i] = tmp;
			}
			if(bd[w] !=0) {
				weightInBridge -=bd[w];
				bd[w] = 0;
				
			}
			if(!trucks.isEmpty() &&trucks.peek()+weightInBridge<= l) {
				bd[0] =trucks.poll();
				weightInBridge +=bd[0];
			}

			cnt++;
		}
		System.out.println(cnt);
	}
}