import java.io.*;
import java.util.*;
/**
 * 문제: D6_1263_사람네트워크2
 * 결과: 맞았습니다
 * 메모리:106,580kb
 * 실행시간: 2984ms
 * @author 서동인
 * 아이디어: 플루이드 워샬을 이용해 문제를 풀었다.
 */
public class Solution {

	public static void main(String[] args) throws Exception{
		//입력을 위함 버프드리더 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//시행횟수
		int T;
		T=Integer.parseInt(br.readLine());
		//dp배열 초기화를 위한 큰 값
		int INF = 1000*1000+1;
		//시행횟수만큼 시행
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//스트링토크나이저 객체생성
			st = new StringTokenizer(br.readLine());
			//정점 수
			int n = Integer.parseInt(st.nextToken());
			//dp배열 초기화
			int[][] d = new int[n][n];
			//가중치가 없기 때문에 각 인접행렬을 그대로 dp 배열에 넣었다.
			for(int i= 0; i<n;i++) {
				for(int j=0 ; j<n; j++)
				{
					//입력받은 값
					int num = Integer.parseInt(st.nextToken());
					//입력받은값이 1이 아닐경우 i=j가 아니면 INF로 초기화 
					if(num!=1 && i!=j) {
						d[i][j] = INF;
					//아닐 경우 num값 입력
					}else {
						d[i][j] = num;
					}
				}
			}
			//플루이드 워샬 방법 각 k번째 정점을 경유지로 하나씩 고려한다.
			for(int k=0; k<n;k++) {
				//시작정점 하나씩 고려
				for(int i=0; i<n;i++) {
					if(i==k) continue;
					//시작정점에서 도착정점 하나씩 고려
					for(int j=0;j<n;j++) {
						if(i==j || j==k) continue;
						//기존 i->j보다 k를 경유해서 가는 방법의 비용이 더 적다면 d[i][j]를 갱신한다.
						d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
					}
				}
				
			}
			//각 정점별 CC
			int CC[] = new int[n];
			//CC의 최소값 INF로 초기화
			int min = INF;
			//CC를 얻어낸다.
			for(int i=0; i<n;i++) {
				for(int j=0;j<n;j++) {
					CC[i] +=d[i][j];
				}
				//min값 갱신
				min= Math.min(CC[i], min);
			}
			//min값 출력
			System.out.println("#" + test_case + " " + min);

		}

	}

}