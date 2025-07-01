#include <string>
#include <vector>
#include <stack>

using namespace std;

string solution(int n, int k, vector<string> cmd) {
    vector<int> prev(n), next(n);
    vector<bool> is_deleted(n, false);  // 삭제 여부 추적
    stack<int> deleted;
    
    for (int i = 0; i < n; ++i) {
        prev[i] = i - 1;
        next[i] = i + 1;
    }
    next[n - 1] = -1;

    int cur = k;

    for (string c : cmd) {
        if (c[0] == 'U') {
            int x = stoi(c.substr(2));
            while (x--) cur = prev[cur];
        } else if (c[0] == 'D') {
            int x = stoi(c.substr(2));
            while (x--) cur = next[cur];
        } else if (c[0] == 'C') {
            deleted.push(cur);
            is_deleted[cur] = true;

            if (prev[cur] != -1) next[prev[cur]] = next[cur];
            if (next[cur] != -1) prev[next[cur]] = prev[cur];

            // 아래가 존재하면 이동, 아니면 위로
            cur = (next[cur] != -1) ? next[cur] : prev[cur];
        } else if (c[0] == 'Z') {
            int restore = deleted.top();
            deleted.pop();
            is_deleted[restore] = false;

            if (prev[restore] != -1) next[prev[restore]] = restore;
            if (next[restore] != -1) prev[next[restore]] = restore;
        }
    }

    // 최종 결과 구성
    string answer(n, 'O');
    for (int i = 0; i < n; ++i) {
        if (is_deleted[i]) answer[i] = 'X';
    }

    return answer;
}

