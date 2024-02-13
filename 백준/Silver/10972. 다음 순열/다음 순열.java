import java.util.*;
import java.io.*;
/**
 * 문제: Main_10972_다음순열
 * 결과: 맞았습니다!
 * 메모리:27216 kb
 * 실행시간: 7566ms
 * @author 서동인
 * 아이디어: next permutation을 사용하여 사전순으로 순열을 리스트에 집어넣는다
 * 리스트는 String을 입력으로 받고 arr를 통해 순열을 만들면 이를 문자열로 변환하여 리스트에 입력
 * next permutation으로 리스트에 입력된 순열의 인덱스를 참조하여 입력받은 순열이 있는지 확인
 * 있다면 그 인덱스 다음의 인덱스를 참조하여 출력, 그 다음의 인덱스 값이 size보다 크다면 -1 출력
 */
public class Main {

	//순열을 저장할 리스트
	static ArrayList<String> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//순열의 크기 n
		int n = Integer.parseInt(br.readLine());
		//순열을 저장할 배열
		int[] arr = new int[n];
		//1~n만큼 배열에 넣는다.
		String[] tmp = br.readLine().split(" ");
		for(int i=0; i<n;i++) {
			arr[i] = Integer.parseInt(tmp[i]);
		}
		
		if(np(arr)) {
			for(int num : arr) {
				bw.append(num + " ");
			}
		}else
		{
			bw.append(-1+"");
		}
		bw.flush();
		
	}
	public static boolean np(int[] arr) {
		//N읜 배열의 크기
		final int N = arr.length;
		//i, 꼭대기의 위치 배열의 끝값으로 초기화
		int i = N-1;
		//꼭대기값 찾을 때 까지 i를 왼쪽으로 이동시키며 찾는다.
		//꼭대기값 자신의 앞의 숫자가 더 작은 경우가 나타나면 꼭대기값
		while(i>0 && arr[i-1] >=arr[i]) --i;
		if(i==0) return false;	//i가 0이라면 순열이 내림차순으로 되어있는 경우이므로 false반환
		
		//바꿀 위치 j, 배열의 끝값으로 초기화
		int j = N-1;
		//바꿀 위치 i-1보다 큰 최소한의 수가 될 때가지 왼쪽으로 이동
		while(arr[i-1] >= arr[j]) --j;
		//두 수를 바꿈
		swap(arr,i-1,j);
		
		//오름차순을 위한 K
		int k= N-1;
		//꼭대기 이후는 내림차순이므로 꼭대기와 끝값을 하나씩 바꾸면서 오름차순 정리
		while(i<k) swap(arr,i++,k--);
		//true 반환
		return true;
	}
	//배열과 바꿀 위치를 받아 서로 값을 바꿔주는 메서드
	public static void swap(int[] arr,int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}