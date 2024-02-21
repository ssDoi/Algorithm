import java.io.*;
import java.util.*;


/**
 * 문제: Main_13023_ABCDE
 * 결과: 맞았습니다!
 * 메모리:18904kb
 * 실행시간: 264ms
 * @author 서동인
 * 아이디어: 카메라의 종류와 인덱스를 리스트에 저장한다.
 * 중복 순열을 사용하여 각 카메라의 방향을 설정한다.
 * 카메라의 방향마다 맵의 상황을 산출하여 0개수를 세서 그 중 최소값을 얻어내 출력한다.
 * 
 */
public class Main {

	static ArrayList<Character> jaList;
	static ArrayList<Character> moList;
	static ArrayList<Character> list;
	static int L,C;
	static HashSet<String> set = new HashSet<>(); 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		jaList = new ArrayList<>();
		moList = new ArrayList<>();
		list = new ArrayList<>();
		String[] tmp = br.readLine().split(" ");
		for(int i=0; i<C;i++) {
			char w = tmp[i].charAt(0);
			list.add(w);
			if(w == 'a' || w == 'e' || w == 'i' || w == 'o' || w == 'u'){
					moList.add(w);
			}else {
				jaList.add(w);
			}
		}
		comb1(0,2,new ArrayList<Character>(),new boolean[jaList.size()]);
		ArrayList<String> answer = new ArrayList<>();
		
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			answer.add(iter.next()+"");
		}
		Collections.sort(answer);
		for(String s: answer) {
			System.out.println(s);
		}
		
	}
	public static void comb1(int depth,int r,ArrayList<Character> arr ,boolean visited[]) {
		if(r==0) {
			for(int i=0 ;i<visited.length;i++) {
				if(visited[i]) {
					arr.add(jaList.get(i));
				}
			}
			comb2(0,1,arr,new boolean[moList.size()]);
			arr.clear();
			return;
		}
		if(depth == visited.length) {
			return;
		}
		visited[depth] = true;
		comb1(depth+1,r-1,arr,visited);
		visited[depth] = false;
		comb1(depth+1,r,arr,visited);
	}
	public static void comb2(int depth,int r,ArrayList<Character> arr ,boolean visited[]) {
		if(r==0) {
			ArrayList<Character> arr1 = new ArrayList<>();
			for(int i=0 ;i<visited.length;i++) {
				if(visited[i]) {
					arr1.add(moList.get(i));
				}
			}
			for(int i=0 ;i<arr.size();i++) {
				arr1.add(arr.get(i));
			}
			if(L-3==0) {
				Collections.sort(arr1);
				String st ="";
				for(char c : arr1) {
					st+=c;
				}
				set.add(st);
			}else {
				ArrayList<Character> restList= new ArrayList<Character>();
				for(int i=0 ;i<list.size();i++) {
					if(arr1.contains(list.get(i))) {
						continue;
					}
					restList.add(list.get(i));
				}
				comb3(0,L-3,arr1,new boolean[restList.size()],restList);
			}
			return;
		}
		if(depth == visited.length) {
			return;
		}
		visited[depth] = true;
		comb2(depth+1,r-1,arr,visited);
		visited[depth] = false;
		comb2(depth+1,r,arr,visited);
	}
	public static void comb3(int depth,int r,ArrayList<Character> arr ,boolean visited[], ArrayList<Character> restList) {
		if(r==0) {
			ArrayList<Character> arr1 = new ArrayList<>();
			for(int i=0 ;i<visited.length;i++) {
				if(visited[i]) {
					arr1.add(restList.get(i));
				}
			}
			for(int i=0 ;i<arr.size();i++) {
				arr1.add(arr.get(i));
			}
			Collections.sort(arr1);
			String st ="";
			for(char c : arr1) {
				st+=c;
			}
			set.add(st);
			return;
		}
		if(depth == visited.length) {
			return;
		}
		visited[depth] = true;
		comb3(depth+1,r-1,arr,visited, restList);
		visited[depth] = false;
		comb3(depth+1,r,arr,visited,restList);
	}

}