import java.io.*;
import java.util.*;
/**
 * 문제:SWEA_5604_구간합
 * 결과:pass
 * 실행시간:113 ms
 * 메모리:23,908 kb
 * 아이디어:점화식 사용
 */
public class Solution {

	static HashMap<Long, Long> f;
	static long start, end, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T=Integer.parseInt(br.readLine());
		f = new HashMap<Long, Long>();
		long sum = 0;
		for(long i=0; i<10; i++) {
			sum += i;
			f.put(i, sum);
		}
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			start= Long.parseLong(st.nextToken());
			end= Long.parseLong(st.nextToken());

			if(start > 0)
				//start가 0보다 클경우 end - start-1 (자신포함)
				ans = F(end) - F(start-1);
			else 
				//start가 0보다 클경우 end - start (0이라 상관없음)
				ans = F(end) - F(start);
			
			System.out.println("#"+test_case+" "+ans);

		}

	}
	static long F(long i) {
		if(f.containsKey(i)) return f.get(i);
		if(i<10) return f.get(i);
		//자릿수
		long v = V(i);
		//자신의 최고 자릿수의 값-1 ex) 2345라면 2000-1 = 1999를 F에 넣음
		long F = F(i-1-i%v);
		//최고 자릿수의 값 * 최고 자릿수 미만의 나머지 값+1 ex) 2345라면 2 *345 +1(+1은 2000일 때 계산) + 최고 자릿수 미만의 나머지값 재귀
		long G = (i/v)*(i%v+1)+ F(i%v);
		long num = F+G;// 위의 둘을 더한 것이 결과
		//재사용을 위한 값 저장
		f.put(i, num);
		
		return num;
	}
	//자릿수 계산
	static long V(long i) {
		long v = 1;
		while(i>=10) {
			v = v*10;
			i = i/10;
		}
		return v;
	}
}