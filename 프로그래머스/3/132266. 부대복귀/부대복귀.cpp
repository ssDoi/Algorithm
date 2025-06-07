#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<int> answer;
    vector<vector<int>> adj(n+1);
    
    for(int i=0;i<roads.size();i++)
    {
        adj[roads[i][0]].push_back(roads[i][1]);
        adj[roads[i][1]].push_back(roads[i][0]);
    }
    vector<int> dist(n+1,100001);
    dist[destination] = 0;
    vector<int> visited(n+1,false);
    int minx = 100001;
    queue<pair<int,int>> q;
    visited[destination]=true;
    q.push({destination,0});
    while(!q.empty())
    {
        int cur = q.front().first;
        int cnt = q.front().second;
        q.pop();
        for(int an : adj[cur])
        {
            if(dist[an]>cnt+1 && !visited[an])
            {
                dist[an] = cnt+1;
                q.push({an,cnt+1});
                visited[an]=true;
            }
        }
    }
        
    
    for(int a : sources)
    {
        if(dist[a] ==100001)
        {
            answer.push_back(-1);
        }else
        {
            answer.push_back(dist[a]);
        }
    }

    return answer;
}