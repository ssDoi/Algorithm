#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {
    long long answer = 0;

    int sumd=0;
    int sump=0;
    int dend = deliveries.size()-1;
    int pend = pickups.size()-1;
    for(int d : deliveries)
    {
        sumd+=d;
    }
    for(int p : pickups)
    {
        sump+=p;
    }
    while(sumd>0 || sump>0)
    {
        // 뒤에서부터 배달할 곳 선택
        int deliver=0;
        int pickup=0;
        int distance=0;
        
        for(int i=dend; i>=0;i--)
        {
            if(deliveries[i]>0)
            {
                distance=distance<i+1 ? i+1 : distance;
                if(deliveries[i]+deliver>cap)
                {
                    dend=i;
                    deliveries[i]-=cap-deliver;
                    deliver=cap;
                    break;
                }
                deliver+=deliveries[i];
                deliveries[i]=0;
            }
        }
        // 뒤에서 부터 수거할 곳 선택
        for(int i=pend; i>=0 ; i--)
        {
            if(pickups[i]>0)
            {
                distance=distance<i+1 ? i+1 : distance;
                if(pickups[i]+pickup>cap)
                {
                    pend=i;
                    pickups[i]-=cap-pickup;
                    pickup=cap;
                    break;
                }
                pickup+=pickups[i];
                pickups[i]=0;
            }
        }
        sumd-=deliver;
        sump-=pickup;
        answer+=distance*2;
        
    }
    return answer;
}