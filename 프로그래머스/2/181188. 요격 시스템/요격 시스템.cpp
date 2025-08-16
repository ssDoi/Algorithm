#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<vector<int>> targets) {
    // 끝점 기준 정렬
    sort(targets.begin(), targets.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[1] < b[1];
    });

    int cnt = 0;
    vector<bool> visited(targets.size(), false);

    for (int i = 0; i < targets.size(); i++) {
        if (visited[i]) continue;

        int start = targets[i][0];
        int end = targets[i][1];
        visited[i] = true;

        // i 이후 구간들 중 겹치는 건 전부 묶기
        for (int m = i + 1; m < targets.size(); m++) {
            int s = targets[m][0];
            int e = targets[m][1];

            if (end > s && start < e) {
                visited[m] = true; // 겹치는 구간은 같은 미사일로 처리
            } else {
                break; // 끝점 정렬했으므로 더 이상 겹치지 않음
            }
        }

        cnt++;
    }

    return cnt;
}
