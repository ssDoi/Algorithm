#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<string> maps) {
    int answer = 0;
    vector<int> dx = {1,-1,0,0};
    vector<int> dy = {0,0,1,-1};
    int n=maps.size();
    int m=maps[0].size();
    vector<int> start;
    vector<int> lever;
    vector<int> escape;
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            if(maps[i][j]=='S')
            {
                start={i,j};
            }else if(maps[i][j]=='E')
            {
                escape={i,j};
            }else if(maps[i][j]=='L')
            {
                lever={i,j};
            }
        }
    }
    vector<vector<bool>> visited(n,vector<bool>(m,false));
    queue<vector<int>> q;
    visited[start[0]][start[1]]=true;
    q.push({start[0],start[1],0});
    int cnt =0;
    bool cango=false;
    while(!q.empty()&& !cango)
    {
        
        int y = q.front()[0];
        int x = q.front()[1];
        int cost = q.front()[2];
        if(y==lever[0] &&  x==lever[1])
        {
            cango=true;
            cnt=cost;
            break;
        }
        q.pop();
        

        for(int i=0;i<4;i++)
        {
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(ny>=0 && nx>=0 && ny<n && nx<m && !visited[ny][nx] && maps[ny][nx] !='X')
            {
                visited[ny][nx]=true;
                q.push({ny,nx,cost+1});

            }
        }
        
        
    }
    if(!cango) return -1;
    for(int i=0;i<n;i++)
    {
        fill(visited[i].begin(),visited[i].end(),false);
    }
    visited[lever[0]][lever[1]]=true;
    while(!q.empty()) q.pop();
    q.push({lever[0],lever[1],cnt});
    
    cango=false;
    while(!q.empty()&& !cango)
    {
        
        int y = q.front()[0];
        int x = q.front()[1];
        int cost = q.front()[2];
        if(y==escape[0] && x==escape[1])
        {
            cango=true;
            cnt=cost;
            break;
        }
        q.pop();
        

        for(int i=0;i<4;i++)
        {
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(ny>=0 && nx>=0 && ny<n && nx<m && !visited[ny][nx] && maps[ny][nx] !='X')
            {
                visited[ny][nx]=true;
                q.push({ny,nx,cost+1});

            }
        }
        
        
    }
    if(!cango) return -1;
    return cnt;
}