import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 문제: Main_16926_배열 돌리기1_서동인
 * 결과: 맞았습니다!
 * 메모리:297784 kb
 * 실행시간: 1636ms
 * @author 서동인
 * 아이디어: 맵을 입력받고 맵의 크기와 같은 크기의 맵을 준비한다.
 * 테두리부터 하나씩 값을 이동시켜 돌린 후 맵의 크기인 n과 m을 줄이고 인덱스 시작위치를 증가시켜
 * 안쪽 배열을 돌린다.
 */
public class Main {
	//배열을 상하반전
	public static int[][] verticalReverse(int n, int m, int map[][])
	{	
		//복사되어 리턴될 배열
		int tmp[][] = new int[n][m];
		//복사할 배열의 y위치 반대로하고 저장
		for(int i = 0 ; i <n;i++) {
			tmp[i] = map[n-i-1];
		}
		return tmp;
	}
	//배열 좌우 반전
	public static int[][] horizontalReverse(int n, int m, int map[][])
	{	
		//tmp를 사용하여 map행의 처음과 끝값 하나씩 서로 바꿔주었다.
		for(int i = 0 ; i <n;i++) {
			for(int j=0; j<m/2;j++) {
				int tmp = map[i][j];
				map[i][j] = map[i][m-1-j];
				map[i][m-1-j] = tmp;
			}
		}
		return map;
	}
	//배열 오른쪽 90도 회전
	public static int[][] rotateR90(int n, int m, int map[][])
	{	
		//복사되어 리턴될 배열 n과 m크기를 바꿈
		int tmp[][] = new int[m][n];
		//열을 고정시키고 n-1-i부터 계속 값을 넣어줌
		for(int i = 0 ; i <n;i++) {
			for(int j=0; j<m;j++) {
				tmp[j][n-1-i] = map[i][j]; 
			}
		}
		return tmp;
	}
	//배열 왼쪽 90도 회전
	public static int[][] rotateL90(int n, int m, int map[][])
	{	
		//복사되어 리턴될 배열 n과 m크기를 바꿈
		int tmp[][] = new int[m][n];
		//행을 고정시키고  m-1-j부터 값을 넣어줌
		for(int i = 0 ; i <n;i++) {
			for(int j=0; j<m;j++) {
				tmp[m-1-j][i] = map[i][j]; 
			}
		}
		return tmp;
	}
	public static int[][] moveGroup(int n, int m, int map[][])
	{	
		//복사되어 리턴될 배열
		int tmp[][] = new int[n][m];
		//n/2, m/2로 축소된 범위를 순회
		for(int i = 0 ; i <n/2;i++) {
			for(int j=0; j<m/2;j++) {
				//1->2
				tmp[i][m/2+j] = map[i][j];
				//2->3
				tmp[n/2+i][m/2+j] = map[i][m/2+j];
				//3->4
				tmp[n/2+i][j] = map[n/2+i][m/2+j];
				//4->1
				tmp[i][j] = map[n/2+i][j];
				
			}
		}
		return tmp;
	}
	public static int[][] moveGroup2(int n, int m, int map[][])
	{	
		//복사되어 리턴될 배열
		int tmp[][] = new int[n][m];
		//n/2, m/2로 축소된 범위를 순회
		for(int i = 0 ; i <n/2;i++) {
			for(int j=0; j<m/2;j++) {
				//1->4
				tmp[n/2+i][j] = map[i][j];
				//4->3
				tmp[n/2+i][m/2+j] = map[n/2+i][j];
				//3->2
				tmp[i][m/2+j] = map[n/2+i][m/2+j];
				//2->1
				tmp[i][j] = map[i][m/2+j]; 
			}
		}
		return tmp;
	}
	
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader  br =new BufferedReader(new InputStreamReader(System.in));
		//출력을 위한 버퍼드라이터
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//입력을 위한 스트링토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		//행의 크기
		int n = Integer.parseInt(st.nextToken());
		//열의 크기
		int m = Integer.parseInt(st.nextToken());
		//돌릴 횟수
		int r = Integer.parseInt(st.nextToken());
		//맵변수 초기화
		int map[][] = new int[n][m];
		//맵을 입력받는다.
		for(int i=0; i <n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//스트링토크나이저 객체생성
		st = new StringTokenizer(br.readLine());
		//실행할 연산 번호 
		int op;
		for(int i=0 ; i<r;i++) {
			//연산 번호 입력
			op = Integer.parseInt(st.nextToken());
			//연산번호에 따라 다른 메서드 ㅈ
			switch (op) {
			case 1:
				map =verticalReverse(map.length,map[0].length,map);
				break;
			case 2:
				map =horizontalReverse(map.length,map[0].length,map);
				break;
			case 3:
				map =rotateR90(map.length,map[0].length,map);
				break;
			case 4:
				map =rotateL90(map.length,map[0].length,map);
				break;
			case 5:
				map =moveGroup(map.length,map[0].length,map);
				break;
			case 6:
				map =moveGroup2(map.length,map[0].length,map);
				break;
			}
		}
		//돌린 결과 출력
		for(int i=0; i <map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				bw.append(map[i][j] + " ");
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
		br.close();

	}

}