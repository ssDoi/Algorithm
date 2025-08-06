#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> nodes, vector<vector<int>> edges) {
    int forward=0;
    int reverse=0;
    map<int,vector<int>> forest;
    map<int,bool> visited;
    for(int n : nodes)
    {
        visited[n]=false;
    }
    for(const vector<int>& e : edges)
    {
        forest[e[0]].push_back(e[1]);
        forest[e[1]].push_back(e[0]);
    }
    
    for(int n : nodes)
    {
        if(visited[n]) continue;
        queue<int> q;
        visited[n]=true;
        q.push(n);
        int f=0;
        int r=0;
        while(!q.empty())
        {
            int cur =q.front();
            q.pop();
            if(cur%2 == forest[cur].size()%2) f++;
            else if(cur%2 != forest[cur].size()%2) r++;
            for(int next : forest[cur])
            {
                if(!visited[next])
                {
                    visited[next]=true;
                    q.push(next);
                }
            }
            
        }
        
        if(f==1)
        {
            forward++;
        }
        if(r==1)
        {
            reverse++;        
        }
        
    }

    
    return {forward,reverse};
}