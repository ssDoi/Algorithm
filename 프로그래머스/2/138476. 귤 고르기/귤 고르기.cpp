#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
bool cmp(pair<int,int>& a, pair<int,int>& b)
{
    return a.second > b.second;
}
int solution(int k, vector<int> tangerine) {
    int answer = 0;
    map<int,int> m;
    for(int& t : tangerine)
    {
        m[t]++;
    }
    priority_queue<int> pq;
    for(auto& [key,value] : m)
    {
        pq.push(value);
    }
    int sum=0;
    while(!pq.empty() && sum<k)
    {
        sum+=pq.top();
        pq.pop();
        answer++;
    }
    
    return answer;
}