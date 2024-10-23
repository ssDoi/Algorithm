import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][] copyMap;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<int[]> blankList;
    static Queue<int[]> virusQueue;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        blankList = new ArrayList<>();
        virusQueue = new ArrayDeque<>();
        map = new int[n][m];
        max = 0;
        
        // 맵 입력 및 빈칸과 바이러스 위치 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    blankList.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    virusQueue.offer(new int[]{i, j});
                }
            }
        }
        
        // 벽을 세우기 위해 빈칸 3개 선택
        comb(0, 0);
        System.out.println(max);
    }

    // 조합으로 벽 세우기
    public static void comb(int start, int depth) {
        if (depth == 3) {
            // 복사 맵에서 벽을 세운 후 바이러스 퍼뜨리기
            copyMap = new int[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                copyMap[i] = map[i].clone();
            }

            bfs();
            max = Math.max(max, getSafeZone());
            return;
        }
        
        for (int i = start; i < blankList.size(); i++) {
            int[] pos = blankList.get(i);
            map[pos[0]][pos[1]] = 1; // 벽을 세움
            comb(i + 1, depth + 1);
            map[pos[0]][pos[1]] = 0; // 벽을 해제
        }
    }

    // BFS로 바이러스 퍼뜨리기
    public static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>(virusQueue);  // 초기 바이러스 위치 큐에 저장

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx >= 0 && ny >= 0 && ny < copyMap.length && nx < copyMap[0].length && copyMap[ny][nx] == 0) {
                    queue.offer(new int[]{ny, nx});
                    copyMap[ny][nx] = 2;  // 바이러스로 감염
                }
            }
        }
    }

    // 안전 영역 계산
    public static int getSafeZone() {
        int safeZone = 0;
        for (int[] row : copyMap) {
            for (int p : row) {
                if (p == 0) safeZone++;
            }
        }
        return safeZone;
    }
}