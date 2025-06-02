#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

bool compare(const vector<int>& a, const vector<int>& b) {
    if (a[0] != b[0]) return a[0] > b[0]; // 근무 태도 내림차순
    return a[1] < b[1]; // 동료 평가 오름차순
}

int solution(vector<vector<int>> scores) {
    vector<int> wanho = scores[0];
    sort(scores.begin(), scores.end(), compare);

    int maxPeer = 0;
    vector<vector<int>> valid; // 인센티브 대상자 리스트

    for (const auto& score : scores) {
        if (score[1] < maxPeer) {
            if (score == wanho) return -1; // 완호 탈락
            continue; // 탈락자
        } else {
            maxPeer = max(maxPeer, score[1]);
            valid.push_back(score); // 통과자
        }
    }

    // 점수 합 기준으로 내림차순 정렬
    sort(valid.begin(), valid.end(), [](const vector<int>& a, const vector<int>& b) {
        return (a[0] + a[1]) > (b[0] + b[1]);
    });

    // 완호 석차 계산
    int rank = 1;
    int same = 1;
    int total = wanho[0] + wanho[1];

    for (int i = 0; i < valid.size(); ++i) {
        int sum = valid[i][0] + valid[i][1];
        if (sum > total) {
            rank += same;
            same = 1;
        } else if (sum == total) {
            // 완호 찾음
            if (valid[i] == wanho) return rank;
            same++;
        } else {
            break;
        }
    }

    return rank;
}
