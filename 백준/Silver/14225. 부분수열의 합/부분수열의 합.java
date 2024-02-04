import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * 문제:Main_14225_부분수령의 합
 * 결과:맞았습니다
 * 실행시간:280ms
 * 메모리:74036kb
 * 아이디어: 재귀를 이용, 부분집합이용
 * 해결시간:1시간이상 -> visited이용 용수코드 참고
 */

public class Main {
	
	//수열을 저장할 s 변수
	static int s[];
	//수열의 합이 존재하는지 나타내는 배열
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링토크나이저
		StringTokenizer st;
		//수열의 크기
		int n = Integer.parseInt(br.readLine());
		//스트리토크나이저 객체 생성
		st = new StringTokenizer(br.readLine());
		//수열객체 생성
		s = new int[n];

		//수열의 합
		int sum = 0;
		//수열 입력받는 반복문
		for (int i = 0; i < n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
			sum+=s[i];
		}
		//수열 전체의 합보다 1크게 배열을 만듦
		visited = new boolean[sum+2];
		//부분집합생성해서 값을 더하는 메서드
		generatePart(0, 0, n);
		//수열에 빈 자연수가 있는 지 확인하는 플래그
		int i = 1;
		for(; visited[i]; i++) {
		}
		System.out.println(i);

	}
		
	public static void generatePart(int cnt, int sum, int n) {
		//재귀의 위치, 수열의 합, 수열의 크기
		//재귀가 끝나면
		if(cnt==n) {
			//sum이 0이 아니묜
			if(sum!=0)
				//해당값 visted배열 true
				visited[sum] = true;
			
			//리턴
			return;
		}
		
		//수열에 합한 경우
		generatePart(cnt+1, sum +s[cnt],n);
		//수열 안합한 경우
		generatePart(cnt+1, sum,n );
	}
}