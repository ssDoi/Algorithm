#include <string>
#include <vector>
#include <map>
#include <cmath>

long long solution(std::vector<int> weights) {
    long long answer = 0;
    std::map<int, int> counts;

    // 1. 몸무게별 인원 수 계산
    for (int w : weights) {
        counts[w]++;
    }

    // 2. 짝꿍 쌍 찾기
    for (auto const& [w, c] : counts) {
        // 같은 몸무게인 경우
        if (c >= 2) {
            answer += (long long)c * (c - 1) / 2;
        }

        // 다른 몸무게인 경우
        // w * 2 == w' * 3
        if (w % 3 == 0) {
            int target_w = w / 3 * 2;
            if (counts.count(target_w)) {
                answer += (long long)c * counts[target_w];
            }
        }
        // w * 2 == w' * 4
        if (w % 2 == 0) {
            int target_w = w / 2;
            if (counts.count(target_w)) {
                answer += (long long)c * counts[target_w];
            }
        }
        // w * 3 == w' * 4
        if (w % 4 == 0) {
            int target_w = w / 4 * 3;
            if (counts.count(target_w)) {
                answer += (long long)c * counts[target_w];
            }
        }
    }

    return answer;
}