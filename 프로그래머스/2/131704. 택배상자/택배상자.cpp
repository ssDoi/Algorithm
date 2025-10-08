#include <string>
#include <vector>
#include <stack>
using namespace std;

int solution(vector<int> order) {
    stack<int> conv;
    int answer =0;
    //컨베이어에 놓인 박스
    int box =1;
    for(int i =0;i <order.size();i++)
     {
        //컨베이어에 놓인 박스와 트럭에 실을 박스가 같다면
        if(box==order[i])
        {
            answer++;
            box++;
            continue;
        }
        //보조컨베이어가 있고 보조컨베이어 입구에 놓은 박스가 트럭에 실을 박스라면
        if(!conv.empty()&&conv.top()==order[i])
        {
            answer++;
            conv.pop();
            continue;
        }
        //컨버에어의 박스가 트럭에 실을 박스보다 작다면 계속 컨베이어의 박스를 보조컨베이어에 집어넣는다.
        while(box<order[i]){
            conv.push(box++);
        }
        if(box==order[i])
        {
            answer++;
            box++;
            continue;
        }
        break;
        
        
     }
    return answer;
}