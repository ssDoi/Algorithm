import java.util.*;
public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String hearing[] = new String[n];
        List<String> nohearlook = new ArrayList<>();
        HashMap<String,Integer> hearMap = new HashMap<>();
        for(int i=0;i<n;i++){
            hearing[i] = sc.next();
            hearMap.put(hearing[i],1);
        }
        for(int i=0;i<m;i++){
            String nolook = sc.next();
            if(hearMap.containsKey(nolook)){
                nohearlook.add(nolook);
            }
        }
        System.out.println(nohearlook.size());
        Collections.sort(nohearlook);
        for(int i=0;i<nohearlook.size();i++){
            System.out.println(nohearlook.get(i));
        }
    }
}