#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> c, int k) {
    vector<int> sum(c.size(),0);
    sum[0] = c[0];
    int n = c.size();
    vector<int> answer={0,n};
    for(int i=1;i<n;i++)
    {
        sum[i]=sum[i-1]+c[i];
    }
    for(int i=0;i<n;i++)
    {
        
        int left = i;
        int right = n-1;
        while(left<=right)
        {
            int mid = (left+right)/2;
            
            if(i>0)
            {
                if(sum[mid] -sum[i-1] == k)
                {
                    if(answer[1]-answer[0] > mid-i)
                    {
                        answer = {i,mid};
                    }
                    break;    
                }
                else
                {
                    if(sum[mid] -sum[i-1]>k)
                    {
                        right = mid-1;
                    }
                    else
                    {
                        left = mid+1;
                    }
                }
            }
            else
            {
                if(sum[mid] == k)
                {
                    
                    if(answer[1]-answer[0] > mid-i)
                    {
                        answer = {i,mid};
                    }
                    break;
                }
                else
                {
                    if(sum[mid]>k)
                    {
                         right = mid-1;
                    }
                    else
                    {
                        left = mid+1;
                    }
                }
            }
        }
    }
    
    return answer;
}