#include <vector>
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int MOD = 20170805;

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int m, int n, vector<vector<int>> city_map) {
    int answer = 0;
    vector<vector<vector<int>>> dp(2,vector<vector<int>>(m,vector<int>(n,0)));
    //0위에서 내려온 거
    //1왼쪽에서 온 거
    dp[0][0][0] =1;
    
    
    
    for(int i=0;i<m;i++)
    {
        for(int j=0;j<n;j++)
        {
            if(city_map[i][j]==1) continue;
            
            //위에서 내려온 경우
            if(i>0)
            {
                
                if(city_map[i-1][j]==2)
                {
                    dp[0][i][j]+=dp[0][i-1][j];
                }
                else
                {
                    dp[0][i][j]+=dp[0][i-1][j];
                    dp[0][i][j]+=dp[1][i-1][j];
                }
            }
            //왼쪽에서 오른쪽으로 온 경우
            if(j>0)
            {
                
                //왼쪽에서 우회전 한 경우
                
                if(city_map[i][j-1]==2)
                {
                    dp[1][i][j]+=dp[1][i][j-1];
                }
                else if(city_map[i][j-1]!=2)
                {
                    dp[1][i][j]+=dp[1][i][j-1];
                    dp[1][i][j]+=dp[0][i][j-1];
                }
                
            }
            dp[0][i][j]%=MOD;
            dp[1][i][j]%=MOD;
        }
        
    }
    
    return (dp[1][m-1][n-1]+dp[0][m-1][n-1])%MOD;
}