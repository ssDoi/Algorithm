
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: Main_17406_배열 돌리기4
 * 결과: 맞았습니다!
 * 메모리:32548 kb
 * 실행시간: 236ms
 * @author 서동인
 * 아이디어: 0~k-1만큼의 수를 순열로 만든다.
 * 해당 순열을 인덱스로하여 돌리는 명령을 만들어진 순열대로 돌린다.
 * 돌려진 맵을 가지고 맵값을 산출한다.
 * 산출한 맵값으로 맵값의 최소를 갱신한다.
 *  
 */

public class Main {
	//명령어집합
	static int[][] orders;
	//순열
	static int[] nums;
	//입력받는 2차원 배열 맵
	static int map[][];
	//출력할 배열값의 최소
	static int min;
	//배열을 돌리는 메서드
	public static int[][] CRevolving(int startY, int startX,int endY,int endX, int arr[][])
	{	//시작인덱스 끝인덱스와 2차원 배열을 받는다.
		//맵을 돌리기 위해 맵값을 일시적으로 저장할 tmp변수 
		int tmp[] = new int[2];
		//인덱스의 시작점
		//n-1-i와 m-1-i가 1이상이여야 반복 (행,열이 2이상이어야함)
		while(endY-1-startY>=1 && endX-1-startX>=1) {
			//인덱스 x,y startX,startY로 초기화
			int x=startX;
			int y=startY;
			//y가 바뀌는 차례인지 확인
			boolean yt=false;
			//인덱스 증가시킬 값
			int increase = 1;
			//반복문 몇번 순회할 지 계산(몇번 복사할지 계산 - 위쪽 테두리 아래쪽 테두리 , 왼쪽테두리 오른쪽 테두리)
			int num = (endY-startY)*2 + (endX-startX)*2 -4;
			//ti는 tmp의 인덱스로 사용
			int ti=0;
			//tmp에 한칸 앞 값 저장
			tmp[ti] = arr[y][x+1];
			//한칸앞 값에 지금 값 저장
			arr[y][x+1] = arr[y][x];
			//x와 ti를 증가
			x++;
			ti++;
			//반복문 1~ num만큼 반복
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
			//end인덱스를 줄이고 start인덱스를 높혀 범위를 축소한다.
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
		if(k==cnt) {//순열 재귀가 끝나면
			//맵을 복사할 2차원 배열
			int tmp[][] = new int[map.length][map[0].length];
			//2차월 배열에 맵 복사
			for(int i=0; i <map.length; i++) {
				tmp[i] = map[i].clone();
			}
			//순열 순서대로 돌리는 명령어 실행
			for(int i=0;i<k;i++) {
				int r=orders[nums[i]][0];
				int c=orders[nums[i]][1];
				int s=orders[nums[i]][2];
				tmp =CRevolving(r-s-1,c-s-1,r+s,c+s,tmp);
			}
			//배열값을 받는다.
			int arrayValue = getArrayValue(tmp);
			//배열값으로 최소값 갱신
			min = Math.min(arrayValue, min);
			//반환
			return;
		}
		
		//순열 반복문
		for(int i=0; i <k ;i++) {
			//이미 방문된 값이면 다음순회
			if(visited[i]) continue;
			//값을 순열에 넣고
			visited[i] = true;
			//해당 위치에 i를 넣음
			nums[cnt]=i;
			//다음 재귀호출
			permutation(visited,k,cnt+1);
			//값을 순열에 안넣을 경우로 바꾸고 다음순회
			visited[i] =false;
		}
	}
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader  br =new BufferedReader(new InputStreamReader(System.in));
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
		//명령어를 저장할 배열
		orders = new int[k][3];
		//순열을 저장할 배열
		nums = new int[k];
		//순열을 돌릴 때 방문했는지 확인하는 배열
		boolean visited[] = new boolean[k];
		//명령어를 배열에 저장
		for(int i=0;i <k;i++) {
			st = new StringTokenizer(br.readLine());
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
			orders[i][2] = Integer.parseInt(st.nextToken());
		}
		//순열 시작
		permutation(visited,k,0);
		//순열 끝나면 최소값 출력
		System.out.println(min);
		br.close();

	}
	
	

}
