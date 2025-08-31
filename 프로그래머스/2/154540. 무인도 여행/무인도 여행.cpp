#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<string> maps) {
    vector<int> answer;
    int n = maps.size();
    int m = maps[0].size();
    vector<vector<bool>> visited(n, vector<bool>(m,false));
    vector<int> dy = {1,-1,0,0};
    vector<int> dx = {0,0,1,-1};
    
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            if(maps[i][j]=='X') continue;
            if(visited[i][j]) continue;
            queue<pair<int,int>> q;
            q.push({i,j});
            visited[i][j] = true;
            int cost =0;
            cost+=maps[i][j]-'0';
            while(!q.empty())
            {
                int y = q.front().first;
                int x = q.front().second;
                q.pop();
                for(int k=0;k<4;k++)
                {
                    int ny= y+dy[k];
                    int nx =x+dx[k];
                    if(ny>=0 && nx>=0 && ny<n && nx<m && !visited[ny][nx] && maps[ny][nx] !='X')
                    {
                        visited[ny][nx] = true;
                        cost+=maps[ny][nx]-'0';
                        q.push({ny,nx});
                        
                    }
                }
            }
            answer.push_back(cost);
            
        }
    }
    cout<<visited[n-1][m-1];
    sort(answer.begin(),answer.end());
    if(answer.empty()) return {-1};
    return answer;
}