#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> players, int m, int k) {
    int answer = 0;
    queue<int> endTime;
    int server=1;
    for(int i=0;i<24;i++)
    {
        int users = players[i];
        while(!endTime.empty() && endTime.front()<=i)
        {
            server--;
            endTime.pop();
        }
        if(m*server<=users)
        {
            while(m*server<=users)
            {
                server++;
                answer++;
                endTime.push(i+k);
            }
        }
    }
    return answer;
}