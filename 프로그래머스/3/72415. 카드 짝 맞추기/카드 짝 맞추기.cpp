#include <string>
#include <vector>
#include <queue>
#include <set>
#include <algorithm>
#include <climits>
using namespace std;

vector<int> dr = {1, -1, 0, 0};
vector<int> dc = {0, 0, 1, -1};

int bfs(vector<vector<int>>& board, int sr, int sc, int target, int& er, int& ec) {
    vector<vector<bool>> visited(4, vector<bool>(4, false));
    queue<tuple<int, int, int>> q;
    q.push({sr, sc, 0});
    visited[sr][sc] = true;

    while (!q.empty()) {
        auto [r, c, dist] = q.front(); q.pop();
        if (board[r][c] == target) {
            er = r;
            ec = c;
            return dist + 1; // 카드 선택 포함
        }

        // 일반 이동
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr >= 0 && nc >= 0 && nr < 4 && nc < 4 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                q.push({nr, nc, dist + 1});
            }
        }

        // Ctrl 이동
        for (int i = 0; i < 4; i++) {
            int nr = r, nc = c;
            while (true) {
                int tr = nr + dr[i], tc = nc + dc[i];
                if (tr < 0 || tc < 0 || tr >= 4 || tc >= 4) break;
                nr = tr;
                nc = tc;
                if (board[nr][nc] != 0) break;
            }
            if (!visited[nr][nc]) {
                visited[nr][nc] = true;
                q.push({nr, nc, dist + 1});
            }
        }
    }
    return INT_MAX; // 못 찾은 경우
}

int solution(vector<vector<int>> board, int r, int c) {
    int answer = INT_MAX;
    set<int> cardSet;
    for (auto& row : board)
        for (int card : row)
            if (card != 0) cardSet.insert(card);

    vector<int> cards(cardSet.begin(), cardSet.end());

    do {
        vector<vector<int>> temp = board;
        int cnt = 0;
        int cr = r, cc = c;

        for (int target : cards) {
            // 첫 번째 카드 찾기
            int nr1, nc1;
            int d1 = bfs(temp, cr, cc, target, nr1, nc1);
            temp[nr1][nc1] = 0;

            // 두 번째 카드 찾기
            int nr2, nc2;
            int d2 = bfs(temp, nr1, nc1, target, nr2, nc2);
            temp[nr2][nc2] = 0;

            cnt += d1 + d2;
            cr = nr2;
            cc = nc2;
        }
        answer = min(answer, cnt);
    } while (next_permutation(cards.begin(), cards.end()));

    return answer;
}
