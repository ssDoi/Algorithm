#include <bits/stdc++.h>
using namespace std;

long long solution(int r1, int r2) {
    long long answer = 0;

    for (int x = 1; x <= r2; x++) {
        long long maxY = floor(sqrt(1LL * r2 * r2 - 1LL * x * x));
        long long minY = ceil(sqrt(1LL * r1 * r1 - 1LL * x * x));

        if (1LL * r1 * r1 - 1LL * x * x < 0) minY = 0; // r1보다 작은 경우는 제외

        answer += (maxY - minY + 1);
    }

    return answer * 4; // 대칭성 이용
}
