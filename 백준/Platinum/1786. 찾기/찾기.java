import java.io.*;
import java.util.*;

/**
 * 문제: Main_1786_찾기 
 * 결과: 맞았습니다 
 * 메모리:11868kb 
 * 실행시간: 88ms
 * @author 서동인 
 * 아이디어:
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		// 부분일치 테이블 배열 작성
		int pi[] = new int[P.length()];
		for (int i = 1; i < P.length(); i++) {
		    int j = pi[i - 1];
		    while (j > 0 && P.charAt(i) != P.charAt(j)) {
		        j = pi[j - 1];
		    }
		    if (P.charAt(i) == P.charAt(j)) {
		        pi[i] = j + 1;
		    }
		}
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		int i = 0;
		int j = 0;
        while (i < T.length()) {
            if (T.charAt(i) == P.charAt(j)) {
                i++;
                j++;
                if (j == P.length()) {
                    cnt++;
                    list.add(i - j +1);
                    j = pi[j - 1]; // 패턴 매칭 성공한 경우, 실패 함수를 이용하여 j 값을 업데이트
                }
            } else {
                if (j != 0) {
                    j = pi[j - 1]; // 패턴 매칭 실패한 경우, 실패 함수를 이용하여 j 값을 업데이트
                } else {
                    i++;
                }
            }
        }
		System.out.println(cnt);
		for(int n : list) {
			System.out.print(n+ " ");
		}

	}

}