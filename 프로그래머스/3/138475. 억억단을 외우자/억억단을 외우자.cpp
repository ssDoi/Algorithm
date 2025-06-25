#include <vector>
#include <algorithm>
using namespace std;

vector<int> solution(int e, vector<int> starts) {
    vector<int> cnt(e + 1, 0);
    vector<int> best(e + 2);
    vector<int> answer;

    // 1. 약수 개수 전처리
    for (int i = 1; i <= e; ++i) {
        for (int j = i; j <= e; j += i) {
            cnt[j]++;
        }
    }

    // 2. best[i] = [i, e] 구간 중 약수 개수 가장 많은 수
    best[e] = e;
    for (int i = e - 1; i >= 1; --i) {
        if (cnt[i] >= cnt[best[i + 1]]) {
            best[i] = i;
        } else {
            best[i] = best[i + 1];
        }
    }

    // 3. 쿼리 처리
    for (int s : starts) {
        answer.push_back(best[s]);
    }

    return answer;
}
