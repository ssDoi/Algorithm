import java.io.*;
import java.util.*;
/**
 * 문제:Main_1992_쿼드트리
 * 결과:맞았습니다!
 * 실행시간:88ms
 * 메모리:11892kb
 * 아이디어: 분할정복기법 사용 인덱스 범위를 n/2씩 4개로 쪼개서 재귀를 사용하였다.
 * 맵을 전역변수를 하여 인덱스와 순회할 크기만 넘겨 전체가 같은 값이면 그 값을 출력한다.
 * 아니라면 괄호안에 시작인덱스를 n/2씩 증가시키고 크기를 n/2을 보내 4개로 쪼갠뒤 메소드를 4번 나뉘어 호출한다. 
 */

public class Main {

	//출력을 위한 버퍼드라이터
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	//통합 맵
	static char map[][];
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//맵의 크기
		int n = Integer.parseInt(br.readLine());
		//맵 객체생성
		map = new char[n][n];
		//맵 입력
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		//출력할 쿼드트리 생성
		generateAnswer(0,0,n);
		//출력
		bw.flush();

	}
	public static void generateAnswer(int startX, int startY, int n) throws IOException {
		//x,y시작위치와 순회할 맵의 크기
		//시작위치의 값
		char firstValue = map[startY][startX];
		//범위의 값 하나의 값인지 확인
		boolean isOneColor = true;
		//시작위치부터 맵의 크기까지 순회
		Loop:
		for(int i=startY; i < startY+n;i++) {
			for(int j = startX; j<startX+n;j++) {
				//시작위치의 값과 다르다면 isOneColor false하고 반복문탈출
				if(firstValue != map[i][j]) {
					isOneColor = false;
					break Loop;
				}
			}
		}
		//만약 범위가 하나의 값으로 이루어져있다면
		if(isOneColor) {
			//처음위치 값 출력
			bw.append(firstValue);
		}else {
			//아니라면 괄호 넣고 4개의 위치로로 쪼개고 크기도 1/4로 쪼개서 순회
			bw.append("(");
			generateAnswer(startX,startY,n/2);
			generateAnswer(startX + n/2,startY,n/2);
			generateAnswer(startX,startY + n/2,n/2);
			generateAnswer(startX + n/2,startY + n/2,n/2);
			bw.append(")");
		}
		
	}
	
}