import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드 리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링 토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		//나라 수
		int n = Integer.parseInt(st.nextToken());
		//랭킹확인할 나라 번호
		int k = Integer.parseInt(st.nextToken());
		//나라별 메달을 저장할 배열
		int[][] medal = new int[n][4];
		//선택된 나라랭크
		int rank = 1;
		//선택된 나라 메달정보
		int selectedNation[] = new int[3];
		//나라별 메달개수 저장
		for(int i=0; i <n ; i++) {
			st = new StringTokenizer(br.readLine());
			medal[i][0] = Integer.parseInt(st.nextToken());
			medal[i][1] = Integer.parseInt(st.nextToken());
			medal[i][2] = Integer.parseInt(st.nextToken());
			medal[i][3] = Integer.parseInt(st.nextToken());
			//선택된 나라 메달정보 저장
			if(medal[i][0]==k) {
				selectedNation[0] = medal[i][1];
				selectedNation[1] = medal[i][2];
				selectedNation[2] = medal[i][3];
			}
		}
		//랭킹 확인하는 반복문
		for(int i=0; i <n ; i++) {
			if(medal[i][0] == k) continue;	//확인할 나라면 다음 순회
			if(selectedNation[0] < medal[i][1]) {	//금메달 개수가 선택된 나라보다 많으면 랭크 늘어남
				rank++;
			}else if(selectedNation[0] == medal[i][1]) {	//금메달 개수가 같고 은메달 개수가 더 많으면 랭크 늘어남
				if(selectedNation[1] < medal[i][2]) {
					rank++;
				}else if(selectedNation[1] == medal[i][2]) {	//금,은 메달개수가 같고 동메달 개수가 더 많으면 랭크 늘어남
					if(selectedNation[2] < medal[i][3]) {
						rank++;
					}
				}
			}
		}
		//결과출력
		System.out.println(rank);
		
	}

}