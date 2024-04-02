import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
문제: BJ_1780_종이의 개수
메모리: 321232KB
결과: 맞았습니다!
시간: 928ms
접근방식:
재귀를 사용하여 접근하였다
board를 입력받고 입력받은 board의 원위치의 값을 검사자로 사용하였다.
검사자와 순회범위의 값이 모두 같다면 검사자의 수에 따라 종이의 개수를 증가시킨다.
*/
class Main {

	public static int fibo (int n) {
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		return	fibo(n-1) + fibo(n-2); 
	}
		
	
	public static void main(String[] args) throws IOException {
		//사용자 입력을 받기위한 변수
		InputStreamReader in = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader(in);
		//사용자 입력을 받아 토큰화하여 변수를 저장한다
		StringTokenizer st =new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		System.out.println(fibo(n));


	}

}