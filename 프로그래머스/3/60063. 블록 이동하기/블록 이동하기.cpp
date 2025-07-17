#include <string>
#include <vector>
#include <queue>
#include <set>
#include <tuple>
#include <iostream>

using namespace std;

// 좌표 유효성 검사
bool isValid(int y, int x, int n, vector<vector<int>>& board) {
    return y >= 0 && x >= 0 && y < n && x < n && board[y][x] == 0;
}

// 방문 처리를 위한 구조체 혹은 튜플 사용
struct Robot {
    int y1, x1, y2, x2, cost;
};

int solution(vector<vector<int>> board) {
    int n = board.size();

    // 중복 방문 방지를 위한 set: 좌표 쌍을 정렬해서 저장
    set<tuple<int, int, int, int>> visited;

    queue<Robot> q;
    q.push({0, 0, 0, 1, 0});  // 초기 위치 (0,0)-(0,1), cost = 0
    visited.insert({0, 0, 0, 1});

    // 4방향 이동을 위한 델타
    vector<int> dy = {0, 1, 0, -1};
    vector<int> dx = {1, 0, -1, 0};

    while (!q.empty()) {
        Robot cur = q.front(); q.pop();

        int y1 = cur.y1, x1 = cur.x1;
        int y2 = cur.y2, x2 = cur.x2;
        int c = cur.cost;

        // 도착했으면 종료
        if ((y1 == n - 1 && x1 == n - 1) || (y2 == n - 1 && x2 == n - 1))
            return c;

        // 평행 이동
        for (int i = 0; i < 4; i++) {
            int ny1 = y1 + dy[i], nx1 = x1 + dx[i];
            int ny2 = y2 + dy[i], nx2 = x2 + dx[i];

            if (!isValid(ny1, nx1, n, board) || !isValid(ny2, nx2, n, board)) continue;

            auto state = make_tuple(min(ny1, ny2), min(nx1, nx2), max(ny1, ny2), max(nx1, nx2));
            if (visited.count(state)) continue;

            visited.insert(state);
            q.push({ny1, nx1, ny2, nx2, c + 1});
        }

        // 회전
        bool isHorizontal = (y1 == y2);

        if (isHorizontal) {
            // 가로 → 세로 회전
            for (int d : {-1, 1}) {
                if (isValid(y1 + d, x1, n, board) && isValid(y2 + d, x2, n, board)) {
                    // x1 기준 회전
                    auto state1 = make_tuple(min(y1, y1 + d), min(x1, x1), max(y1, y1 + d), max(x1, x1));
                    if (!visited.count(state1)) {
                        visited.insert(state1);
                        q.push({y1, x1, y1 + d, x1, c + 1});
                    }
                    // x2 기준 회전
                    auto state2 = make_tuple(min(y2, y2 + d), min(x2, x2), max(y2, y2 + d), max(x2, x2));
                    if (!visited.count(state2)) {
                        visited.insert(state2);
                        q.push({y2, x2, y2 + d, x2, c + 1});
                    }
                }
            }
        } else {
            // 세로 → 가로 회전
            for (int d : {-1, 1}) {
                if (isValid(y1, x1 + d, n, board) && isValid(y2, x2 + d, n, board)) {
                    // y1 기준 회전
                    auto state1 = make_tuple(min(y1, y1), min(x1, x1 + d), max(y1, y1), max(x1, x1 + d));
                    if (!visited.count(state1)) {
                        visited.insert(state1);
                        q.push({y1, x1, y1, x1 + d, c + 1});
                    }
                    // y2 기준 회전
                    auto state2 = make_tuple(min(y2, y2), min(x2, x2 + d), max(y2, y2), max(x2, x2 + d));
                    if (!visited.count(state2)) {
                        visited.insert(state2);
                        q.push({y2, x2, y2, x2 + d, c + 1});
                    }
                }
            }
        }
    }

    return -1;  // 도달 불가능한 경우
}
