#include <vector>
#include <cmath>
#include <iostream>
using namespace std;

vector<vector<int>> adj;
vector<bool> visited;
vector<long long> w;
long long answer = 0;

long long dfs(int node) {
    visited[node] = true;
    for (int next : adj[node]) {
        if (!visited[next]) {
            w[node] += dfs(next);
        }
    }
    answer += abs(w[node]);
    return w[node];
}

long long solution(vector<int> a, vector<vector<int>> edges) {
    long long sum = 0;
    int n = a.size();
    w = vector<long long>(a.begin(), a.end());
    adj = vector<vector<int>>(n);
    visited = vector<bool>(n, false);

    for (int i = 0; i < n; ++i) sum += w[i];
    if (sum != 0) return -1;

    for (auto& edge : edges) {
        adj[edge[0]].push_back(edge[1]);
        adj[edge[1]].push_back(edge[0]);
    }

    dfs(0);  // 임의의 루트에서 시작

    return answer;
}
