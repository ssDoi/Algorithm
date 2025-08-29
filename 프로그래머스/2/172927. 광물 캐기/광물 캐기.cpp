#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> picks, vector<string> minerals) {
    int answer = 1e9;
    vector<string> p;
    for(int i=0;i<3;i++)
    {
        for(int j=0;j<picks[i];j++)
        {
            if(i==0)
            {
                p.push_back("dia");
            }else if(i==1)
            {
                p.push_back("iron");
            }else if(i==2)
            {
                p.push_back("stone");
            }
        }
    }
    
    do
    {
        int cost = 0;
        for(int i=0;i<minerals.size() && i/5<p.size();i++)
        {
            string pick = p[i/5];
            string m = minerals[i];
            if(pick=="dia")
            {
                cost++;
                continue;
            }else if(pick=="iron")
            {
                if(m=="diamond")
                {
                    cost+=5;
                }else 
                {
                    cost++;
                }
            }else if(pick=="stone")
            {
                if(m=="diamond")
                {
                    cost+=25;
                }else if(m=="iron")
                {
                    cost+=5;
                }else
                {
                    cost++;
                }
            }
        }
        answer = answer>cost ? cost : answer;
        
    }while(next_permutation(p.begin(),p.end()));
    return answer;
}