#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;
int timeToMin(string time)
{
    return stoi(time.substr(0,2))*60 + stoi(time.substr(3));
}
string minToTime(int min)
{
    string s = "";
    int h = min/60;
    int m = min%60;
    if(h<10) s+="0";
    s+=to_string(h)+":";
    if(m<10) s+="0";
    s+=to_string(m);
    return s;
}
string solution(int n, int t, int m, vector<string> timetable) {
    string answer = "";
    priority_queue<int,vector<int>, greater<int> > q;
    for(string time : timetable)
    {
        q.push(timeToMin(time));
    }
    int start = timeToMin("09:00");
    for(int i=0;i<n-1;i++)
    {
        int cur = start+i*t;
        int cnt=0;
        while(cnt<m && !q.empty() && q.top()<=cur)
        {
            cnt++;
            q.pop();
        }
        
    }
    int endtime = start+(n-1)*t;
    if(q.size() < m)
    {
        answer = minToTime(endtime);
    }
    else
    {
        int cnt=0;
        while(cnt<m-1 && q.top()<=endtime)
        {
            cnt++;
            q.pop();
        }
        if(q.top()>endtime)
        {
            answer = minToTime(endtime);
        }
        else
        {
            answer = minToTime(q.top()-1);
        }
    }
    return answer;
}