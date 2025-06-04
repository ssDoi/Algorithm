#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

void dfs (int node , vector<vector<int>>& dp, vector<vector<int>>& adj, vector<bool>& visited)
{
    visited[node] = true;
    dp[node][0] = 0;
    dp[node][1] = 1;
    for(int v : adj[node])
    {
        if(!visited[v]){
            dfs(v,dp,adj,visited);
            dp[node][0] +=dp[v][1];
            dp[node][1] +=min(dp[v][0], dp[v][1]);
        }
    }
    
    
}
int solution(int n, vector<vector<int>> lighthouse) {
    vector<vector<int>> dp(n+1, vector<int>(2,0));
    vector<vector<int>> adj(n+1);
    vector<bool> visited(n+1, false);
    for(const vector<int>& v : lighthouse)
    {
        adj[v[0]].push_back(v[1]);
        adj[v[1]].push_back(v[0]);
    }
    
    dfs(1, dp, adj,visited);
    return min(dp[1][0],dp[1][1]);
}