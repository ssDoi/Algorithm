#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

bool check(int n, int k, vector<int> enemy, int round)
{
    if(k>round) return true;
    
    sort(enemy.begin(),enemy.begin()+round,greater<int>());
    for(int i=k;i<round;i++)
    {
        n-=enemy[i];
        if(n<0) return false;
    }
    return true;
}
int solution(int n, int k, vector<int> enemy) {
    int answer = 0;
    int m = enemy.size();
    if(k>=m)
        return m;
    //dp -> 그렇다면 뭐로? 연속성을 가진 n이잖아
    //이진 탐색 round?해당 라운드 일 때 못한다면 오 가능할지도
    int left =0;
    int right = m;
    while(left<=right)
    {
        int mid = (left+right)/2;
        if(!check(n,k,enemy,mid))
        {
            right=mid-1;
        }
        else
        {
            answer=mid;
            left=mid+1;
        }
    }
    
    return answer;
}