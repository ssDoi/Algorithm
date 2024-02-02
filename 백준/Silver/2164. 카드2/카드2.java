import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 문제: Main_2164_카드2_서동인
 * 결과: pass
 * 메모리:18,920 kb
 * 실행시간: 103 ms
 * @author 서동인
 * 아이디어: 큐를 이용 제일 앞의 번호 버리고 그다음 문자를 다시 뒤로 보내는 것을 반복
 * @throws IOException 
 * @throws NumberFormatException 
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=n;i++) {
			q.offer(i);
		}
		while(true) {
			if(q.size()<=1) {
				break;
			}
			q.poll();
			q.offer(q.poll());
			
		}
		System.out.println(q.poll());

	}

}