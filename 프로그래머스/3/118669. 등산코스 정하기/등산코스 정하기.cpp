#include <vector>
#include <queue>
#include <algorithm>
#include <limits>

using namespace std;

const int INF = 1e9;

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {
    vector<vector<pair<int, int>>> adj(n + 1);
    vector<bool> isSummit(n + 1, false);

    // 인접 리스트 구성
    for (int i = 0; i < paths.size(); ++i) {
        int u = paths[i][0], v = paths[i][1], w = paths[i][2];
        adj[u].push_back(make_pair(v, w));
        adj[v].push_back(make_pair(u, w));
    }

    // 산봉우리 체크
    for (int i = 0; i < summits.size(); ++i) {
        isSummit[summits[i]] = true;
    }

    // 최소 intensity 배열
    vector<int> intensity(n + 1, INF);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    // 여러 gate에서 출발
    for (int i = 0; i < gates.size(); ++i) {
        int gate = gates[i];
        intensity[gate] = 0;
        pq.push(make_pair(0, gate));
    }

    while (!pq.empty()) {
        pair<int, int> cur = pq.top();
        pq.pop();
        int curIntensity = cur.first;
        int u = cur.second;

        // 산봉우리에 도달한 경우 더 이상 진행하지 않음
        if (isSummit[u]) continue;

        for (int i = 0; i < adj[u].size(); ++i) {
            int v = adj[u][i].first;
            int w = adj[u][i].second;
            int nextIntensity = max(curIntensity, w);

            if (intensity[v] > nextIntensity) {
                intensity[v] = nextIntensity;
                pq.push(make_pair(nextIntensity, v));
            }
        }
    }

    // 가장 좋은 산봉우리 선택
    sort(summits.begin(), summits.end()); // 번호가 작은 것 우선
    int bestSummit = 0, minIntensity = INF;
    for (int i = 0; i < summits.size(); ++i) {
        int s = summits[i];
        if (intensity[s] < minIntensity) {
            minIntensity = intensity[s];
            bestSummit = s;
        }
    }

    return vector<int>{bestSummit, minIntensity};
}
