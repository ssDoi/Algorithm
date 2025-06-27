#include <string>
#include <vector>

using namespace std;

long long solution(vector<int> sequence) {
    long long answer = 0;
    int n = sequence.size();
    vector<long long> pulse1(n);
    vector<long long> pulse2(n);
    for(int i=0;i<n;i++)
    {
        pulse1[i] = sequence[i] * (i%2==0?1:-1);
        pulse2[i] = sequence[i] * (i%2==0?-1:1);
    }
    auto kadane= [](const vector<long long>& arr) ->long long
    {
        long long max_sum=arr[0];
        long long current_sum=arr[0];
        for(int i=1;i<arr.size();i++)
        {
            current_sum=max(arr[i],arr[i]+current_sum);
            max_sum=max(max_sum,current_sum);
        }
        return max_sum;
        
    };
    
    return max(kadane(pulse1),kadane(pulse2));
}