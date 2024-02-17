import java.io.*;
import java.util.*;
/**
 * 문제:D3_상호의배틀필드
 * 결과:pass
 * 실행시간:134ms
 * 메모리:19828kb
 * 아이디어: 구현 문제
 * 맵을 입력받을 때 탱크의 위치도 받아 저장한다
 * 명령문을 받아 해당 명령문에 따라 탱크의 위치와 모습을 바꾼다
 * 포탄을 쏘는 명령은 탱크 위치에서 탱크의 방향에 따라 반복문으로 맵을 순회하여
 * 벽을 부딛힐 경우 벽의 종류에 따라 평지로 만든다
 */

public class Solution {

	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더, 출력을 위한 버퍼드 라이터
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//테스트케이스 수
		int T;
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//맵 크기 입력받아 저장
			String tmp[] = br.readLine().split(" ");
			int H = Integer.parseInt(tmp[0]);
			int W = Integer.parseInt(tmp[1]);
			//맵 초기화
			char map[][] = new char[H][W];
			//탱크의 위치 y, x
			int posY = 0;
			int posX = 0;
			//맵입력받아 저장
			for(int i=0; i <H;i++) {
				map[i] = br.readLine().toCharArray();
				//탱크의 초기 위치 저장
				for(int k=0; k<W;k++) {
					if(map[i][k] == '^' || map[i][k] == 'v' || map[i][k] == '<'|| map[i][k] == '>') {
						posY = i;
						posX = k;
					}
				}
			}
			//명령의 수
			int n = Integer.parseInt(br.readLine());
			//명령문 저장
			char[] orders = br.readLine().toCharArray();
			//명령의 수만큼 반복
			for(int i=0; i<n;i++) {
				//명령에 따라 다른 행위 실행
				switch(orders[i]) {
				//UP
				case 'U':
					//탱크를 위쪽모습으로 바꿈
					map[posY][posX] = '^';
					//인덱스 범위에 있고 위가 평지일 경우 이동
					if(posY-1>=0 &&map[posY-1][posX] =='.') {
						map[posY][posX] = '.';
						map[--posY][posX] = '^';
					}
					break;
				//DOWN
				case 'D':
					//탱크를 아래쪽모습으로 바꿈
					map[posY][posX] = 'v';
					//인덱스 범위에 있고 아래가 평지일 경우 이동
					if(posY+1<H &&map[posY+1][posX] =='.') {
						map[posY][posX] = '.';
						map[++posY][posX] = 'v';
					}
					break;
				//LEFT
				case 'L':
					//탱크를 왼쪽 모습으로 바꿈
					map[posY][posX] = '<';
					//인덱스 범위에 있고 왼쪽이 평지일 경우이동
					if(posX-1>=0 &&map[posY][posX-1] =='.') {
						map[posY][posX] = '.';
						map[posY][--posX] = '<';
					}
					break;
				//RIGHT
				case 'R':
					//탱크를 오른쪽 모습으로 바꿈
					map[posY][posX] = '>';
					//인덱스 범위에 있고 오른쪽이 평지일 경우이동
					if(posX+1<W &&map[posY][posX+1] =='.') {
						map[posY][posX] = '.';
						map[posY][++posX] = '>';
					}
					break;
				//SHOOT
				case 'S':
					//탱크의방향에 따라 포탄의 방향 결정
					switch (map[posY][posX]) {
					//위
					case '^':
						//위쪽방향으로 맵 순회 
						for(int k=posY; k >=0; k--) {
							//순회시 벽돌벽이라면 평지로 바꾸고 반복문 탈출
							if(map[k][posX] =='*') {
								map[k][posX] ='.';
								break;
							//강철벽이면 안바꾸고 반복문 탈출
							}else if(map[k][posX] =='#') {
								break;
							}
						}
						break;
					//아래
					case 'v':
						for(int k=posY; k <H; k++) {
							//순회시 벽돌벽이라면 평지로 바꾸고 반복문 탈출
							if(map[k][posX] =='*') {
								map[k][posX] ='.';
								break;
							//강철벽이면 안바꾸고 반복문 탈출
							}else if(map[k][posX] =='#') {
								break;
							}
						}
						break;
					//왼쪽
					case '<':
						for(int k=posX; k >=0; k--) {
							//순회시 벽돌벽이라면 평지로 바꾸고 반복문 탈출
							if(map[posY][k] =='*') {
								map[posY][k] ='.';
								break;
							//강철벽이면 안바꾸고 반복문 탈출	
							}else if(map[posY][k] =='#') {
								break;
							}
						}
						break;
					//오른쪽
					case '>':
						for(int k=posX; k <W; k++) {
							//순회시 벽돌벽이라면 평지로 바꾸고 반복문 탈출
							if(map[posY][k] =='*') {
								map[posY][k] ='.';
								break;
							//강철벽이면 안바꾸고 반복문 탈출
							}else if(map[posY][k] =='#') {
								break;
							}
						}
						break;
				}
				}
			}
			//결과맵 출력
			bw.append("#"+ test_case + " ");
			for(int i=0; i<H;i++) {
				for(int k=0; k<W;k++) {
					bw.append(map[i][k]);
				}
				bw.append("\n");
			}
			bw.flush();
		}

	}

}