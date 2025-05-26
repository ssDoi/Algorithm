#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
unordered_map<int,pair<int,int>> pos = {
    {1,{0,0}},{2,{0,1}},{3,{0,2}},{4,{1,0}},{5,{1,1}},{6,{1,2}},{7,{2,0}},{8,{2,1}},{9,{2,2}},{0,{3,1}}
};
int dist[10][10];

void checkDist()
{
    for(int start=0;start<10;start++)
    {
        vector<int> cost(10, INT_MAX);
        queue<pair<int, int>> q;
        q.push({start, 0});
        cost[start] = 0;
        while(!q.empty())
        {
            int cur = q.front().first;
            int c = q.front().second;
            q.pop();
            pair<int,int> p = pos[cur];
            for(int drow =-1 ; drow<=1 ; drow++)
                for(int dcol = -1 ; dcol <=1; dcol++)
                {
                    if(drow==0 && dcol==0) continue;
                    int nr = p.first +drow;
                    int nc = p.second +dcol;
                    for(auto& [next,np] : pos)
                    {
                        if(np.first==nr && np.second ==nc)
                        {
                            int new_cost = c+ (((abs(dcol)+abs(drow)) ==2 ) ? 3 : 2);                   
                            if(new_cost<cost[next])
                            {
                                cost[next] = new_cost;
                                q.push({next, new_cost});
                            }
                        }
                    }
                }
        }
        cost[start] = 1;
        for(int i=0;i<=9;i++) dist[start][i] = cost[i];
    }
    
}
int solution(string numbers) {
    checkDist();
    int n= numbers.size();
    vector<vector<vector<int>>> dp(n+1,vector<vector<int>>(10,vector<int>(10,INT_MAX)));
    dp[0][4][6] =0;
    for(int i=0;i<n;i++)
    {
        int target = numbers[i] -'0';
        for(int l=0;l<=9;l++)
            for(int r=0;r<=9;r++)
            {
                if(dp[i][l][r] ==INT_MAX || l==r) continue;
                
                if(target != r)
                {
                    int cost = (l==target) ? 1 :dist[l][target];
                    dp[i+1][target][r] =min(dp[i+1][target][r], dp[i][l][r] +cost);
                }
                if(target != l)
                {
                    int cost = (r==target) ? 1 : dist[r][target];
                    dp[i+1][l][target] = min(dp[i+1][l][target],dp[i][l][r] +cost);
                }
            }
    }
    int answer = INT_MAX;
    for(int i=0;i<10;i++)
        for(int j=0;j<10;j++)
        {
            answer = min(answer,dp[n][i][j]);
        }
    return answer;
}