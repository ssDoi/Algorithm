#include <vector>
#include <algorithm>
using namespace std;

int n, m;
vector<int> dr = { -1, 1, 0, 0 };
vector<int> dc = { 0, 0, -1, 1 };

pair<bool, int> dfs(vector<vector<int>>& board, int r1, int c1, int r2, int c2) {
    if (board[r1][c1] == 0) return { false, 0 }; // 현재 플레이어가 발판 없는 곳이면 패배

    bool canWin = false;
    int minWinTurn = 1e9;
    int maxLoseTurn = 0;

    for (int i = 0; i < 4; i++) {
        int nr = r1 + dr[i];
        int nc = c1 + dc[i];

        if (nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) continue;

        board[r1][c1] = 0; // 현재 위치 발판 제거
        auto [opponentWin, turnCount] = dfs(board, r2, c2, nr, nc); // 턴 넘기기
        board[r1][c1] = 1; // 백트래킹

        if (!opponentWin) {
            canWin = true;
            minWinTurn = min(minWinTurn, turnCount + 1);
        } else {
            maxLoseTurn = max(maxLoseTurn, turnCount + 1);
        }
    }

    if (canWin) return { true, minWinTurn };
    else return { false, maxLoseTurn };
}

int solution(vector<vector<int>> board, vector<int> aloc, vector<int> bloc) {
    n = board.size();
    m = board[0].size();
    auto [canWin, totalTurn] = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]);
    return totalTurn;
}
