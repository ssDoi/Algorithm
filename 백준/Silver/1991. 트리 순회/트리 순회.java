import java.util.*;
import java.io.*;

public class Main {
    static Map<String, String[]> tree;
    static String pres="";
    static String mids="";
    static String fores="";
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        tree = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            tree.put(st.nextToken(), new String[]{st.nextToken(), st.nextToken()});
        }
        pre("A");
        mid("A");
        fore("A");
        System.out.println(pres);
        System.out.println(mids);
        System.out.println(fores);

    }
    public static void pre(String s)
    {
        pres +=s;
        String d1= tree.get(s)[0];
        String d2= tree.get(s)[1];
        if(!d1.equals(".")){
            pre(d1);
        }
        if(!d2.equals(".")){
            pre(d2);
        }
    }
    public static void mid(String s)
    {

        String d1= tree.get(s)[0];
        String d2= tree.get(s)[1];
        if(!d1.equals(".")){
            mid(d1);
        }
        mids+=s;
        if(!d2.equals(".")){
            mid(d2);
        }

    }
    public static void fore(String s)
    {
        String d1= tree.get(s)[0];
        String d2= tree.get(s)[1];
        if(!d1.equals(".")){
            fore(d1);
        }
        if(!d2.equals(".")){
            fore(d2);
        }
        fores+=s;
    }
}