#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<vector<int>> beginning, vector<vector<int>> target) {
    int n = beginning.size();
    int m = beginning[0].size();
    int answer = 1e9;

    // rowFlipMask: 각 행을 뒤집을지 여부를 나타내는 비트마스크 (0 ~ 2^n - 1)
    for (int rowFlipMask = 0; rowFlipMask < (1 << n); ++rowFlipMask) {
        // 1. 임시 배열 만들기
        vector<vector<int>> flipped = beginning;

        // 2. 선택된 행 뒤집기
        for (int i = 0; i < n; ++i) {
            if (rowFlipMask & (1 << i)) {
                for (int j = 0; j < m; ++j)
                    flipped[i][j] ^= 1;
            }
        }

        // 3. 어떤 열을 뒤집어야 하는지 판단
        vector<bool> colFlip(m, false);
        bool valid = true;
        for (int j = 0; j < m; ++j) {
            bool flipColumn = false;
            if (flipped[0][j] != target[0][j])
                flipColumn = true;
            colFlip[j] = flipColumn;
        }

        // 4. 열 뒤집기 수행
        for (int j = 0; j < m; ++j) {
            if (colFlip[j]) {
                for (int i = 0; i < n; ++i)
                    flipped[i][j] ^= 1;
            }
        }

        // 5. 결과 비교
        if (flipped == target) {
            int rowFlipCount = __builtin_popcount(rowFlipMask);
            int colFlipCount = count(colFlip.begin(), colFlip.end(), true);
            answer = min(answer, rowFlipCount + colFlipCount);
        }
    }

    return (answer == 1e9 ? -1 : answer);
}
