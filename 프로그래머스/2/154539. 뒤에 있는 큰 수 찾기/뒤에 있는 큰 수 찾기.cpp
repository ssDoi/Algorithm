#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> numbers) {
    int n = numbers.size();
    vector<int> answer(n, -1);
    stack<int> st; // 뒷 큰수 후보들 저장

    for (int i = n - 1; i >= 0; i--) {
        // 현재 값보다 작거나 같은 건 필요 없음
        while (!st.empty() && st.top() <= numbers[i]) {
            st.pop();
        }

        // 스택이 비지 않았다면 top이 뒷 큰수
        if (!st.empty()) {
            answer[i] = st.top();
        }

        // 현재 값을 스택에 push
        st.push(numbers[i]);
    }

    return answer;
}

