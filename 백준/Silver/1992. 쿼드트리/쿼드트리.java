import java.io.*;
import java.util.*;


public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static char map[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		generateAnswer(0,0,n);
		bw.flush();

	}
	public static void generateAnswer(int startX, int startY, int n) throws IOException {
		char firstValue = map[startY][startX];
		boolean isOneColor = true;
		Loop:
		for(int i=startY; i < startY+n;i++) {
			for(int j = startX; j<startX+n;j++) {
				if(firstValue != map[i][j]) {
					isOneColor = false;
					break Loop;
				}
			}
		}
		if(isOneColor) {
			bw.append(firstValue);
		}else {
			bw.append("(");
			generateAnswer(startX,startY,n/2);
			generateAnswer(startX + n/2,startY,n/2);
			generateAnswer(startX,startY + n/2,n/2);
			generateAnswer(startX + n/2,startY + n/2,n/2);
			bw.append(")");
		}
		
	}
	

}