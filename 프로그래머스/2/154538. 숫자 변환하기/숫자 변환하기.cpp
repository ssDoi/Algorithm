#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(int x, int y, int n) {
    int answer = 0;
    vector<int> dp(y+1,1e9);
    if(x>y) return -1;
    dp[x] =0;
    for(int i=x;i<=y;i++)
    {
        if(i-n>=x)
            dp[i] = min(dp[i],dp[i-n] +1);
        if(i%2==0 && i/2>=x)
            dp[i] = min(dp[i],dp[i/2] +1);
        if(i%3==0 && i/3>=x)
            dp[i] = min(dp[i],dp[i/3] +1);
    }
    if(dp[y]==1e9) return -1;
    return dp[y];
}