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
	//배열을 돌리는 메서드
	public static int[][] verticalReverse(int n, int m, int map[][])
	{	
		int tmp[][] = new int[n][m];
		for(int i = 0 ; i <n;i++) {
			tmp[i] = map[n-i-1];
		}
		return tmp;
	}
	public static int[][] horizontalReverse(int n, int m, int map[][])
	{	
		for(int i = 0 ; i <n;i++) {
			for(int j=0; j<m/2;j++) {
				int tmp = map[i][j];
				map[i][j] = map[i][m-1-j];
				map[i][m-1-j] = tmp;
			}
		}
		return map;
	}
	public static int[][] rotateR90(int n, int m, int map[][])
	{	
		
		int tmp[][] = new int[m][n];
		for(int i = 0 ; i <n;i++) {
			for(int j=0; j<m;j++) {
				tmp[j][n-1-i] = map[i][j]; 
			}
		}
		return tmp;
	}
	public static int[][] rotateL90(int n, int m, int map[][])
	{	
		
		int tmp[][] = new int[m][n];
		for(int i = 0 ; i <n;i++) {
			for(int j=0; j<m;j++) {
				tmp[m-1-j][i] = map[i][j]; 
			}
		}
		return tmp;
	}
	public static int[][] moveGroup(int n, int m, int map[][])
	{	
		
		int tmp[][] = new int[n][m];
		for(int i = 0 ; i <n/2;i++) {
			for(int j=0; j<m/2;j++) {
				
				tmp[i][m/2+j] = map[i][j];
				tmp[n/2+i][m/2+j] = map[i][m/2+j]; 
				tmp[n/2+i][j] = map[n/2+i][m/2+j]; 
				tmp[i][j] = map[n/2+i][j];
				
			}
		}
		return tmp;
	}
	public static int[][] moveGroup2(int n, int m, int map[][])
	{	
		
		int tmp[][] = new int[n][m];
		for(int i = 0 ; i <n/2;i++) {
			for(int j=0; j<m/2;j++) {
				tmp[n/2+i][j] = map[i][j];
				tmp[n/2+i][m/2+j] = map[n/2+i][j];
				tmp[i][m/2+j] = map[n/2+i][m/2+j]; 
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
		st = new StringTokenizer(br.readLine());
		int op;
		for(int i=0 ; i<r;i++) {
			op = Integer.parseInt(st.nextToken());
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