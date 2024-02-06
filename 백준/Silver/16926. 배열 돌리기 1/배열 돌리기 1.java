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
	public static int[][] RCRevolving(int n, int m, int arr[][])
	{	//n,m 맵크기와 맵변수를 받는다.
		//맵 변수를 복사할 2차원 배열 
		int tmp[][] = new int[n][m];
		//인덱스의 시작점
		int i=0;
		//n-1-i와 m-1-i가 1이상이여야 반복 (행,열이 2이상이어야함)
		while(n-1-i>=1 && m-1-i>=1) {
			//인덱스 x,y i로 초기화
			int x=i;
			int y=i;
			//y가 바뀌는 차례인지 확인
			boolean yt=true;
			//인덱스 증가시킬 값
			int increase = 1;
			//반복문 몇번 순회할 지 계산(몇번 복사할지 계산 - 위쪽 테두리 아래쪽 테두리 , 왼쪽테두리 오른쪽 테두리)
			int num = (n-i)*2 + (m-i)*2;
			//반복문 num만큼 반복
			for(int k = 0 ; k<num; k++) {
				//y가 값이 바뀔 차례면
				if(yt) {
					//y의 값을 변화시킨 위치에 기존 배열의 값을 넣는다.
					tmp[y+increase][x]=arr[y][x];
					//y의 값을 증가
					y+=increase;
					//y가 인덱스 범위 끝에 도달하면 x가 값이 바뀔차례
					if(y>=n-1 || y<=i) {
						yt =false;
					}
				}else {//x가 값이 바뀔차례면
					//x의 값을 변화시킨 위치에 기존 배열의 값을 넣는다.
					tmp[y][x+increase]=arr[y][x];
					//x의 값 증가
					x+=increase;
					//x가 인덱스범위 끝에 달하면 y가 값이 바뀔차례
					if(x>=m-1 || x<=i) {
						yt =true;
						//increase값 -1을 곱해 증가 감소 변화
						increase= increase*-1;
					}
				}
			}
			//m과 n을 줄여 인덱스 번위를 줄인다. - 돌린 테두리 안쪽 배열 돌림
			//인덱스 i를 증가시킨다. - 돌린 테두리 안족 배열 돌림
			m--;
			n--;
			i++;
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
		//r번만큼 돌린다.
		for(int i=0;i <r;i++) {
			map = RCRevolving(n,m,map);
		}
		//돌린 결과 출력
		for(int i=0; i <n;i++) {
			for(int j=0;j<m;j++) {
				bw.append(map[i][j] + " ");
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
		br.close();

	}

}