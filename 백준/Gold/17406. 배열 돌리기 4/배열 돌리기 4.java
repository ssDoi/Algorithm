import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: Main_17406_배열 돌리기4
 * 결과: 맞았습니다!
 * 메모리:297784 kb
 * 실행시간: 1636ms
 * @author 서동인
 * 아이디어: k만큼의 수를 순열로 순서를 반복한다.
 *  
 */

public class Main {
	static int[][] orders;
	static int[] nums;
	static int map[][];
	static int min;
	//배열을 돌리는 메서드
	public static int[][] CRevolving(int startY, int startX,int endY,int endX, int arr[][])
	{	//n,m 맵크기와 맵변수를 받는다.
		//맵 변수를 복사할 2차원 배열 
		int tmp[] = new int[2];
		//인덱스의 시작점
		//n-1-i와 m-1-i가 1이상이여야 반복 (행,열이 2이상이어야함)
		while(endY-1-startY>=1 && endX-1-startX>=1) {
			//인덱스 x,y i로 초기화
			int x=startX;
			int y=startY;
			//y가 바뀌는 차례인지 확인
			boolean yt=false;
			//인덱스 증가시킬 값
			int increase = 1;
			//반복문 몇번 순회할 지 계산(몇번 복사할지 계산 - 위쪽 테두리 아래쪽 테두리 , 왼쪽테두리 오른쪽 테두리)
			int num = (endY-startY)*2 + (endX-startX)*2 -4;
			int ti=0;
			tmp[ti] = arr[y][x+1];
			arr[y][x+1] = arr[y][x];
			x++;
			ti++;
			//반복문 num만큼 반복
			for(int k = 1 ; k<num+1; k++) {
				//y가 값이 바뀔 차례
				if(yt) {
					//y의 값을 변화시킨 위치에 기존 배열의 값을 넣는다.
					tmp[ti%2]=arr[y+increase][x];
					arr[y+increase][x] = tmp[++ti%2]; 
					//y의 값을 증가
					
					y+=increase;
					//y가 인덱스 범위 끝에 도달하면 x가 값이 바뀔차례
					if(y>=endY-1 || y<=startY) {
						yt =false;
						increase= increase*-1;
					}
				}else {//x가 값이 바뀔차례면
					//x의 값을 변화시킨 위치에 기존 배열의 값을 넣는다.
					tmp[ti%2]=arr[y][x+increase];
					arr[y][x+increase]=tmp[++ti%2];
					//x의 값 증가
					x+=increase;
					//x가 인덱스범위 끝에 달하면 y가 값이 바뀔차례
					if(x>=endX-1 || x<=startX) {
						yt =true;
						//increase값 -1을 곱해 증가 감소 변화
					}
				}
			}
			//m과 n을 줄여 인덱스 번위를 줄인다. - 돌린 테두리 안쪽 배열 돌림
			//인덱스 i를 증가시킨다. - 돌린 테두리 안족 배열 돌림
			endX--;
			endY--;
			startX++;
			startY++;
		}
		return arr;
	}
	public static int getArrayValue(int tmp[][]) {
		int arrayValue=Integer.MAX_VALUE;
		int sum=0;
		for(int i=0; i<tmp.length;i++) {
			sum=0;
			for(int j=0; j<tmp[i].length;j++) {
				sum+=tmp[i][j];
			}
			arrayValue = Math.min(arrayValue,sum);
		}
		return arrayValue;
		
	
	}
	public static void permutation(boolean visited[],int k, int cnt) {
		if(k==cnt) {
			int tmp[][] = new int[map.length][map[0].length];
			for(int i=0; i <map.length; i++) {
				tmp[i] = map[i].clone();
			}
			for(int i=0;i<k;i++) {
				int r=orders[nums[i]][0];
				int c=orders[nums[i]][1];
				int s=orders[nums[i]][2];
				tmp =CRevolving(r-s-1,c-s-1,r+s,c+s,tmp);
			}
			int arrayValue = getArrayValue(tmp);
			min = Math.min(arrayValue, min);
			return;
		}
		for(int i=0; i <k ;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			nums[cnt]=i;
			permutation(visited,k,cnt+1);
			visited[i] =false;
		}
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
		int k = Integer.parseInt(st.nextToken());
		//맵변수 초기화
		map = new int[n][m];
		min = Integer.MAX_VALUE;
		//맵을 입력받는다.
		for(int i=0; i <n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		orders = new int[k][3];
		nums = new int[k];
		boolean visited[] = new boolean[k];
		//k번만큼 돌린다.
		for(int i=0;i <k;i++) {
			st = new StringTokenizer(br.readLine());
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
			orders[i][2] = Integer.parseInt(st.nextToken());
		}
		permutation(visited,k,0);
		
		System.out.println(min);
		//돌린 결과 출력
		/*for(int i=0; i <n;i++) {
			for(int j=0;j<m;j++) {
				bw.append(map[i][j] + " ");
			}
			bw.append("\n");
		}*/
		bw.flush();
		bw.close();
		br.close();

	}
	
	

}