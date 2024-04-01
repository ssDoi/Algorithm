import java.io.*;
import java.util.*;

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
    // 팩토리얼을 구하는 메서드
    public static long factorial(int n, int p) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % p;
        }
        return result;
    }

    // 페르마의 소정리를 사용하여 역원을 구하는 메서드
    public static long modularInverse(long a, int p) {
        long result = 1;
        while (a > 1) {
            result = (result * (p - p / a)) % p;
            a = p % a;
        }
        return result;
    }

    // 조합의 경우의 수를 구하는 메서드
    public static long combination(int n, int k, int p) {
        long numerator = factorial(n, p);
        long denominator = (factorial(k, p) * factorial(n - k, p)) % p;
        long inverseDenominator = modularInverse(denominator, p);
        return (numerator * inverseDenominator) % p;
    }

}