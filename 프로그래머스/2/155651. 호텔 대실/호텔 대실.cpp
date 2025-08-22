#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
int toMinute(string t)
{
    return stoi(t.substr(0,2))*60 + stoi(t.substr(3));
}
bool cmp(vector<int>& a, vector<int>& b)
{
    return a[0]<b[0];
}
int solution(vector<vector<string>> book_time) {
    vector<vector<int>> time;
    int answer=0;
    for(const vector<string> b : book_time)
    {
        time.push_back({toMinute(b[0]),toMinute(b[1])});
    }
    sort(time.begin(),time.end(),cmp);
    priority_queue<int, vector<int>, greater<int>> pq;
    
    for(const vector<int> t : time)
    {
        int start = t[0];
        int end = t[1];
        while(!pq.empty() && pq.top()<=start)
        {
            pq.pop();
        }
        pq.push(end+10);
        answer = answer<pq.size() ? pq.size(): answer;
    }

    
    return answer;
}