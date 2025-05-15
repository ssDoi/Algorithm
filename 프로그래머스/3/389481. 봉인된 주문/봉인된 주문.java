import java.util.*;

class Solution {
    static List<Long> banned = new ArrayList<>();

    public String solution(long n, String[] bans) {
        for (String s : bans) {
            banned.add(strToIndex(s));
        }
        Collections.sort(banned);

        long left = 1, right = (long)1e18; // 충분히 큰 범위
        long answerIndex = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long bannedBeforeMid = countBannedBefore(mid);

            if (mid - bannedBeforeMid >= n) {
                answerIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return indexToStr(answerIndex);
    }

    // 문자열 → 인덱스
    static long strToIndex(String s) {
        long index = 0;
        for (int len = 1; len < s.length(); len++) {
            index += Math.pow(26, len);
        }

        long factor = 1;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            index += ch * Math.pow(26, s.length() - i - 1);
        }

        return index + 1;
    }

    // 인덱스 → 문자열
    static String indexToStr(long idx) {
        int length = 1;
        long total = 0;
        while (true) {
            long cnt = (long)Math.pow(26, length);
            if (idx <= total + cnt) break;
            total += cnt;
            length++;
        }

        idx -= total + 1;
        char[] result = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            result[i] = (char)('a' + (idx % 26));
            idx /= 26;
        }

        return new String(result);
    }

    // banned 리스트에서 mid 이하의 개수
    static long countBannedBefore(long idx) {
        int left = 0, right = banned.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (banned.get(mid) <= idx) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
