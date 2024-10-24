import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, 1, -1};
    static boolean[][][] visited;

    public static class Walk {
        int y;
        int x;
        int dist;
        int broke;

        Walk(int y, int x, int dist, int broke) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broke = broke;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][2];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        System.out.println(bfs());

    }

    static int bfs() {
        ArrayDeque<Walk> queue = new ArrayDeque<>();
        queue.offer(new Walk(0, 0, 1, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Walk cur = queue.poll();

            int y = cur.y;
            int x = cur.x;
            // 목표 지점에 도달했으면 거리를 반환
            if (y == n - 1 && x == m - 1) {
                return cur.dist;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (ny >= 0 && nx >= 0 && nx < m && ny < n) {
                    // 벽이 없는 곳으로 이동
                    if (map[ny][nx] == 0 && !visited[ny][nx][cur.broke]) {
                        queue.offer(new Walk(ny, nx, cur.dist + 1, cur.broke));
                        visited[ny][nx][cur.broke] = true;
                    } else if (map[ny][nx] == 1 && cur.broke == 0 && !visited[ny][nx][1]) {
                        visited[ny][nx][1] = true;
                        queue.offer(new Walk(ny, nx, cur.dist + 1, 1)); // 벽을 부순 상태로 이동
                    }
                }
            }
        }

        return -1;
    }
}