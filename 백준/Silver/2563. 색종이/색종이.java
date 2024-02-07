import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제: BJ_10798_세로읽기
메모리: 11500KB
결과: 맞았습니다!
시간: 80ms
접근방식:
기본적으로 이중반복문을 사용하여 세로읽기를 실행했다.
char타입의 이차배열을 만들고 문자열을 CharArray로 바꿔서 집어 넣었다
세로읽기 시 해당 짧은 행의 인덱스를 인식하고 인식하면 continue를 통해 반복문을 진행시켰다.
*/
class Main {

	public static void main(String[] args) throws IOException {
		//사용자 입력을 받기위한 변수
		InputStreamReader in = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader(in);
		StringTokenizer st =new StringTokenizer(br.readLine());
		int tc = Integer.valueOf(st.nextToken());
		int[][] board = new int[100][100];
		int[][] position  =new int[tc][2];
		int space = 0;
		for(int i = 0 ; i< tc; i++)
		{
			st = new StringTokenizer(br.readLine());
			position[i][0] = Integer.valueOf(st.nextToken());
			position[i][1] = Integer.valueOf(st.nextToken());
		}
		for(int i = 0 ; i< tc; i++)
		{
			for(int j = position[i][0] ; j< position[i][0] +10; j++)
			{
				for(int k =  position[i][1] ; k< position[i][1]+10;k++)
				{
					board[j][k] = 1;
				}
			}
			
		}
		for(int i =0; i<100; i++)
		{
			for(int j=0;j<100; j++)
			{
				space += board[i][j];
			}
		}
		System.out.println(space);
	
	}

}