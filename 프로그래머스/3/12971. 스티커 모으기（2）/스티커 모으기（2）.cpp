#include <iostream>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
int solution(vector<int> sticker) {
    int n = sticker.size();
    if (n == 0) return 0;
    if (n == 1) return sticker[0];
    if (n == 2) return max(sticker[0], sticker[1]);

    // 첫번째 스티커 사용시 -> 마지막 스티커 사용 불가
    vector<int> dp1(n, 0);
    dp1[0] = sticker[0];
    dp1[1] = max(sticker[0], sticker[1]);
    for (int i = 2; i < n - 1; i++) {
        dp1[i] = max(dp1[i - 1], dp1[i - 2] + sticker[i]);
    }

    // 첫번째 스티커 미사용시 마지막 스티커 사용가능
    
    vector<int> dp2(n, 0);
    dp2[0] = 0;
    dp2[1] = sticker[1];
    for (int i = 2; i < n; i++) {
        dp2[i] = max(dp2[i - 1], dp2[i - 2] + sticker[i]);
    }

    return max(dp1[n - 2], dp2[n - 1]);
}
