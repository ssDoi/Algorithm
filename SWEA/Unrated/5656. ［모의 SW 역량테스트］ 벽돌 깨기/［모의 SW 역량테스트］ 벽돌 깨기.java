import java.io.*;
import java.util.*;

/**
 * 문제: SWEA_5656_벽돌깨기 결과: 맞았습니다 메모리:102,412kb 실행시간: 376ms
 * 
 * @author 서동인 아이디어: 완전탐색을 활용하여 문제를 풀었다. 왼쪽부터 끝까지 제일 위에 있는 벽돌을 터트리며 재귀를 돌렸다.
 */
public class Solution {

	// 벽돌의 최소 수
	static int min;

	// 벽돌 객체
	static class block {
		// 벽돌 위치 x, y
		int x;
		int y;
		// 벽돌의 점수
		int level;

		block(int a, int b, int c) {
			x = a;
			y = b;
			level = c;
		}

		@Override
		public String toString() {
			return "block [x=" + x + ", y=" + y + ", level=" + level + "]";
		}

	}

	// 맵의 크기 W, H
	static int W, H;
	// 4방탐색을 위한 dx,dy
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		// 입력을 위한 버퍼드리더 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 테스트케이스 횟수
		int T = Integer.parseInt(br.readLine());
		// 테스트 케이스 횟수만큼 테스트를 한다.
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			// 구슬쏘는 횟 수
			int N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			// 최소 블록 수 초기화
			min = Integer.MAX_VALUE;
			// 맵 입력
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 완전탐색 시작
			dfs(N, map);
			// 결과출력
			System.out.println("#" + test_case + " " + min);

		}
	}

	public static void dfs(int n, int[][] map) {
		// 구슬 다 쐈으면
		if (n == 0) {
			int cnt = 0;
			// 벽돌 개수를 세서
			for (int row[] : map) {
				for (int b : row) {
					if (b != 0) {
						cnt++;
					}
				}
			}
			// 최소 갱신
			min = Math.min(min, cnt);
			return;
		}
		// 왼쪽부터 끝까지 하나씩 반복
		for (int i = 0; i < W; i++) {
			int tmp[][] = new int[H][W];
			// 기존 맵 복사
			for (int j = 0; j < H; j++) {
				tmp[j] = map[j].clone();
			}
			// 터질 블록넣을 큐
			Queue<block> blockList = new ArrayDeque<>();
			// 제일 위의 블록 찾기
			for (int j = 0; j < H; j++) {
				if (tmp[j][i] != 0) {
					blockList.offer(new block(i, j, tmp[j][i]));
					tmp[j][i] = 0;
					break;
				}
			}
			// 블록이 있으면 블록터지는 로직
			if (!blockList.isEmpty()) {
				tmp = boom(blockList, tmp);
			}
			// 다음 구슬쏘는 횟수 줄이고 탐색 순회
			dfs(n - 1, tmp);
		}
	}

	public static int[][] boom(Queue<block> blockList, int[][] map) {
		// 터질 블록이 있으면
		while (!blockList.isEmpty()) {
			block b = blockList.poll();
			// 터질 블록 레벨만큼 4방의 블록을 큐에 넣음
			for (int i = 1; i < b.level; i++) {
				for (int j = 0; j < 4; j++) {
					int ny = b.y + dy[j] * i;
					int nx = b.x + dx[j] * i;
					if (ny >= 0 && nx >= 0 && nx < W && ny < H && map[ny][nx] != 0) {
						blockList.offer(new block(nx, ny, map[ny][nx]));
						// 블록터지면 0을 넣음
						map[ny][nx] = 0;
					}
				}
			}
		}
		// 벽돌을 아래로 내리는 로직
		for (int i = 0; i < W; i++) {
			//아래에서부터 블록을 확인해서 저장할 큐
			Queue<Integer> sort = new ArrayDeque<>();
			//아래에서 부터 블록을 큐에 저장하고 해당 블록을 지운다.
			for (int j = H - 1; j >= 0; j--) {
				if (map[j][i] != 0) {
					sort.offer(map[j][i]);
					map[j][i] = 0;
				}
			}
			//아래에서부터 블록을 쌓는다.
			for (int j = H - 1; !sort.isEmpty(); j--) {
				map[j][i] = sort.poll();
			}

		}
		//로직을 완료한 map을 리턴
		return map;
	}

}