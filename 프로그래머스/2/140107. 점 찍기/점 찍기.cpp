#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

long long solution(int k, int d) {
    long long answer = 0;
    for (long long i = 0; i <= d; i += k) {
        long long val = (long long)d * d - i * i;  // 반드시 long long
        long long x = floor(sqrt((long double)val)); // double→long long 캐스팅
        answer += x / k + 1;
    }
    return answer;
}
