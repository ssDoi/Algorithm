import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드 리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int T = Integer.parseInt(br.readLine());
			st =new StringTokenizer(br.readLine());
			Queue<Integer> password = new ArrayDeque<>();
			for (int i = 0; i < 8; i++) {
				password.offer(Integer.parseInt(st.nextToken()));
			}
			boolean isValid = true;
			while(isValid) {
				for(int i=1; i <=5; i++) {
					if(password.peek() - i >0) {
						password.offer(password.poll()-i);
					}else {
						password.poll();
						password.offer(0);
						isValid = false;
						break;
					}
				}
			}
			System.out.print("#" +test_case+ " ");
			for(int pass : password ) {
				System.out.print(pass+ " ");
			}
			System.out.println();
		}
		

	}

}