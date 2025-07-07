#include <string>
#include <vector>

using namespace std;
bool canLoad(int a, int b, vector<int>& g, vector<int>& s, vector<int>& w, vector<int>& t,long long time)
{
    long long total_gold = 0;
    long long total_silver = 0;
    long long total=0;
    int n = g.size();
    
    for(int i=0;i<n;i++)
    {
        long long round = t[i]*2;
        long long move = time/round;
        if(t[i]<=time%round) move++;
        total_gold += min((long long)g[i], move*w[i]);
        total_silver += min((long long)s[i], move*w[i]);
        total+= min((long long)(g[i]+s[i]),move*w[i]);
    }
    return total_gold>=a && total_silver >=b && total >=a+b;
      
}
long long solution(int a, int b, vector<int> g, vector<int> s, vector<int> w, vector<int> t) {
    
    //이분탐색
    long long left =0;
    long long right = 1e15;
    long long answer = right;
    while(left<=right)
    {
        long long mid = (left+right)/2;
        if(canLoad(a,b,g,s,w,t,mid))
         {
            answer= mid;
            right = mid-1;
            
         }
         else
         {
              left = mid+1;
         }
           
    }
    
    return answer;
}