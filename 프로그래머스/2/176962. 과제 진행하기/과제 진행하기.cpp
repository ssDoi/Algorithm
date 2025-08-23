#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
int timeToMinute(string t)
{
    return stoi(t.substr(0,2)) * 60 + stoi(t.substr(3));
}
struct subject
{
    string name;
    int start;
    int rest;
    
    subject (string n, int s, int r) : name(n), start(s), rest(r)
    {}
};
bool cmp(const subject& a, const subject& b)
{
    return a.start < b.start;
}
vector<string> solution(vector<vector<string>> plans) {
    vector<string> answer;
    vector<subject> plan;
    for(const vector<string>& p : plans)
    {
        plan.emplace_back(p[0], timeToMinute(p[1]), stoi(p[2]));
    }
    sort(plan.begin(),plan.end(),cmp);
    stack<subject> pause;
    
    
    for(int i=1;i<plan.size();i++)
    {
        subject pre = plan[i-1];
        subject cur = plan[i];

        if(cur.start>= pre.start+pre.rest)
        {
            answer.push_back(pre.name);
            
            int rest_time = cur.start - (pre.start+pre.rest);

            while(!pause.empty() && rest_time>0)
            {
                if(pause.top().rest<=rest_time)
                {
                    rest_time -=pause.top().rest;
                   
                    answer.push_back(pause.top().name);
                    pause.pop();
                }
                else
                {
                    pause.top().rest -=rest_time;
                    rest_time=0;
                    break;
                }
            }
        }
        else
        {
             pause.push(subject(pre.name, pre.start,pre.start+pre.rest - cur.start));
        }
        
        if(i==plan.size()-1)
        {
            answer.push_back(cur.name);
        }
    }
    
    while(!pause.empty())
    {
        answer.push_back(pause.top().name);
        pause.pop();
    }
    return answer;
}