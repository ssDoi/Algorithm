#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
vector<vector<int>> checkOutLine(vector<string>& exs)
{
    vector<int> dx={1,-1,0,0};
    vector<int> dy={0,0,1,-1};
    vector<vector<int>> outliner;
    queue<vector<int>> q;
    int n = exs.size();
    int m = exs[0].size();
    vector<vector<bool>> visited(n, vector<bool>(m,false));
    q.push({0,0});
    visited[0][0]=true;
    while(!q.empty())
    {
        int y=q.front()[0];
        int x=q.front()[1];
        q.pop();
        for(int i=0;i<4;i++)
        {
            int ny = y+dy[i];
            int nx = x+dx[i];
            
            if(ny<n&& nx<m&& nx>=0 && ny>=0 && !visited[ny][nx])
            {
                visited[ny][nx] = true;
                if(exs[ny][nx]==' ')
                {
                    q.push({ny,nx});
                }else
                {
                    outliner.push_back({ny,nx});
                }
            }
        }
    }
    return outliner;
  
}
int solution(vector<string> storage, vector<string> requests) {
    int answer = 0;
    //외곽을 가져오기
    int n = storage.size();
    int m = storage[0].size();
    int cnt= n*m;
    string s = " ";
    for(int i=0;i<m;i++)
    {
        s+=" ";
    }
    s+=" ";
    vector<string> exs(n+2,s);
    for(int i=0;i<n;i++)
    {
        exs[i+1]=" " +storage[i] + " ";
    }
    vector<vector<int>> outliner = checkOutLine(exs);
    
    for(string r: requests)
    {
        if(r.size()==1)
        {
            for(vector<int>& out : outliner)
            {
                if(exs[out[0]][out[1]]==r[0])
                {
                    exs[out[0]][out[1]] = ' ';
                    cnt--;
                }
            }
            //외곽에서 찾아서 비우기
        }
        else
        {
            //전체에서 찾아서 비우기
            for(int i=1;i<=n;i++)
            {
                for(int j=1;j<=m;j++)
                {
                    if(exs[i][j]==r[0])
                    {
                        exs[i][j] =' ';
                        cnt--;
                    }
                }
            }
        }
        //외곽갱신
        outliner = checkOutLine(exs);
    }
    return cnt;
}