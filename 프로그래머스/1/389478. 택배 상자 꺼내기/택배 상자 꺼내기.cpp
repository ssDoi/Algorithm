#include <string>
#include <vector>

using namespace std;

int solution(int n, int w, int num){
    int answer = 0;
    
    int up =0;
    while(up<=n)
    {
        int nums = num%w;
        if(nums==0)
            up = num+1;
        else up = num+(w-nums)*2+1;
        answer++;
        num=up;
    }
    
    return answer;
}