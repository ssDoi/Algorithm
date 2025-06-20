#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> a) {
    int answer = 2;
    if(a.size()==1) return 1;
    else if(a.size()==2) return 2;
    
    vector<int> rm(a.size(),1000000);
    vector<int> lm(a.size(),1000000);
    lm[0] = a[0];
    rm[rm.size()-1] = a[a.size()-1];
    for(int i=1;i<a.size();i++)
    {
        if(a[i]<lm[i-1])
        {
            lm[i] = a[i];
        }else
        {
            lm[i]=lm[i-1];
        }
    }

    for(int i=a.size()-2;i>=0;i--)
    {
        if(a[i]<rm[i+1])
        {
            rm[i] = a[i];
        }else
        {
            rm[i]=rm[i+1];
        }
        
    }
    for(int i=1;i<a.size()-1;i++)
    {
        if(a[i] <=lm[i] || a[i] <=rm[i])
        {
            answer++;
        }

    }
    return answer;
}