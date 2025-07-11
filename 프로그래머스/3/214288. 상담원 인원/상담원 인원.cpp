#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
int mins = 1e9;
void dfs(int k,int cnt, int n,vector<int> arr,vector<vector<int>>& reqs)
{
    
    if(n==0)
    {
        vector<priority_queue<int, vector<int>, greater<int>>> order(k);
        int wating=0;
        for(const vector<int>& req : reqs)
        {
            int rt =req[0];
            int gt =req[1];
            int type = req[2]-1;
            
            while(!order[type].empty() &&order[type].top() <=rt) order[type].pop();
            
            if(order[type].size() <arr[type])
            {
                order[type].push(rt+gt);
            }
            else
            {
                
                int start = order[type].top();
                wating+=start-rt;
                order[type].pop();
                order[type].push(start+gt);
            }
        }
        if(mins>wating)
        {
            mins=wating;
            // cout << "갱신 " << wating <<  endl;
            // for(int a : arr)
            // {
            //     cout << a << " ";
            // }
            // cout << endl;
        }
        
        return;
    }
    if(cnt==k)
    {

        return;
    }
    for(int i=0;i<=n;i++)
    {
        
        arr[cnt]+=i;
        dfs(k,cnt+1,n-i,arr,reqs);
        arr[cnt]-=i;
    }
}
int solution(int k, int n, vector<vector<int>> reqs) {
    int answer = 0;
    vector<int> arr(k,1);
    int agent =n-k;
    
    dfs(k,0,agent,arr,reqs);
    
    return mins;
}