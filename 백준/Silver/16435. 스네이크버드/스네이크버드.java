import java.util.*;
import java.io.*;
/**
 * 문제: Main_16435_스네이크버드
 * 결과: 맞았습니다!
 * 메모리:11844kb
 * 실행시간: 84ms
 * @author 서동인
 * 아이디어: 과일의 높이 배열을 오름차순으로 정렬하고
 * 작은 것 부터 하나씩 l과 비교해서 l보다 작다면 l을 증가시키고 아니라면 반복문을 탈출했다.
 * 
 */
public class Main_16435_스네이크버드 {

	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링토크나이저
		StringTokenizer st  = new StringTokenizer(br.readLine());
		//과일의 수 n
		int n = Integer.parseInt(st.nextToken());
		//몸통 길이 l
		int l = Integer.parseInt(st.nextToken());
		//과일 높이 배열
		int fruits[] = new int[n];
		//스트링토크나이저 객체생성
		st  = new StringTokenizer(br.readLine());
		//과일 높이 배열 입력
		for(int i=0;i<n;i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		//높이배열 오름차순으로 저장
		Arrays.sort(fruits);
		//높이를 작은 것 부터 확인하여 l보다 작거나 같으면 l을 늘리고 아니라면 반복문 탈출
		for(int fruit : fruits) {
			if(fruit <= l) {
				l++;
			}else {
				break;
			}
		}
		//결과출력
		System.out.println(l);
		

	}

}
