#include <string>
#include <vector>

using namespace std;

int solution(int n, vector<int> tops) {
    int answer = 0;
    //dp사용시 마름모로 각 삼각형의 왼쪽,오른쪽,사용하지 않음, 위(tops에 있을 경우)
    vector<vector<int>> dp(4,vector<int>(n,0));
    dp[0][0]=1;
    dp[1][0]=1;
    dp[2][0]=1;
    if(tops[0]) dp[3][0]=1;
    for(int i=1;i<n;i++)
    {
        //해당 삼각형의 왼쪽을 마름모로 사용
        dp[0][i] = (dp[0][i-1] + dp[2][i-1] + dp[3][i-1])%10007;
        //해당 삼각형의 오른쪽을 마름모로 사용
        dp[1][i] = (dp[0][i-1] +dp[1][i-1] + dp[2][i-1] + dp[3][i-1])%10007;
        //해당 삼각형을 마름모로 사용하지 않음
        dp[2][i] = (dp[0][i-1] +dp[1][i-1] + dp[2][i-1] + dp[3][i-1])%10007;
        //해당 삼각형의 위쪽을 마름모로 사용
        if(tops[i])
        {
            dp[3][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1] + dp[3][i-1])%10007;
        }
    }
    for(int i=0;i<4;i++)
    {
        answer+=dp[i][n-1];
    }
    return answer%10007;
}