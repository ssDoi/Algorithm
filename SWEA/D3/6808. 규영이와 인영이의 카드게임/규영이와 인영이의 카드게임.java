import java.io.*;
import java.util.*;
/**
 * 문제: D3_6808_규영이와 인영이의 카드게임
 * 결과: 맞았습니다!
 * 메모리:20496kb
 * 실행시간: 2667ms
 * @author 서동인
 * 아이디어: 순열을 사용하여 인영이가 내는 카드의 순서를 모두 구한 뒤
 * 서로의 승부의 결과를 계산한다. 그후 규영이의 win과 lose를 출력한다.
 * 
 */
public class Solution {

	//규영이 카드패
	static int[] nums1;
	//인영이 카드패 -> 내는 순서대로 정렬됨
	static int[] nums2;
	//규영이가 이기고 진 횟수
	static int win,lose;
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드 리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링토크나이저
		StringTokenizer st;
		//테스트케이스 입력
		int T;
		T=Integer.parseInt(br.readLine());
		//테스트케이스만큼 돌리기
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//스트링토크나이저 객체생성
			st = new StringTokenizer(br.readLine());
			//규영이, 인영이 패 객체생성
			nums1 = new int[9];
			nums2 = new int[9];
			//규영이가 가진패가 아닌 것을 고르기 위한 boolean배열
			boolean isNum1[] = new boolean[19];
			//규영이 패 입력
			for(int i=0;i<9;i++) {
				nums1[i] = Integer.parseInt(st.nextToken());
				//규영이 패에 있는 수는 true
				isNum1[nums1[i]] =true;
			}
			//인영이 패
			int arr[] = new int[9];
			//인영이 패 인덱스
			int ci=0;
			//인영이 패 저장
			for(int i=1;i<=18;i++) {
				//규영이패가 아니라면 인영이패로 저장
				if(isNum1[i] ==false) {
					arr[ci++] = i;
				}
			}
			//순열 중복회피를 위한 visited배열
			boolean visited[] = new boolean[9];
			//이기고 진 횟수 초기화
			win =0;
			lose=0;
			//순열실행
			permutation(0,visited,arr);
			//결과 출력
			System.out.println("#" + test_case+ " " +win + " " + lose);
			
			

		}

	}
	public static void permutation(int cnt, boolean[] visited,int[] arr) {
		// cnt가 arr길이만큼 순회됬다면 순열만들기 종료
		if(cnt==arr.length) {
			//규영이 점수
			int score1=0;
			//인영이 점수
			int score2=0;
			for(int i=0; i<9;i++) {
				//규영이 가 낸 카드가 크다면 규영이 점수 획득
				if(nums1[i] > nums2[i]) {
					score1+=nums1[i] + nums2[i];
				}else {//인영이가 낸 카드가 크다면 인영이 점수 획득
					score2+=nums1[i] + nums2[i];
				}
				//1~18까지 더한 수는 171 그 반 이상 점수를 얻었다면 이긴 것이므로 규영이 승
				if(score1>=86) {
					win++;
					return;
				}else if(score2>=86) {	//인영이가 86이상 얻었으면 인영이 승, 규영이 패
					lose++;
					return;
				}
			}
			
			
		}
		//순열을 위한 반복문
		for(int i=0; i<arr.length;i++) {
			//중복확인
			if(visited[i] == true) continue;
			//해당 배열의 값을 인영이패에 넣음
			nums2[cnt] = arr[i];
			//해당 인덱스 순열에 넣었다는 것을 확인
			visited[i] = true;
			//다음 순열 실행
			permutation(cnt+1,visited,arr);
			//해당 인덱스 순열에 배제하고 다음 반복순회
			visited[i] = false;
		}
	}

}