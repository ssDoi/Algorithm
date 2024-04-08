import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int SIZE = 10;
    static int[] paper = {0, 5, 5, 5, 5, 5}; // 사용 가능한 색종이 개수
    static int[][] board = new int[SIZE][SIZE];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        
        // 정답 출력
        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
    }

    // (x, y) 위치부터 탐색하며, 색종이를 붙이거나 다음 위치로 이동하는 함수
    static void dfs(int x, int y, int cnt) {
        // 모든 위치를 다 확인했을 때 최소값 갱신
        if (x >= SIZE) {
            answer = Math.min(answer, cnt);
            return;
        }

        // 이미 확인한 위치는 스킵
        if (board[x][y] == 0) {
            if (y + 1 < SIZE) dfs(x, y + 1, cnt); // 다음 열로 이동
            else dfs(x + 1, 0, cnt); // 다음 행의 첫 열로 이동
            return;
        }

        // 색종이를 붙여보기
        for (int k = 5; k >= 1; k--) {
            if (paper[k] == 0 || x + k > SIZE || y + k > SIZE) continue;
            if (canCover(x, y, k)) { // 색종이를 붙일 수 있는지 확인
                cover(x, y, k, 0); // 색종이 붙이기
                paper[k]--;
                if (y + k < SIZE) dfs(x, y + k, cnt + 1); // 다음 열로 이동
                else dfs(x + 1, 0, cnt + 1); // 다음 행의 첫 열로 이동
                cover(x, y, k, 1); // 색종이 제거하기 (백트래킹)
                paper[k]++;
            }
        }
    }

    // (x, y) 위치에서 크기가 size인 색종이를 붙일 수 있는지 확인하는 함수
    static boolean canCover(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    // (x, y) 위치에서 크기가 size인 색종이를 붙이거나 제거하는 함수
    static void cover(int x, int y, int size, int value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = value;
            }
        }
    }
}