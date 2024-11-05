import java.util.*;
import java.io.*;

public class Main {
    static int cnt;

    static Set<String> set;
    public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    set = new HashSet<>();
    int n = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    int[] p = new int[n];
    int[] c = new int[n];

    for(int i=0;i<n;i++){
        c[i] = i;
    }
    for(int i=0;i<n;i++){
        p[i] = Integer.parseInt(st.nextToken());
    }

    int[] s = new int[n];
    st = new StringTokenizer(br.readLine());
    for(int i=0;i<n;i++){
        s[i] = Integer.parseInt(st.nextToken());
    }
    cnt=0;
    while(!isEnd(c,p)){
        cnt++;
        if(!set.add(Arrays.toString(c)))
        {
            cnt=-1;
            break;
        }
        int cg[] = new int[n];
        for(int i=0;i<n;i++){
            cg[s[i]] = c[i];
        }
        c= cg;

    }
    System.out.println(cnt);

    }
    static boolean isEnd(int[] c,int[] p){
        for(int i=0;i<c.length;i++){
            if(p[c[i]] != i%3)
            {
                return false;
            }
        }
        return true;
    }
}