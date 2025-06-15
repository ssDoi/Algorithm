#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;


int solution(int alp, int cop, vector<vector<int>> problems) {
    
    pair<int,int> target = {alp,cop};
    for(const vector<int>& p : problems)
    {

        target.first= max(target.first,p[0]);
        target.second= max(target.second,p[1]);
    }
    vector<vector<int>> dp(target.first+2, vector<int>(target.second+2,1000000));
    dp[alp][cop] =0;
    
    for(int i=alp;i<=target.first; i++)
    {
        for(int j=cop; j<=target.second; j++)
        {
            if(dp[i][j]==1000000) continue;
            
            dp[i+1][j] = min(dp[i][j]+1, dp[i+1][j]);
            dp[i][j+1] = min(dp[i][j]+1, dp[i][j+1]);
            
            for(vector<int>& p : problems)
            {
                if(i<p[0] || j<p[1]) continue;
                int ni=min(i+p[2],target.first);
                int nj=min(j+p[3],target.second);
                dp[ni][nj] = min(dp[i][j]+p[4], dp[ni][nj]);
            }
        }
    }
    
    return dp[target.first][target.second];
}