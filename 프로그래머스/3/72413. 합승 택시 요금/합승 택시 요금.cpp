#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(int n, int s, int a, int b, vector<vector<int>> fares)
{
    vector<vector<pair<int,int>>> adj(n+1,vector<pair<int,int>>(n+1));
    int INF=10000000;
    vector<vector<int>> dp(n+1,vector<int>(n+1,INF));
    int answer = INF;
    for(const vector<int>& fare : fares)
    {
        dp[fare[0]][fare[1]] = fare[2];
        dp[fare[1]][fare[0]] = fare[2];
        adj[fare[0]].push_back({fare[1],fare[2]});
        adj[fare[1]].push_back({fare[0],fare[2]});
    }
    //거쳐가는 노드
    for(int k=1;k<=n;k++)
    {
        //출발노드
        for(int i=1;i<=n;i++)
        {
            //도착노드
            for(int j=1;j<=n;j++)
            {
                if(i==j) 
                {
                    dp[i][j]=0;
                    continue;
                }
                if(dp[i][k]+dp[k][j] < dp[i][j])
                {
                    dp[i][j] = dp[i][k]+dp[k][j];
                }
            }
        }
    }
    for(int i=1;i<=n;i++)
    {
        answer = min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
    }


    
    return answer;
}