#include <string>
#include <vector>

using namespace std;
int user=-1;
int maxcost = -1;
void dfs(int cnt,vector<int>& discounts, int m,vector<vector<int>>& users, vector<int>& emoticons)
{
    if(cnt==m)
    {
        int cost=0;
        int regi=0;
        for(int i=0;i<users.size();i++)
        {
            int alow = users[i][0];
            int limit = users[i][1];
            int sum=0;
            for(int j=0;j<m;j++)
            {
                if(alow<=discounts[j])
                {
                    sum+=emoticons[j]*(100-discounts[j])/100;
                }
                if(sum>=limit)
                {
                    break;
                }
            }
            if(sum>=limit)
            {
                regi++;
            }
            else
            {
                cost+=sum;    
            }
            
        }
        if(regi>user)
        {
            user=regi;
            maxcost=cost;
            
        }
        else if(regi==user && cost>maxcost)
        {
            maxcost=cost;
        }
        
        return;
    }
    for(int i=10;i<=40;i+=10)
    {
        discounts[cnt]=i;
        dfs(cnt+1,discounts,m,users,emoticons);
    }
}
vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    vector<int> answer;
    
    //1플러스 가입자를 최대
    //이모티콘 판매액을 최대
    //n명 이모티콘 mro를 할인
    //할인율 10,20,30,40
    //일정비율이상 할인하는 이모티콘을 전부 구매
    //할인률 배치
    //100
    //이모틴콘 4*7;
    //완전탐색으로 해보자
    int m = emoticons.size();
    vector<int> discounts(m);
    dfs(0,discounts,m,users,emoticons);
    return {user,maxcost};
}