#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

int solution(vector<int> a) {
    unordered_map<int, int> freq;
    for (int num : a)
        freq[num]++;

    int maxLength = 0;

    for (auto& [key, count] : freq) {
        if (count * 2 <= maxLength) continue; // 더 긴 수열 못 만들면 skip

        int pairCount = 0;
        for (int i = 0; i < a.size() - 1; ++i) {
            if ((a[i] == key || a[i+1] == key) && a[i] != a[i+1]) {
                pairCount++;
                i++; // 이 쌍은 사용했으므로 다음으로
            }
        }

        maxLength = max(maxLength, pairCount * 2);
    }

    return maxLength;
}
