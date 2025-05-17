import java.util.*;

class Solution {
    
    static Set<Set<String>> result = new HashSet<>();
    static boolean[] visited;

    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        visited = new boolean[user_id.length];
        dfs(new HashSet<>(),0,user_id,banned_id);
        answer = result.size();
        return answer;
    }
    
    public static void dfs(Set<String> path, int depth, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            result.add(new HashSet<>(path));
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) continue;
            if (isMatch(user_id[i], banned_id[depth])) {
                visited[i] = true;
                path.add(user_id[i]);
                dfs(path, depth + 1, user_id, banned_id);
                path.remove(user_id[i]);
                visited[i] = false;
            }
        }
    }
    
    public static boolean isMatch(String user, String ban)
    {
        if (user.length() != ban.length()) return false;
        for (int i = 0; i < user.length(); i++) {
            if (ban.charAt(i) == '*') continue;
            if (user.charAt(i) != ban.charAt(i)) return false;
        }
        return true;
    }

}