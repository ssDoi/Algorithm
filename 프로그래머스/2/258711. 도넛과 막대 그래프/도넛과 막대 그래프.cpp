#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<vector<int>> edges) {
    int n=0;

    // 노드 개수 파악
    for(const auto& e: edges) {
        n = max({n, e[0], e[1]});
    }

    vector<vector<int>> next(n+1);
    vector<vector<int>> prev(n+1);

    for(const auto& e: edges) {
        next[e[0]].push_back(e[1]);
        prev[e[1]].push_back(e[0]);
    }

    int ins = -1;  // 끼어든 정점
    int d=0, m=0, d8=0;

    // 끼어든 정점 찾기
    for (int i=1; i<=n; i++) {
        if (prev[i].empty() && next[i].size() >= 2) {
            ins = i;
            break;
        }
    }

    vector<bool> visited(n+1, false);

    // ins의 자식 노드들만 시작점으로 탐색
    for (int start : next[ins]) {
        if (visited[start]) continue;

        bool round = false; // 도넛 여부
        bool is8 = false;   // 8자 여부

        queue<int> q;
        q.push(start);
        visited[start] = true;

        while(!q.empty()) {
            int cur = q.front(); q.pop();

            if (next[cur].empty()) { 
                // 막대형
                m++;
                round = false; // 순환 아님
                break;
            }

            if (next[cur].size() == 2) {
                is8 = true;
            }

            for (int ne : next[cur]) {
                if (!visited[ne]) {
                    visited[ne] = true;
                    q.push(ne);
                } else {
                    // 시작점으로 되돌아온 경우 -> 순환
                    if (ne == start) round = true;
                }
            }
        }

        if (round) {
            if (is8) d8++;
            else d++;
        }
    }

    return {ins, d, m, d8};
}
