#include <string>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

string transform(const string& s) {
    stack<char> stk;
    int cnt110 = 0;

    // 1. "110" 패턴 제거
    for (char c : s) {
        stk.push(c);
        if (stk.size() >= 3) {
            char third = stk.top(); stk.pop();
            char second = stk.top(); stk.pop();
            char first = stk.top(); stk.pop();
            if (first == '1' && second == '1' && third == '0') {
                cnt110++; // "110" 제거
            } else {
                stk.push(first);
                stk.push(second);
                stk.push(third);
            }
        }
    }

    // 2. 스택 → 문자열
    string t = "";
    while (!stk.empty()) {
        t += stk.top();
        stk.pop();
    }
    reverse(t.begin(), t.end());

    // 3. 삽입 위치 찾기
    int insertPos = -1;
    for (int i = t.size() - 1; i >= 0; --i) {
        if (t[i] == '0') {
            insertPos = i + 1;
            break;
        }
    }

    // 4. "110" cnt110개를 삽입
    string result;
    string ones = "";
    for (int i = 0; i < cnt110; ++i) ones += "110";

    if (insertPos == -1) {
        // '0'이 없으면 앞에 삽입
        result = ones + t;
    } else {
        result = t.substr(0, insertPos) + ones + t.substr(insertPos);
    }

    return result;
}

vector<string> solution(vector<string> s) {
    vector<string> answer;
    answer.reserve(s.size());
    for (const string& str : s) {
        answer.push_back(transform(str));
    }
    return answer;
}
