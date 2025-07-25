#include <string>
#include <vector>

using namespace std;
//시간당 처리할 수 있는 작업 수
long long countProcess(long long time, vector<int>& cores)
{
    long long total = cores.size();
    for(int core : cores)
    {
        total+= time/core;
    }
    return total;
}

int solution(int n, vector<int> cores) {
    int answer = 0;
    if (n <= cores.size()) return n; 
    //처리에 걸리는 최소시간 t 구하기
    long long left = 0;
    long long right = 1e8;
    long long mid;
    long long t;
    while(left<=right)
    {
        mid =(left+right)/2;
        //해당 시간에 처리할 수 있는 작업의 수가 n보다 크다면
        if(countProcess(mid,cores) >=n)
        {
            t=mid;
            right = mid-1;
            
        }else
        {
            left = mid+1;
        }
    }
    //최소시간 t를 구했으면 t 바로 직전에 상황 t-1일 때는 얼마나 작업할 수 있는 지 구하자
    int closeEnd = countProcess(t-1,cores);
    //t시간에 사용가능한 core를 확인하고 해당 core가 작업한 개수가 n번째 프로세스라면 멈춤
    for(int i=0;i<cores.size();i++)
    {
        if(t%cores[i]==0)
        {
            closeEnd++;
            if(closeEnd==n)
                return i+1;
        }
    }
    
    return -1;
}