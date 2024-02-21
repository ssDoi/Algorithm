import java.io.*;
import java.util.*;


/**
 * 문제: Main_1759_암호만들기
 * 결과: 맞았습니다!
 * 메모리:36052kb
 * 실행시간: 256ms
 * @author 서동인
 * 아이디어: 자음을 2개 선택, 모음을  1개 선택, 이후 나머지 중에서 L-3만큼 선택
 * 선택한 것을 sort한 후 String화 시켜 set에 저장 set를 arrayList에 저장하고 sort
 * sort한 것을 출력
 * 
 */
public class Main_1759_암호만들기_서동인 {

	//자음리스트
	static ArrayList<Character> jaList;
	//모음리스트
	static ArrayList<Character> moList;
	//전체리스트
	static ArrayList<Character> list;
	static int L,C;
	//중복제거를 위한 set
	static HashSet<String> set = new HashSet<>(); 
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//암호문 길이, 문자개수 저장
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//전역변수 초기화
		jaList = new ArrayList<>();
		moList = new ArrayList<>();
		list = new ArrayList<>();
		//문자입력을 저장하기 위한 tmp
		String[] tmp = br.readLine().split(" ");
		//문자 리스트에 저장
		for(int i=0; i<C;i++) {
			//해당문자
			char w = tmp[i].charAt(0);
			//전체리스트에 저장
			list.add(w);
			//모음이면 저장
			if(w == 'a' || w == 'e' || w == 'i' || w == 'o' || w == 'u'){
					moList.add(w);
			}else {	//자음이면 저장
				jaList.add(w);
			}
		}
		//자음 중 2개 선택
		comb1(0,2,new ArrayList<Character>(),new boolean[jaList.size()]);
		//답 리스트
		ArrayList<String> answer = new ArrayList<>();
		//set순회를 위한 이터레이터
		Iterator iter = set.iterator();
		//set순회
		while(iter.hasNext()) {
			//set의 내용물 answer에 옮김
			answer.add(iter.next()+"");
		}
		//answer 정렬
		Collections.sort(answer);
		//답 출력
		for(String s: answer) {
			System.out.println(s);
		}
		
	}
	public static void comb1(int depth,int r,ArrayList<Character> arr ,boolean visited[]) {
		//다 뽑았으면 arr에 선택된 것을 저장
		if(r==0) {
			for(int i=0 ;i<visited.length;i++) {
				if(visited[i]) {
					arr.add(jaList.get(i));
				}
			}
			comb2(0,1,arr,new boolean[moList.size()]);
			//다음 선택지를 위해 arrclear
			arr.clear();
			return;
		}
		//끝까지 순회했다면 리턴
		if(depth == visited.length) {
			return;
		}
		//선택된 경우
		visited[depth] = true;
		comb1(depth+1,r-1,arr,visited);
		//선택되지 않은 경우
		visited[depth] = false;
		comb1(depth+1,r,arr,visited);
	}
	public static void comb2(int depth,int r,ArrayList<Character> arr ,boolean visited[]) {
		//다 뽑았으면 arr에 선택된 것을 저장
		if(r==0) {
			//이번 조합을 저장할 리스트
			ArrayList<Character> arr1 = new ArrayList<>();
			//선택된 것을 리스트에 저장
			for(int i=0 ;i<visited.length;i++) {
				if(visited[i]) {
					arr1.add(moList.get(i));
				}
			}
			//이전에 뽑았던 것도 리스트에 저장
			for(int i=0 ;i<arr.size();i++) {
				arr1.add(arr.get(i));
			}
			//암호문 길이가 3이면 sort한 후 스틀이화 시켜 set에 저장
			if(L-3==0) {
				Collections.sort(arr1);
				String st ="";
				for(char c : arr1) {
					st+=c;
				}
				set.add(st);
			}else {//아니라면
				//선택된 것을 제외한 나머지 리스트
				ArrayList<Character> restList= new ArrayList<Character>();
				//선택된 것이 아닌 나머지 문자를 나머지리스트에 저장
				for(int i=0 ;i<list.size();i++) {
					if(arr1.contains(list.get(i))) {
						continue;
					}
					restList.add(list.get(i));
				}
				//나머지 리스트 중에서 L-3만큼 뽑음
				comb3(0,L-3,arr1,new boolean[restList.size()],restList);
			}
			return;
		}
		//끝까지 순회했다면 리턴
		if(depth == visited.length) {
			return;
		}
		//선택된 경우
		visited[depth] = true;
		comb2(depth+1,r-1,arr,visited);
		//선택되지 않은 경우
		visited[depth] = false;
		comb2(depth+1,r,arr,visited);
	}
	public static void comb3(int depth,int r,ArrayList<Character> arr ,boolean visited[], ArrayList<Character> restList) {
		//다 뽑았으면 set에 선택된 것을 저장
		if(r==0) {
			//이번에 선택된 문자리스트
			ArrayList<Character> arr1 = new ArrayList<>();
			//선택된 것을 리스트에 저장
			for(int i=0 ;i<visited.length;i++) {
				if(visited[i]) {
					arr1.add(restList.get(i));
				}
			}
			//이전에 뽑은 것을 리스트에 저장
			for(int i=0 ;i<arr.size();i++) {
				arr1.add(arr.get(i));
			}
			//뽑은 문자를 사전순으로 정리
			Collections.sort(arr1);
			//뽑은 문자를 스트링화 시킴
			String st ="";
			for(char c : arr1) {
				st+=c;
			}
			//set에 저장
			set.add(st);
			return;
		}
		//다 순회하면 리턴
		if(depth == visited.length) {
			return;
		}
		//선택된 경우
		visited[depth] = true;
		comb3(depth+1,r-1,arr,visited, restList);
		//선택되지 않은 경우
		visited[depth] = false;
		comb3(depth+1,r,arr,visited,restList);
	}

}
