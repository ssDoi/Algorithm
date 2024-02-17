import java.util.*;
import java.io.*;
/**
 * 문제:Main_2447_별찍기10
 * 결과:pass
 * 실행시간:134ms
 * 메모리:19828kb
 * 아이디어: 재귀를 사용
 * 
 */
public class Main {

	
	static char map[][];
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		map = new char[n][n];
		for(int i=0; i <n;i++) {
			for(int j=0; j<n;j++) {
				map[i][j] = ' ';
			}
		}
		star(0,0,n);
		for(int i=0; i <n;i++) {
			for(int j=0; j<n;j++) {
				bw.append(map[i][j]);
			}
			bw.append("\n");
		}
		bw.flush();
		sc.close();
		bw.close();
	}
	public static void star(int y,int x,int n) {
		if(n ==1) {
			map[y][x] ='*';
			return;
		}
		if(n==0) {
			return;
		}
		star(y,x,n/3);
		star(y,x+ n/3,n/3);
		star(y,x+n/3*2,n/3);
		star(y+n/3,x,n/3);
		star(y+n/3,x+n/3 *2,n/3);
		star(y+n/3*2,x,n/3);
		star(y+n/3*2,x+n/3,n/3);
		star(y+n/3*2,x+n/3 *2,n/3);
		
	}
}