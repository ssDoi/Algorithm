import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 문제: Main_1158_요세푸스문제_서동인
 * 결과: 맞았습니다!
 * 메모리:23796 kb
 * 실행시간: 124 ms
 * @author 서동인
 * 아이디어: 큐를 이용 제일 앞의 번호 버리고 그다음 문자를 다시 뒤로 보내는 것을 반복
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위해 라인입력 스플릿
		String tmp[] = br.readLine().split(" ");
		//n입력
		int n = Integer.parseInt(tmp[0]);
		//k입력
		int k = Integer.parseInt(tmp[1]);
		//숫자를 입력하기 위한 ArrayList
		ArrayList<Integer> o = new ArrayList<>();
		//1~n까지 o에 숫자 저장
		for(int i =1 ; i<=n;i++) {
			o.add(i);
		}
		//인덱스 i 0초기화
		int i=0;
		//정답을 저장할 어레이리스트
		ArrayList<Integer> answer = new ArrayList<>();
		//o에 있는 수가 있으면 반복
		while(o.size()!=0) {
			//k번째수는 k-1인덱스 참조 i에서 더해서 참조 원형순회를 위해 o.size() 나머지 계산
			i=(i+k-1)%o.size();
			//o에서 제거한 것을 answer리스트에 저장
			answer.add(o.remove(i));
		}
		//결과출력
		System.out.print("<");
		for(int j =0; j <answer.size(); j++) {
			if(j==answer.size()-1) {
				System.out.print(answer.get(j) + ">");
			}else {
				System.out.print(answer.get(j) + ", ");
			}
			
			
		}
		


	}

}