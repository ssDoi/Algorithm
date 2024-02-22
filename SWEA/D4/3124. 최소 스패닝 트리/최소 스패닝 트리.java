import java.io.*;
import java.util.*;
/**
 * 문제: D4_3124_최소스패닝트리
 * 결과: pass
 * 메모리:113,652kb
 * 실행시간: 1923ms
 * @author 서동인
 * 아이디어: 유니온 파인드를 이용한 크루스칼 알고리즘으로 문제를 해결
 * 
 */
public class Solution {

	//부모 배열
	static int[] p;
	//간선 배열
	static Edge[] edgeList;
	//간선 클래스
	public static class Edge implements Comparable<Edge>{
		//from, to, 가중치 정보
		int from;
		int to;
		int weight;
		//초기화 생성자
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			//가중치 기준으로 정렬
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더, 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//테스트케이스 수
		int T;
		T=Integer.parseInt(br.readLine());
		//T만큼 반복
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st =new StringTokenizer(br.readLine());
			//정점 수
			int V = Integer.parseInt(st.nextToken());
			//간선 수
			int E = Integer.parseInt(st.nextToken());
			//간선 배열 초기화
			edgeList = new Edge[E];
			//간선 배열에 저장
			for(int i=0; i<E;i++) {
				st =new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from,to,weight);
			}
			//간선 오름차순 정리
			Arrays.sort(edgeList);
			//부모배열 초가화
			p = new int[V+1];
			//부모배열에 자기자신을 저장
			for(int i=1; i<=V;i++) {
				p[i] =i;
			}
			//가중치 합
			long sum=0;
			//
			for(int i=0; i<E;i++) {
				//간선의 정점을 유니온 하여 서로 같은 집합이 아니라면 유니온하고
				if(!union(edgeList[i].from, edgeList[i].to)) continue;
				//가중치 누적
				sum+=edgeList[i].weight;
			}
			//결과출력
			System.out.println("#" + test_case + " " + sum);

		} 

	}
	//대표자 찾기
	public static int find(int a) {
		if(a == p[a]) {
			return a;
		}
		//경로 압축
		return p[a] = find(p[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a); 
		int bRoot = find(b);
		//같은 집합이면 false
		if(aRoot==bRoot) {
			return false;
		}
		//b의 대표자의 부모 배열에 a대표자를 넣음
		p[bRoot] = aRoot;
		//true 반환
		return true;
	}

}