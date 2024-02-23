import java.io.*;
import java.util.*;
/**
 * 문제: Main_15961_회전초밥
 * 결과: 맞았습니다!
 * 메모리:15961kb
 * 실행시간: 884ms
 * @author 서동인
 * 아이디어: 배열을 순회할 때 %배열크기를 써서 배열끝에 도달하면 다시 처음으로 순회하게끔 설정
 * 슬라이딩 윈도우 기법을 활용하여 문제를 풀었다. 
 * 
 */
public class Main {
	//회전초밥 배열
	static int sushi[];
	//각 초밥의 종류 중복됐는지 확인
	static boolean stype[];
	//먹을 수 있는 최대 종류수
	static int max=0;
	//먹은 것의 종류와 개수 맵
	static Map<Integer, Integer> eat;
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더, 스트링 토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//초밥벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken());	//초밥의 가짓수 
		int k = Integer.parseInt(st.nextToken());	//연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken());	//쿠폰번호
		//회전초밥 배열 초기화
		sushi = new int[n];
		//회전초밥 배열에 초밥배열 입력
		for (int i = 0; i < n; i++) {
			sushi[i]= Integer.parseInt(br.readLine());
		}
		//초밥 종류 수 만큼 크기로 초기화
		stype = new boolean[d+1];
		//먹은 초밥 종류수
		int cnt=0;
		//먹은 초밥 종류와 개수
		eat = new HashMap<Integer, Integer>();
		//맵의 종류와 개수 쌍 생성
		for(int i=1; i<d+1;i++) {
			eat.put(i, 0);
		}
		//처음 0~k-1까지 윈도우를 만든다.
		for(int j=0; j<k; j++) {
			//종류와 개수 갱신
			eat.put(sushi[j%n], eat.get(sushi[j%n])+1);
			//이미 먹은 거면 다음 순회
			if(stype[sushi[j%n]]) continue;
			//먹은 거 체크 후 cnt증가
			stype[sushi[j%n]] =true;
			cnt++;
		}
		//k의 윈도우를 가지고 슬라이딩 윈도우 실행
		for(int i=k; i<n+k;i++) {
			//왼쪽의 빠진 값을 맵에 갱신 해당 종류와 그 개수를 감소
			eat.put(sushi[(i-k)%n], eat.get(sushi[(i-k)%n])-1);
			//만약 왼쪽의 빠진 초밥의 개수가 0이 되었으면 윈도우에 그 초밥이 없다는 뜻이므로
			if(eat.get(sushi[(i-k)%n])==0) {
				//초밥종류 감소 후 해당 초밥 false
				cnt--;
				stype[sushi[(i-k)%n]] =false;
			}
			//오른쪽에 들어온 초밥의 종류와 개수를 증가
			eat.put(sushi[i%n], eat.get(sushi[i%n])+1);
			//들어온 초밥이 이미 먹었던 초밥이 아니라면
			if(!stype[sushi[i%n]]) {
				//해당 초밥 먹은 걸로 체크하고 초밥종류 증가
				stype[sushi[i%n]] =true;
				cnt++;
			}
			//쿠폰의 초밥을 먹었던 거면
			if(stype[c]) {
				//초밥 종류 그대로 하고 최대값 갱신
				max= Math.max(max, cnt);
			//쿠폰의 초밥 안먹었으면 초밥종류 +1로 최대값 갱신
			}else {
				max= Math.max(max, cnt+1);
			}
			
		}
		//결과 먹을 수 있는 최대종류 츨력
		System.out.println(max);
	}
}