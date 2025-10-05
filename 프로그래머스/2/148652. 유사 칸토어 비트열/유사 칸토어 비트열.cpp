#include <bits/stdc++.h>
using namespace std;

long long countOne(int n, long long k) {
    if (n == 0) {
        return (k > 0 ? 1 : 0);
    }

    long long len = (long long)pow(5, n);
    long long base = len / 5;
    long long sub = countOne(n-1, base); // n-1 전체 길이에서의 1 개수

    long long section = (k-1) / base; // 0~4 구간

    long long total = 0;
    if (section == 0) {
        total = countOne(n-1, k);
    } else if (section == 1) {
        total = sub + countOne(n-1, k - base);
    } else if (section == 2) {
        total = 2*sub; // 가운데는 다 0
    } else if (section == 3) {
        total = 2*sub + countOne(n-1, k - 3*base);
    } else if (section == 4) {
        total = 3*sub + countOne(n-1, k - 4*base);
    }
    return total;
}

int solution(int n, long long l, long long r) {
    return countOne(n, r) - countOne(n, l-1);
}
