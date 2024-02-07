import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 문제: Main_11286_절댓값 힙_서동인
 * 결과: 맞았습니다!
 * 메모리:27216 kb
 * 실행시간: 7566ms
 * @author 서동인
 * 아이디어: 우선순위 큐를 활용하여 해결
 * 우선순위 큐에 Comparator를 적용하여 절댓값 기준으로 정렬하게끔 만들었다.
 * 두 수의 절댓값이 같은경우 음수인 수가 앞으로 오게끔 만들었다.
 */
public class Main {

	public static void main(String[] args) throws Exception{
		
		// 입력을 위한 버퍼드리더
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		//입력 수 n 입력
		int n = Integer.parseInt(br.readLine());
		//우선순위 큐 초기화 , size는 n만큼, comparator사용
		PriorityQueue<Integer> q = new PriorityQueue<>(n, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				//두 수의 절댓값이 같은경우 음수인 수가 앞으로 옴
				if(o1.intValue() + o2.intValue() ==0 ) {
					return o1.intValue()<0?-1:1;
				}
				//절댓값 기준으로 오름차순 정렬
				return Math.abs(o1.intValue())> Math.abs(o2.intValue()) ? 1:-1;
			}
		});
		//큐에 입력 저장
		for(int i=0; i<n;i++) {
			//입력된 명령을 저장
			int order = Integer.parseInt(br.readLine());
			//0이 입력됐으면
			if(order ==0) {
				//q가비어있지 않았을 경우
				if(!q.isEmpty()) {
					//q의 값을 빼고 출력
					System.out.println(q.poll());
				}else {
					//q가 비어있으면 0출력
					System.out.println(0);
				}
			}else {
				//0이 아닌 수 입력시 q에 저장
				q.offer(order);
			}
			
		}
	}

}