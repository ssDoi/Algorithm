#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;
int n;
vector<int> answer;
int maxwin=-1;
// void simulate(vector<int>& s,int cnt,const vector<vector<int>>& dice, const vector<int> a, const vector<int> b)
// {
//     if(cnt==n)
//     {
//         int suma=0;
//         int sumb=0;
        
//         for(int i=0;i<s.size();i++)
//         {
//             if(i<n/2)
//             {
//                 suma += dice[a[i%(n/2)]][s[i]];
//             }
//             else
//             {
//                 sumb += dice[b[i%(n/2)]][s[i]];
//             }
//         }
//         if(suma > sumb)
//         {
//             win++;
//         }
//         else if(suma < sumb)
//         {
//             lose++;
//         }else
//         {
//             draw++;
//         }
//         return;
//     }
//     for(int i=0;i<6;i++)
//     {
//         s[cnt]=i;
//         simulate(s,cnt+1,dice,a,b);
//     }
// }
int countWins(const map<int, int>& A, const map<int, int>& B) {
    int win = 0;
    //int total = 0;
    for (auto& [sumA, cntA] : A) {
        for (auto& [sumB, cntB] : B) {
           // total += cntA * cntB;
            if (sumA > sumB) win += cntA * cntB;
        }
    }
    return win;
}


void getSumDist(const vector<int>& team, map<int, int>& sumDist, int depth, int sum, const vector<vector<int>>& dice) {
    
    if (depth == team.size()) {
        sumDist[sum]++;
        return;
    }
    for (int i = 0; i < 6; ++i) {
        getSumDist(team, sumDist, depth + 1, sum + dice[team[depth]][i],dice);
    }
}

void cb(int depth, int cnt, vector<bool>& visited,const vector<vector<int>>& dice)
{

    if(cnt==n/2)
    {
        
        vector<int> a;
        vector<int> b;
        cout<< "a: ";
        for(int i=0;i<n;i++)
        {
            if(visited[i]) 
            {
                a.push_back(i);
            }
            else
            {
                b.push_back(i);
            }
            
        }
        
        
        //vector<int> simul(n);
        //simulate(simul,0,dice,a,b);
        map<int,int> sumA,sumB;
        getSumDist(a,sumA,0,0,dice);
        getSumDist(b,sumB,0,0,dice);
        int win = countWins(sumA, sumB);
        if(maxwin<win)
        {
            maxwin=win;
            answer.clear();
            for(int k : a)
            {
                answer.push_back(k+1);
            }
        }
        return;
        
    }
    
    if(depth==n) return;
    
    visited[depth]=true;
    cb(depth+1,cnt+1,visited,dice);
    visited[depth]=false;
    cb(depth+1,cnt,visited,dice);
    
}

vector<int> solution(vector<vector<int>> dice) {
    n = dice.size();
    vector<bool> visited(n,false);
    
    cb(0,0,visited,dice);
    
    sort(answer.begin(),answer.end());
    return answer;
}