#include <bits/stdc++.h>
using namespace std;

long long gcd_ll(long long a, long long b) {
    while (b != 0) {
        long long t = a % b;
        a = b;
        b = t;
    }
    return (a < 0 ? -a : a);
}

long long array_gcd(const vector<int>& arr) {
    long long g = arr[0];
    for (size_t i = 1; i < arr.size(); ++i) {
        g = gcd_ll(g, arr[i]);
    }
    return g;
}

bool divides_none(long long g, const vector<int>& arr) {
    for (auto x : arr) {
        if (x % g == 0) return false; // 하나라도 나눠지면 조건 깨짐
    }
    return true;
}

int solution(vector<int> arrayA, vector<int> arrayB) {
    long long gcdA = array_gcd(arrayA);
    long long gcdB = array_gcd(arrayB);

    long long candidate1 = divides_none(gcdA, arrayB) ? gcdA : 0;
    long long candidate2 = divides_none(gcdB, arrayA) ? gcdB : 0;

    return (int)max(candidate1, candidate2);
}
