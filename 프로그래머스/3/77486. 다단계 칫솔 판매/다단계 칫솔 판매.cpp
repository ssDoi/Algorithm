#include <string>
#include <vector>
#include <unordered_map>
using namespace std;

unordered_map<string, string> parent;
unordered_map<string, int> benefit;

void distribute(string name, int money) {
    if (name == "-" || money == 0) return;

    int pass_up = money / 10;
    int keep = money - pass_up;
    benefit[name] += keep;

    distribute(parent[name], pass_up);
}

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
    int n = enroll.size();
    vector<int> answer;

    // 1. 연결 구조 세팅
    for (int i = 0; i < n; i++) {
        parent[enroll[i]] = referral[i];
        benefit[enroll[i]] = 0;
    }

    // 2. 판매 시 분배 처리
    for (int i = 0; i < seller.size(); i++) {
        string name = seller[i];
        int totalMoney = amount[i] * 100;
        distribute(name, totalMoney);
    }

    // 3. 정답 구성
    for (string name : enroll) {
        answer.push_back(benefit[name]);
    }

    return answer;
}
