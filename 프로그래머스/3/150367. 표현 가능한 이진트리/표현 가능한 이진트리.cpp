#include <string>
#include <vector>
#include <cmath>
#include <bitset>
#include <algorithm>
using namespace std;

// 이진 트리를 재귀적으로 확인하는 함수
bool isValid(const string& s) {
    if (s.size() == 1) return true;

    int mid = s.size() / 2;
    char root = s[mid];

    string left = s.substr(0, mid);
    string right = s.substr(mid + 1);

    // 부모가 0인데 자식에 1이 존재하면 invalid
    if (root == '0') {
        if (left.find('1') != string::npos || right.find('1') != string::npos) {
            return false;
        }
    }

    return isValid(left) && isValid(right);
}

// 숫자를 포화 이진 트리 크기에 맞춰 이진수 문자열로 변환하는 함수
string toPaddedBinary(long long n) {
    string bin = "";
    while (n > 0) {
        bin += (n % 2) + '0';
        n /= 2;
    }
    reverse(bin.begin(), bin.end());

    // 포화 이진 트리의 최소 노드 수 찾기 (2^k - 1 >= bin.size())
    int len = bin.size();
    int treeSize = 1;
    while (treeSize < len) {
        treeSize = treeSize * 2 + 1;
    }

    // 왼쪽에 0을 추가하여 길이를 맞춤
    bin = string(treeSize - len, '0') + bin;
    return bin;
}

vector<int> solution(vector<long long> numbers) {
    vector<int> answer;

    for (long long n : numbers) {
        string bin = toPaddedBinary(n);
        if (isValid(bin)) answer.push_back(1);
        else answer.push_back(0);
    }

    return answer;
}
