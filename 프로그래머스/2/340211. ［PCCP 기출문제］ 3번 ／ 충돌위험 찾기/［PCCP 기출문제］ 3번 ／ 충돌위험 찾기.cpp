#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<vector<int>> points, vector<vector<int>> routes) {
    int answer = 0;
    int n = points.size();
    int robots =routes.size();
    vector<vector<int>> position;
    map<pair<int,int>,int> crush;
    //각 로봇이 향할 위치
    vector<int> target(robots,1);
    
    //각 로봇의 현재 위치를 초기화
    for(int i=0;i<routes.size();i++)
    {
        position.push_back({points[routes[i][0]-1][0],points[routes[i][0]-1][1],0});
        crush[{points[routes[i][0]-1][0],points[routes[i][0]-1][1]}] +=1;
    }
    for(auto [p,v] : crush)
    {
        if(v>=2) answer++;
    }
    
    
    while(robots>0)
    {
        map<pair<int,int>,int> crush;
        //각 로봇들 이동
        for(int i=0;i<routes.size();i++)
        {
            if(position[i][2]) 
            {
                //cout << i+1 << " 로봇은 도착했습니다."<<endl;
                continue;
            }
            
            vector<int> p = position[i];
            vector<int> t = points[routes[i][target[i]]-1];
            // cout << i+1 << " 로봇의 현재 위치 : " << p[0] <<" "<<p[1] << endl;
            // cout << i+1 << " 로봇의 목표 위치 : " << t[0] << " "<<t[1] << endl;
            if(p[0]!=t[0])
            {
                if(p[0]>t[0])
                {
                    p[0]--;
                }else
                {
                    p[0]++;
                }
            }
            else
            {
                if(p[1]>t[1])
                {
                    p[1]--;
                }else
                {
                    p[1]++;
                }
            }
            if(p[0]==t[0] && p[1]==t[1])
            {
                target[i]++;
            }
            if(target[i]==routes[i].size())
            {
                position[i] = {p[0],p[1],1};
                crush[{p[0],p[1]}]+=1;
                robots--;
                continue;
            }
            crush[{p[0],p[1]}]+=1;
            position[i]=p;
            
        }
        for(auto [p,v] : crush)
        {
            if(v>=2) answer++;
        }
        
    }
    return answer;
}