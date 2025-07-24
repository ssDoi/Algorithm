#include <vector>
using namespace std;

int solution(int n, vector<int> money) {
    vector<long long> dp(n + 1, 0);
    dp[0] = 1; // 금액 0을 만드는 방법은 1가지

    for (int coin : money) {
        for (int i = coin; i <= n; ++i) {
            dp[i] += dp[i - coin];
            
        }
    }
    return dp[n]%1000000007;
}
