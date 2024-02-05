import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 문제: Main_2493_탑_서동인
 * 결과: 맞았습니다!
 * 메모리:13852 kb
 * 실행시간: 200 ms
 * @author 서동인
 * 아이디어: 원형순회를 위해 %배열size연산으로 인덱스값을 한정시켰다
 * 배열 중간에 값이 지워지기 때문에 arraylist활용
 * 지운 값을 새로운 리스트에 저장
 * arraylist안의 값이 전부사라지기 까지 while문 반복
 *
 */


public class Main {


	public static void main(String[] args) throws Exception {
		class Tower{
			int index;
			int height;
			public Tower(int index, String height) {
				this.index = index;
				this.height =Integer.parseInt(height);
			}
			
		}
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//입력을 위해 라인입력 스플릿
		String tmp[] = br.readLine().split(" ");
		Stack<Tower> tower = new Stack<>();
		int[] answer = new int[n];
		tower.push(new Tower(0,tmp[0]));
		//n입력
		for(int i=1; i <n;i++) {
			if(tower.peek().height < Integer.parseInt(tmp[i])){
				while(!tower.isEmpty()&&tower.peek().height < Integer.parseInt(tmp[i]) ) {
					tower.pop();
				}
				if(tower.isEmpty()) {
					answer[i] = 0;
				}else {
					answer[i] = tower.peek().index+1;
				}
				tower.push(new Tower(i,tmp[i]));
			}else {
				answer[i] = tower.peek().index+1;
				tower.push(new Tower(i,tmp[i]));
			}
			
			
		}
		for(int num : answer) {
			System.out.print(num+ " ");
		}
		
	


	}
	

}