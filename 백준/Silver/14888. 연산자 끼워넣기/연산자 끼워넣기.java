import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * 문제:Main_14888_연산자끼워넣기
 * 결과:맞았습니다
 * 실행시간:96ms
 * 메모리:12876kb
 * 아이디어: 재귀를 이용, 부분집합이용
 * 해결시간:1시간이상 -> visited이용 용수코드 참고
 */

public class Main {
	

	//수열의 합이 존재하는지 나타내는 배열
	static boolean[] visited;
	static int nums[];
	static int op[];
	static char ops[];
	static char operators[];
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
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
		nums = new int[n];
		//연산자 객체 생성
		op = new int[4];
		//수열을 입력받는 반복문
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		//연산자모음 객체배열
		ops = new char[n-1];
		//연사자 순서 객체배열
		operators =  new char[n-1];
		//연산자 저장을 위한 인덱스
		int oc=0;
		//입력을위한 스트링토크나이저
		st = new StringTokenizer(br.readLine());
		//연산자 숫자를 밥아 가각 연산자 문자로 저장
		for (int i = 0; i < 4; i++) {
			int op = Integer.parseInt(st.nextToken());
			for(int j =0; j <op;j++) {
				switch (i) {
				case 0:
					ops[oc++] = '+';
					break;
				case 1:
					ops[oc++] = '-';
					break;
				case 2:
					ops[oc++] = '*';
					break;
				case 3:
					ops[oc++] = '/';
					break;
				}
			}
		}
		//중복검열을 위한 vistied 배열
		visited = new boolean[n-1];
		//순열 구하기 재귀
		generatePart(0,n-1);
		//맥스값 출력
		System.out.println(max);
		//민값 출력
		System.out.println(min);


	}
	//순열반복
	public static void generatePart(int cnt, int num) {
	
		if(cnt==num) {//재귀 끝
			//첫째자리 수와 둘째자리 수 를 연산하여 result에 저장
			int result = operate(nums[0], nums[1], operators[0]);
			//계속해서 연산하여 result에 저장
			for(int i=1; i<num;i++) {
				result = operate(result,nums[i+1],operators[i]);
			}
			//최소값 갱신
			min = Math.min(min, result);
			//최대값 갱신
			max = Math.max(max, result);
			return;
		}
		
		//순열을 구하는 반복문
		for(int i=0; i<num;i++) {
			//중복이라면 다음순회
			if(visited[i] == true) continue;
			//인덱스의 연산자를 해당 순서에 넣는다 
			operators[cnt] = ops[i];
			//중복 확인
			visited[i] = true;
			//확인된 중복을 가지고 다음 재귀
			generatePart(cnt+1,num);
			//중복 해제 다음 순회
			visited[i] = false;
			
		}


	}
	//연산자를 받아 계산결과를 반환하는 메서드
	public static int operate (int a, int b, char operator) {
		switch (operator) {
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '*':
				return a*b;
			case '/':
				return a/b;
		}
		return 0;
	}
}