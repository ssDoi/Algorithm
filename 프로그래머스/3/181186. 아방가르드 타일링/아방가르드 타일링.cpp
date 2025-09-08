#include <string>
#include <vector>

using namespace std;
const int MOD = 1000000007;

int solution(int n) {
    // dp[i] = 3×i 직사각형을 채우는 방법의 수
    vector<long long> dp(max(n+1, 7), 0);

    // 초기값 설정
    dp[0] = 1;   // 공집합(채울 공간 없음)
    dp[1] = 1;
    dp[2] = 3;
    dp[3] = 10;
    dp[4] = 23;
    dp[5] = 62;
    dp[6] = 170;

    for (int i = 7; i <= n; i++) {
        dp[i] = (dp[i-1] + 2*dp[i-2] + 6*dp[i-3] + dp[i-4] - dp[i-6]) % MOD;
        if (dp[i] < 0) dp[i] += MOD; // 음수 방지
    }
    return (int)dp[n];
}