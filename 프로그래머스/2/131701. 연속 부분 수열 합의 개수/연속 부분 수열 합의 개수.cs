using System;
using System.Linq;
using System.Text;
using System.Collections.Generic;
public class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        HashSet<int> s = new HashSet<int>();
        int n =elements.Length;
        int l = 1;
        while(l<=n)
        {
            int start=0;
            int end = start+l-1;
            int sum=0;
            for(int i=start;i<=end;i++)
            {
                sum+=elements[i];
            }
            while(start<=n-1)
            {
                sum-=elements[start];
                start++;
                end++;
                sum+=elements[end%n];
                
                s.Add(sum);
            }
            l++;
        }
        answer=s.Count;
        //for(int i=1)
        return answer;
    }
}