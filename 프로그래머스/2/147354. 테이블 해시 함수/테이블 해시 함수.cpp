#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
int s;
bool cmp(vector<int>& a, vector<int>& b)
{
    if(a[s] == b[s])
    {
        return a[0]>b[0];
    }
    return a[s]<b[s];
}
int solution(vector<vector<int>> data, int col, int row_begin, int row_end) {
    int answer = 0;
    s= col-1;
    sort(data.begin(),data.end(),cmp);

    for(int i=row_begin-1 ; i <row_end;i++)
    {
        int sum=0;
        for(int j=0;j<data[i].size();j++)
        {
            sum+=data[i][j]%(i+1);
        }
        if(i==row_begin-1)
        {
            answer=sum;
        }
        else
        {
            answer^=sum;
        }
    }
    
    return answer;
}