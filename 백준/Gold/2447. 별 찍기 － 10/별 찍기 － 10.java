import java.util.*;
import java.util.*;
import java.io.*;
/**
 * 문제:Main_2447_별찍기10
 * 결과:pass
 * 실행시간:356ms
 * 메모리:24488kb
 * 아이디어: 재귀를 사용하여 문제를 해결했다.
 * map2차배열을 만들어 전부 공백으로 채운뒤
 * n/3의 크기 범위와 상하좌우대각선을 위치로 주어 8번위 재귀를 호출
 * n==1일 때 해당 위치에 *삽입
 * 소요시간:30분
 */
public class Main_2447_별찍기10_서동인 {

	//맵 배열
	static char map[][];
	public static void main(String[] args) throws IOException {
		//입력을 위한 스캐너
		Scanner sc = new Scanner(System.in);
		//맵의 크기
		int n = sc.nextInt();
		//출력을 위한 버퍼드라이터
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//맵 객체 생성 초기화
		map = new char[n][n];
		//맵을 공백으로채움
		for(int i=0; i <n;i++) {
			for(int j=0; j<n;j++) {
				map[i][j] = ' ';
			}
		}
		//맵에 별찍기
		star(0,0,n);
		//맵 출력
		for(int i=0; i <n;i++) {
			for(int j=0; j<n;j++) {
				bw.append(map[i][j]);
			}
			bw.append("\n");
		}
		bw.flush();
		//입출력 클로즈
		sc.close();
		bw.close();
	}
	//맵에 별 찍기-재귀
	public static void star(int y,int x,int n) {
		//범위크기가 1이라면 해당위치에 별 저장, 리턴
		if(n ==1) {
			map[y][x] ='*';
			return;
		}
		//범위크기 0이면 그냥리턴
		if(n==0) {
			return;
		}
		//상하좌우대각선 탐색하여 n/3크기로 재귀호출 
		//왼쪽위
		star(y,x,n/3);
		//위
		star(y,x+ n/3,n/3);
		//오른쪽 위
		star(y,x+n/3*2,n/3);
		//왼쪽
		star(y+n/3,x,n/3);
		//오른쪽
		star(y+n/3,x+n/3 *2,n/3);
		//왼쪽 아래
		star(y+n/3*2,x,n/3);
		//아래
		star(y+n/3*2,x+n/3,n/3);
		//오른쪽 아래
		star(y+n/3*2,x+n/3 *2,n/3);
		
	}
}
