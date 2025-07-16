#include <string>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>
using namespace std;

int solution(vector<vector<int>> board) {
    int n = board.size();
    vector<vector<vector<int>>> cost(2, vector<vector<int>>(n, vector<int>(n, 1e9)));

    queue<tuple<int, int, int, int>> q; // x, y, 방향(0:세로,1:가로), 비용

    // 시작 방향: 오른쪽(가로) or 아래(세로)
    if (board[0][1] == 0) {
        q.push({1, 0, 1, 100});
        cost[1][0][1] = 100;
    }
    if (board[1][0] == 0) {
        q.push({0, 1, 0, 100});
        cost[0][1][0] = 100;
    }

    int dx[4] = {1, -1, 0, 0};
    int dy[4] = {0, 0, 1, -1};

    while (!q.empty()) {
        auto [x, y, dir, c] = q.front(); q.pop();

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int ndir = (i <= 1) ? 1 : 0; // 0: 세로, 1: 가로

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (board[ny][nx] == 1) continue;

            int nc = (dir == ndir) ? c + 100 : c + 600;
            if (cost[ndir][ny][nx] > nc) {
                cost[ndir][ny][nx] = nc;
                q.push({nx, ny, ndir, nc});
            }
        }
    }

    return min(cost[0][n-1][n-1], cost[1][n-1][n-1]);
}
