#include <string>
#include <vector>
#include <bits/stdc++.h>


using namespace std;
int solution(vector<vector<int>> info, int n, int m) {
    

    bool pass=true;
    vector<vector<vector<bool>>> dp(info.size()+1,vector<vector<bool>>(n,vector<bool>(m,false)));
    dp[0][0][0] = true;
    for(int i=1;i<=info.size();i++)
    {
        for(int j=0;j<n;j++)
        {
            for(int k=0;k<m;k++)
            {
                if(dp[i-1][j][k])
                {
                    if(j+info[i-1][0] < n)
                    {
                        dp[i][j+info[i-1][0]][k]=true;
                    }
                    if(k+info[i-1][1] < m)
                    {
                        dp[i][j][k+info[i-1][1]]=true;
                    }
                }
            }
        }
    }
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            if(dp[info.size()][i][j])
            {
                return i;
            }
        }
    }
    return -1;
}