#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> A, vector<int> B) {
    int answer = 0;
    int n = A.size();
    sort(A.begin(),A.end());
    sort(B.begin(),B.end());
    int idx=0;
    for(int i=0;i<n&& idx<n; i++)
    {
        while(B[idx]<=A[i] && idx<n)
        {
            idx++;
        }     
        if(idx<n)
            answer++;
        idx++;
    }
    return answer;
}