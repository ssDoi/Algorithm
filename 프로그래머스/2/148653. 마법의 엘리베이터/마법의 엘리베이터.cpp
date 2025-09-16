#include <string>
#include <vector>

using namespace std;

int solution(int storey) {
    int answer = 0;
    while(storey>0)
    {
        int digit = storey%10;
        int up = 10-digit;
        if(digit>5 || digit==5 && (storey/10)%10 >4 )
        {
            answer+=up;
            storey+=up;
        }else
        {
            answer+=digit;
        }
        storey/=10;
        
    }

    return answer;
}