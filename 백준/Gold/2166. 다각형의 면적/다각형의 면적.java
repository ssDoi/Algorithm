import java.io.*;
import java.util.*;
public class Main {
    public static class Point{
        long x;
        long y;
        public Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
        }
        double sum1=0;
        double sum2=0;
        for(int i = n-1; i >=0; i--){
            Point p1 = points[i];
            Point p2;
            if(i==0){
                p2=points[n-1];
            }else {
                p2 = points[i-1];
            }
            sum1+=p1.x*p2.y;
            sum2+=p2.x*p1.y;
        }
        double area =Math.abs(sum1-sum2)/2.0;
        area = Math.round(area * 10) / 10.0;
        System.out.printf("%.1f",area);

    }
}