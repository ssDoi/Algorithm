import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * 문제: Main_2493_탑_서동인
 * 결과: 맞았습니다!
 * 메모리:13852 kb
 * 실행시간: 200 ms
 * @author 서동인
 * 아이디어: 스택을 이용하였다
 * 스택에 내림차순으로 탑의 높이를 저장
 * 탑의 높이 입력 시가 입력되면 스택의 peek값을 참조해서 자기보다 작으면
 * 자기보다 큰 수가 나올 때까지 pop한다.
 * 자기보다 큰 수가 나오면  스택의 peek값의 인덱스를 answer배열에 넣고 자신을 stack에 넣는다.
 */


public class Main {


	public static void main(String[] args) throws Exception {
		//Tower 객체
		class Tower{
			//탑의 인덱스
			int index;
			//탑의 높이
			int height;
			//탑의 인덱스와 높이 초기화 생성자
			public Tower(int index, String height) {
				this.index = index;
				this.height =Integer.parseInt(height);
			}
			
		}
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//탑의 수 입력
		int n = Integer.parseInt(br.readLine());
		//탑의 크기를 입력받아 배열에 저장
		String tmp[] = br.readLine().split(" ");
		//레이저가 가리키는 방향의 큰 탑을 저장할 stack
		Stack<Tower> tower = new Stack<>();
		//먼저 제일 첫번째 탑을 스택에 넣는다.
		tower.push(new Tower(0,tmp[0]));
		bw.append(0+ " ");
		//1부터 시작 2번째 탑부터 반복한다.
		for(int i=1; i <n;i++) {
			//스택의 탑이 자신보다 큰 값이 나올 때 까지 pop
			while(!tower.isEmpty()&&tower.peek().height < Integer.parseInt(tmp[i]) ) {
				tower.pop();
			}
			//스택이 비면 자신이 가장 높은 탑이기 때문에 0을 출력
			if(tower.isEmpty()) {
				bw.append(0+ " ");
			}else {//아니라면 스택의 제일 위의 인덱스 +1 값을 출력
				bw.append(tower.peek().index+1 +" ");
			}
			//스택에 해당 탑 인덱스와 높이저장.
			tower.push(new Tower(i,tmp[i]));

			
		}
		//결과 출력
		bw.flush();
		
	}
	

}