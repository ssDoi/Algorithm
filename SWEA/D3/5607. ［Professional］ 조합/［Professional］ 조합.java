import java.io.*;
import java.util.*;
/**
 * 문제: SWEA_8458_원점으로집합
 * 결과: 맞았습니다 
 * 메모리:37,928kb 
 * 실행시간: 324ms
 * @author 서동인 
 * 아이디어: 모든 좌표의 맨해튼 거리가 모두 짝수이거나 홀수여야 조건을 만족한다. 제일 먼 좌표의 거리보다 1~i를 모두 더한 값이 더 크고 좌표의 거리가
 * 짝수 일 경우 짝수, 홀수일 경우 홀수여야한다.
 */
public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] tmp = br.readLine().split(" ");
			int n = Integer.parseInt(tmp[0]);
			int r = Integer.parseInt(tmp[1]);
			
			System.out.println("#"+ test_case + " " + combination(n,r,1234567891));

		}


	}
    // 페르마의 소정리를 사용하여 역원을 구하는 메서드
    public static long Inverse(long a, int p) {
        long result = 1;
        while (a > 1) {
            result = (result * (p - p / a)) % p;
            a = p % a;
        }
        return result;
    }

    // 조합의 경우의 수를 구하는 메서드
    public static long combination(int n, int k, int p) {
        long[] fac = new long[n+1];
        fac[1] = 1;
        for(int i=2;i<n+1;i++) {
        	fac[i] = fac[i-1] *i %p;
        }
        long inverse = Inverse((fac[k] * fac[n-k])%p, p);
        return (fac[n] * inverse) % p;
    }

}