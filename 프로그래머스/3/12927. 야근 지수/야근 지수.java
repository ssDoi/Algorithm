import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        while(n>0)
        {
            Arrays.sort(works);
            int max = works[works.length-1];
            if(max==0) break;
            for(int i=0;i<works.length;i++)
            {
                if(works[i] == max)
                {
                    if(n>0){
                        works[i]--;
                        n--;
                    }else
                    {
                        break;
                    }
                   // System.out.println(max +" " + works[i] + " "+ i);
                }
            }
        }
        for(int a : works )
        {
            answer += (long)Math.pow(a,2);
        }
        return answer;
    }
}