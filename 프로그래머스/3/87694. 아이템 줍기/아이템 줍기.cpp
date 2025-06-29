#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 1e9;
    //배화해야됨
    int mx = 0;
    int my = 0;
    vector<int> dy = {1,-1,0,0};
    vector<int> dx = {0,0,1,-1};
    for(vector<int>& rect : rectangle)
    {
        mx = max(mx,rect[0]);
        mx = max(mx,rect[2]);
        my = max(my,rect[1]);
        my = max(my,rect[3]);
    }
        
    vector<vector<int>> paper((my+1) *2,vector<int>((mx+1) *2,0));
    vector<vector<bool>> visited((my+1) *2,vector<bool>((mx+1) *2,false));
     for(vector<int>& rect : rectangle)
    {
        for(int i=rect[1]*2;i<=rect[3]*2;i++) 
        {
            for(int j=rect[0]*2;j<=rect[2]*2;j++)
            {
                paper[i][j]=1;
            }
        }
    }
    for(vector<int>& rect : rectangle)
    {
        for(int i=rect[1]*2+1;i<=rect[3]*2-1;i++) 
        {
            for(int j=rect[0]*2+1;j<=rect[2]*2-1;j++)
            {
                paper[i][j]=0;
            }
        }
    }
    queue<pair<int,int>> q;
    
    q.push({characterX*2,characterY*2});
    visited[characterY*2][characterX*2] = true;
    int cnt=0;
    while(!q.empty())
    {
            int qsize=q.size();
            for(int k=0;k<qsize;k++)
            {
            int x=q.front().first;
            int y=q.front().second;
            q.pop();
            for(int i=0;i<4;i++)
            {
                int nx= x + dx[i];
                int ny= y + dy[i];
                if(nx>=0 && ny>=0 && nx<paper[0].size() && ny<paper.size()&& paper[ny][nx] && !visited[ny][nx])
                {
                    visited[ny][nx] = true;
                    if(ny==itemY*2 && nx ==itemX*2)
                    {
                        answer=min(cnt+1,answer);
                    }
                    else
                    {
                        q.push({nx,ny});
                    }
                    
                }
            }
        }
        cnt++;
    }
    
    
    return answer/2;
                              
}