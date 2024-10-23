import java.util.*;
import java.io.*;
public class Main {
    static int map[][];
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};
    static List<int[]> blankList;
    static Queue<int []> virusQueue;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        blankList = new ArrayList<>();
        virusQueue = new ArrayDeque<>();
        map = new int[n][m];
        max = -1;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0){
                    blankList.add(new int[]{i,j});
                }else if(map[i][j]==2){
                    virusQueue.offer(new int[]{i,j});
                }
            }
        }
        comb(3,0,blankList.size(),new boolean[blankList.size()]);
        System.out.println(max);

    }
    public static void comb(int r, int cnt,int m , boolean[] visited)
    {
        if(r==0){
            int[][] copyMap = new int[map.length][map[0].length];
            for(int i=0;i<map.length;i++){
                copyMap[i]=map[i].clone();
            }

            for(int i=0;i<m;i++){
                if(visited[i]){
                    copyMap[blankList.get(i)[0]][blankList.get(i)[1]]=1;
                }
            }
            int s=cal(copyMap);
            if(max<s){
                max=s;
            }

            return;
        }
        if(cnt==m){
            return;
        }
        visited[cnt]=true;
        comb(r-1,cnt+1,m,visited);
        visited[cnt]=false;
        comb(r,cnt+1,m,visited);

    }
    public static int cal(int[][] map){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.addAll(virusQueue);
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            for(int i=0;i<4;i++){
                int ny = y+dy[i];
                int nx = x+dx[i];
                if(nx>=0 && ny>=0 && ny<map.length && nx<map[0].length && map[ny][nx] ==0){
                    queue.offer(new int[]{ny,nx});
                    map[ny][nx]=2;
                }
            }
        }
        int sum=0;
        for(int[] row : map){
            for(int p : row){
                if(p==0)
                    sum++;
            }
        }
        return sum;
    }
}