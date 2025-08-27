#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
bool check(vector<int> cast,vector<vector<int>>& q, vector<int>& ans)
{
    for(int i=0;i<q.size();i++)
    {
        int cnt=0;
        for(int j=0;j<5;j++)
        {
            if(find(cast.begin(),cast.end(),q[i][j]) != cast.end())   cnt++;
        }
        if(cnt!=ans[i]) return false;
        
    }
    return true;
}
int solution(int n, vector<vector<int>> q, vector<int> ans) {
    int answer = 0;
    //n까지의 정수 중 5개를 선택
    for(int a=1;a<=n;a++)
        for(int b=a+1;b<=n;b++)
            for(int c=b+1;c<=n;c++)
                for(int d=c+1; d<=n;d++)
                    for(int e=d+1; e<=n;e++)
                    {
                        if(check({a,b,c,d,e},q,ans))
                        {
                            answer++;
                        }
                    }
    
    return answer;
}