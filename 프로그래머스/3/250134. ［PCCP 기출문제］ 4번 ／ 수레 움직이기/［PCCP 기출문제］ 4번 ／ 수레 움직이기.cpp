#include <bits/stdc++.h>
using namespace std;

struct State {
    int rx, ry;                    // 빨간 수레 위치
    int bx, by;                    // 파란 수레 위치
    int turns;                     // 현재 턴 수
    bitset<16> redVisited;         // 빨간 수레가 방문한 칸 (4x4 -> 16칸 비트마스크)
    bitset<16> blueVisited;        // 파란 수레가 방문한 칸
};

int n, m;
int redDestX, redDestY, blueDestX, blueDestY;
vector<vector<int>> maze;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

// 비트마스크용 좌표 변환 함수
inline int pos(int x, int y) {
    return y * m + x;
}

// 방문 체크용 구조체 (위치와 방문비트셋 두 개 합쳐서 관리)
struct VisitState {
    int rx, ry, bx, by;
    bitset<16> redV;
    bitset<16> blueV;

    bool operator==(const VisitState& other) const {
        return rx == other.rx && ry == other.ry && bx == other.bx && by == other.by
            && redV == other.redV && blueV == other.blueV;
    }
};

struct VisitStateHasher {
    size_t operator()(const VisitState& s) const {
        size_t h1 = hash<int>()(s.rx) ^ hash<int>()(s.ry << 3);
        size_t h2 = hash<int>()(s.bx) ^ hash<int>()(s.by << 3);
        size_t h3 = hash<unsigned long long>()(s.redV.to_ullong());
        size_t h4 = hash<unsigned long long>()(s.blueV.to_ullong());
        return h1 ^ h2 ^ h3 ^ h4;
    }
};

bool inRange(int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n && maze[y][x] != 5;
}

int solution(vector<vector<int>> board) {
    maze = board;
    n = (int)maze.size();
    m = (int)maze[0].size();

    int redStartX, redStartY, blueStartX, blueStartY;

    // 시작 위치, 도착 위치 찾기
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (maze[y][x] == 1) { redStartX = x; redStartY = y; }
            else if (maze[y][x] == 2) { blueStartX = x; blueStartY = y; }
            else if (maze[y][x] == 3) { redDestX = x; redDestY = y; }
            else if (maze[y][x] == 4) { blueDestX = x; blueDestY = y; }
        }
    }

    queue<State> q;
    unordered_set<VisitState, VisitStateHasher> visited;

    State start;
    start.rx = redStartX; start.ry = redStartY;
    start.bx = blueStartX; start.by = blueStartY;
    start.turns = 0;
    start.redVisited.reset();
    start.blueVisited.reset();
    start.redVisited.set(pos(redStartX, redStartY));
    start.blueVisited.set(pos(blueStartX, blueStartY));

    q.push(start);

    VisitState vs{start.rx, start.ry, start.bx, start.by, start.redVisited, start.blueVisited};
    visited.insert(vs);

    while (!q.empty()) {
        State cur = q.front(); q.pop();

        // 도착 체크
        bool redAtDest = (cur.rx == redDestX && cur.ry == redDestY);
        bool blueAtDest = (cur.bx == blueDestX && cur.by == blueDestY);
        if (redAtDest && blueAtDest) {
            return cur.turns;
        }

        // 빨간 수레 이동 가능한 방향
        vector<pair<int,int>> redMoves;
        if (redAtDest) redMoves.push_back({cur.rx, cur.ry}); // 고정
        else {
            for (int i = 0; i < 4; i++) {
                int nx = cur.rx + dx[i], ny = cur.ry + dy[i];
                if (!inRange(nx, ny)) continue;
                if (cur.redVisited.test(pos(nx, ny))) continue;
                redMoves.push_back({nx, ny});
            }
        }

        // 파란 수레 이동 가능한 방향
        vector<pair<int,int>> blueMoves;
        if (blueAtDest) blueMoves.push_back({cur.bx, cur.by}); // 고정
        else {
            for (int i = 0; i < 4; i++) {
                int nx = cur.bx + dx[i], ny = cur.by + dy[i];
                if (!inRange(nx, ny)) continue;
                if (cur.blueVisited.test(pos(nx, ny))) continue;
                blueMoves.push_back({nx, ny});
            }
        }

        // 두 수레 이동 조합 모두 시도
        for (auto& rmove : redMoves) {
            for (auto& bmove : blueMoves) {
                int nrx = rmove.first, nry = rmove.second;
                int nbx = bmove.first, nby = bmove.second;

                // 같은 칸인지 체크
                if (nrx == nbx && nry == nby) continue;

                // 자리 바꾸는 경우 체크
                if (nrx == cur.bx && nry == cur.by && nbx == cur.rx && nby == cur.ry) continue;

                State nxt = cur;
                nxt.rx = nrx; nxt.ry = nry;
                nxt.bx = nbx; nxt.by = nby;
                nxt.turns = cur.turns + 1;
                nxt.redVisited.set(pos(nrx, nry));
                nxt.blueVisited.set(pos(nbx, nby));

                VisitState nxtVs{nxt.rx, nxt.ry, nxt.bx, nxt.by, nxt.redVisited, nxt.blueVisited};
                if (visited.count(nxtVs)) continue;
                visited.insert(nxtVs);
                q.push(nxt);
            }
        }
    }

    return 0; // 도달 불가
}
