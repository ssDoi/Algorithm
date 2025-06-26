#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(int temperature, int t1, int t2, int a, int b, vector<int> onboard) {
    int n = onboard.size();
    temperature += 11;
    t1 += 11;
    t2 += 11;
    int tempRange = 50;
    int INF = 1e9;
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(tempRange + 2, vector<int>(2, INF)));

    dp[0][temperature][0] = 0; // off 상태
    dp[0][temperature][1] = 0; // on 상태

    for (int i = 1; i < n; i++) {
        for (int temp = 0; temp <= 51; temp++) {
            // 이전에 온도 temp에 도달 가능한지 확인
            for (int ac = 0; ac <= 1; ac++) {
                if (dp[i - 1][temp][ac] == INF) continue;

                // 에어컨 off → off 상태
                int newtemp = temp;
                if (temp < temperature) newtemp++;
                else if (temp > temperature) newtemp--;
                // 탑승 중이면 범위 확인 필요
                if (!onboard[i] || (newtemp >= t1 && newtemp <= t2)) {
                    dp[i][newtemp][0] = min(dp[i][newtemp][0], dp[i - 1][temp][ac]);
                }

                // 에어컨 on → on 상태
                for (int delta = -1; delta <= 1; delta++) {
                    int newtemp = temp + delta;
                    if (newtemp < 0 || newtemp > 51) continue;
                    if (!onboard[i] || (newtemp >= t1 && newtemp <= t2)) {
                        int cost = (delta == 0 ? b : a);
                        dp[i][newtemp][1] = min(dp[i][newtemp][1], dp[i - 1][temp][ac] + cost);
                    }
                }
            }
        }
    }

    int answer = INF;
    for (int i = 0; i <= 51; i++) {
        answer = min({answer, dp[n - 1][i][0], dp[n - 1][i][1]});
    }
    return answer;
}

