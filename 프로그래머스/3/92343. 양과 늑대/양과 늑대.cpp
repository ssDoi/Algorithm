#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

int mxs;

void dfs(int n,int sheep, int wolf,set<int> nextNodes, vector<vector<int>>& adj, vector<int>& info )
{
    mxs=max(sheep,mxs);
    
    nextNodes.erase(n);
    for(int next : adj[n])
    {
        nextNodes.insert(next);
    }
    for(int next : nextNodes)
    {
        if(info[next] && wolf+1<sheep)
        {
            dfs(next,sheep,wolf+1,nextNodes,adj,info);
        }else if(info[next]==0)
        {
            dfs(next,sheep+1,wolf,nextNodes,adj,info);
        }
    }
}
int solution(vector<int> info, vector<vector<int>> edges) {
    int answer = 0;
    int n = info.size();
    mxs=-1;
    vector<vector<int>> adj(n);
    set<int> nextNodes;
    for(const vector<int>& e : edges)
    {
        adj[e[0]].push_back(e[1]);
    }
    dfs(0,1,0,nextNodes,adj,info);
    return mxs;
}