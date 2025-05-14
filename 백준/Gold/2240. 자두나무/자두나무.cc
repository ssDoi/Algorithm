#include <iostream>
#include <bits/stdc++.h>

using namespace std;

int main() {
    int T, W;
    cin >> T >> W;

    vector<int> tree(T + 1);
    for (int i = 1; i <= T; ++i) {
        cin >> tree[i];
    }

    int dp[1001][31][2] = {0};

    dp[0][0][0] = 0; // 시작은 1번 나무

    for (int t = 1; t <= T; ++t) {
        int cur_tree = tree[t] - 1;
        for (int w = 0; w <= W; ++w) {
            for (int p = 0; p < 2; ++p) {
                if (p == cur_tree) {
                    if (w == 0) {
                        if (p == 0) // 초기 위치가 1번 나무일 때만 의미 있음
                            dp[t][w][p] = dp[t - 1][w][p] + 1;
                    } else {
                        dp[t][w][p] = max(dp[t - 1][w][p], dp[t - 1][w - 1][1 - p]) + 1;
                    }
                } else {
                    if (w == 0) {
                        if (p == 0)
                            dp[t][w][p] = dp[t - 1][w][p];
                    } else {
                        dp[t][w][p] = max(dp[t - 1][w][p], dp[t - 1][w - 1][1 - p]);
                    }
                }
            }
        }
    }

    int result = 0;
    for (int w = 0; w <= W; ++w) {
        for (int p = 0; p < 2; ++p) {
            result = max(result, dp[T][w][p]);
        }
    }

    cout << result << endl;
    return 0;
}