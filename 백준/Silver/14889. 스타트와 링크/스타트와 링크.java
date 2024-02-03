import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 문제:Main_14889_스타트와 링크
 * 결과:맞았습니다
 * 실행시간:280ms
 * 메모리:74036kb
 * 아이디어: 재귀와 조합을 이용하여 해결
 */

public class Main {

	static int map[][];
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링 토크나이저
		StringTokenizer st;
		//전체 사람 수
		int n = Integer.parseInt(br.readLine());
		//팀 시너지 2차배열 객체 초기화
		map = new int[n][n];
		//선택됐는지 저장하는 배열
		boolean selected[] = new boolean[n];
		//맵에 팀 시너지 저장
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//팀을 2로 나눠서 선택
		comb(0,n,selected,n/2);
		//결과 출력
		System.out.println(min);
		
		
	}
	public static void comb(int st , int n, boolean[] selected,int r) {
		if(r == 0) {	//전부선택 됐으면
			//스타트팀 배열 인덱스
			int sc=0;
			//링크팀 배열 인덱스
			int lc=0;
			//스타트팀 배열
			int start[] = new int[n/2];
			//링크 팀 배열
			int link[] = new int[n/2];
			//스타트팀과 링크팀을 나눠 배열에 넣는다
			for(int i = 0 ; i<n;i++) {
				if(selected[i]) {
					start[sc++] = i;
				}else {
					link[lc++] = i;
				}
			}
			//둘의 팀전력차이와 min 중 더 적은 것을 min에 저장
			min = Math.min(min, Math.abs(generateTeamPower(start) - generateTeamPower(link)));
		}
		//하나씩 선택하는 반복문
		for(int i= st; i < n ; i++) {
			//선택됐으면 해당 i true
			selected[i] =true;
			//선택 됐기 때문에 선택할 수 r을 빼고 순회시작할 인덱스를 1증가 시킨다
			comb(i+1,n,selected,r-1);
			//아닌 경우 false하고 다음 순회
			selected[i] =false;
		}
		
	}
	//팀의 전력을 산출하는 메서드
	public static int generateTeamPower(int[] arr) {
		int sum = 0;	//팀전력 합
		//배열을 받아 맵에있는 팀전력 시너지를 sum에 누적시킨다.
		//arr의 개수 중 2개를 선택하는 반복문
		for(int i=0; i<arr.length;i++) {
			for(int j = i+1; j<arr.length; j++) {
				sum+= map[arr[i]][arr[j]] + map[arr[j]][arr[i]];
			}
		}
		return sum;//결과를 반환
	}
}