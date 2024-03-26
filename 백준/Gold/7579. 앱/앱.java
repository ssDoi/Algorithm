import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int memory[] = new int[n + 1];
        int cost[] = new int[n + 1];
        int maxCost = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i]; // 모든 앱을 비활성화하는 최대 비용 계산
        }

        int[] dp = new int[maxCost + 1]; // 비용을 인덱스로 하는 1차원 배열

        for (int i = 1; i <= n; i++) {
            for (int j = maxCost; j >= cost[i]; j--) { // 최대 비용부터 현재 앱의 비용까지 반복
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]); // 최대 메모리 갱신
            }
        }

        int answer = 0;
        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= m) { // 요구하는 메모리 이상을 확보할 수 있는 최소 비용 찾기
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}