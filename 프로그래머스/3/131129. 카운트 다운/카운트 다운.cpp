#include <string>
#include <vector>

using namespace std;
vector<int> answer;

    
vector<int> solution(int target) {
    vector<pair<int, int>> dp(target + 1, {1e9, 0});
    dp[0] = {0, 0};

    vector<pair<int, int>> scores;
    for (int i = 1; i <= 20; i++) {
        scores.push_back({i, 1});      // 싱글
        scores.push_back({i * 2, 0});  // 더블
        scores.push_back({i * 3, 0});  // 트리플
    }
    scores.push_back({50, 1});         // 불

    for (int i = 1; i <= target; i++) {
        for (auto [score, is_single] : scores) {
            if (i - score < 0) continue;

            int prev_darts = dp[i - score].first + 1;
            int prev_singles = dp[i - score].second + is_single;

            if (prev_darts < dp[i].first || 
               (prev_darts == dp[i].first && prev_singles > dp[i].second)) {
                dp[i] = {prev_darts, prev_singles};
            }
        }
    }

    return {dp[target].first, dp[target].second};
    
}