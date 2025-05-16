import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverage = 2 * w + 1;
        int start = 1; // 처음 아파트 위치

        for (int station : stations) {
            int left = station - w;
            int right = station + w;

            if (start < left) {
                int gap = left - start;
                answer += (gap + coverage - 1) / coverage; // 올림 연산
            }

            start = right + 1; // 다음 검사 시작 위치
        }

        // 마지막 기지국 이후 남은 구간
        if (start <= n) {
            int gap = n - start + 1;
            answer += (gap + coverage - 1) / coverage;
        }

        return answer;
    }
}