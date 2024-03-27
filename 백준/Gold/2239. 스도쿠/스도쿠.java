import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int map[][] = new int[9][9];
		boolean visited[][] = new boolean[9][9];
		for(int i=0 ; i<9;i++)
		{
			char[] row = br.readLine().toCharArray(); 
			for(int j=0 ; j<9 ;j++) {
				map[i][j] = row[j]-'0';
				if(map[i][j]!=0) {
					visited[i][j] = true;
				}
			}
		}
		check(map,visited);
		
		

	}
	public static void check(int[][] map ,boolean visited[][]) {
		boolean flag = false;
		int y = 0;
		int x = 0;
		Loop:
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(!visited[i][j]) {
					y = i;
					x = j;
					flag=true;
					break Loop;
				}
			}
		}
		if(!flag) {
			for(int r[] : map) {
				for(int num : r) {
					System.out.print(num);
				}
				System.out.println();
			}
			System.exit(0);
			return;
		}
		int hBit = 0;
		int vBit = 0;
		int aBit = 0;
		for(int i= 0 ;i<9; i++) {
			hBit = hBit|(1<<map[y][i]);
			vBit = vBit|(1<<map[i][x]);
			
		}
		for(int i=0; i<3;i++) {
			for(int j=0;j<3;j++) {
				aBit = aBit|(1<<map[y/3 *3 +i][x/3 *3 +j]);
			}
		}
		int dBit = hBit | vBit | aBit;
		ArrayList<Integer> v = new ArrayList<>();
		for(int i=1;i<=9;i++) {
			if((dBit&(1<<i)) ==0) {
				v.add(i);
			}
		}
		for(int i=0;i<v.size();i++) {
			map[y][x] = v.get(i);
			visited[y][x]= true;
			check(map,visited);
			map[y][x] = 0;
			visited[y][x]= false;
		}
		
	}

}