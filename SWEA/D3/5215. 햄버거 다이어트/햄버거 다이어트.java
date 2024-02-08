import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제: D4_5215_햄버거 다이어트
 * 결과: 맞았습니다!
 * 메모리:19,860kb
 * 실행시간: 186ms
 * @author 서동인
 * 아이디어: 백트래킹을 활용하여 풀었다.
 * 햄버거 재료의 칼로리와 맛점수 정보를 받고
 * 선택시 칼로리와 맛점수를 누적하여 재귀하였다.
 * 만약 칼로리의 누적치가 L보다 커진다면 return하여 백트래킹을 하였다.
 */
public class Solution {
	//맛점수
	static int scores[];
	//칼로리
	static int cals[];
	//맛점수 맥스값
	static int max;
	public static void main(String[] args) throws Exception{
		//버퍼드리더
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링토크나이저
		StringTokenizer st;
		//테스트케이스 입력
		int T = Integer.parseInt(br.readLine());
		//테스트케이스 반복
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//스트링토크나이저 객체생성
			st = new StringTokenizer(br.readLine());
			//N과 L입력
			int N=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			//맛점수 칼로리 객체생성
			scores = new int[N];
			cals = new int[N];
			//맛점수와 칼로리 입력
			for(int i=0; i <N;i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}
			//맥스 초기화
			max=0;
			//dfs시작
			dfs(L,N,0,0,0);
			//결과출력
			System.out.println("#"+test_case+" "+max);
		}

	}
	public static void dfs (int L, int N,int cnt,int scoreSum,int calSum) {
		//L,N,재귀횟수,맛점수 누적,칼로리누적
		if(calSum>L) {//칼로리누적치가 리미트칼로리보다 높으면 리턴
			return;
		}
		if(cnt==N) {//다 재귀했으면 맥스값 갱신
			max = Math.max(scoreSum, max);
			return;
		}
		//선택한 경우 맛점수 칼로리 누적
		dfs(L,N,cnt+1,scoreSum+scores[cnt],calSum+cals[cnt]);
		//선택하지 않은경우 다음재귀호출
		dfs(L,N,cnt+1,scoreSum,calSum);
	
	}
}