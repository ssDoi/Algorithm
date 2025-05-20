#include <bits/stdc++.h>
#include <math.h>
using namespace std;
int n;
vector<int> liquid;
vector<int> answer;


int main()
{
    cin >> n;
    for(int i=0;i<n;i++)
    {
        int l;
        cin >> l;
        liquid.push_back(l);
    }
    int left =0;
    int right = n-1;
    int minSum = INT_MAX;
    int ans1 = 0, ans2 = 0;
    while(left <right)
    {
        int sum = liquid[left] + liquid[right];
        if(abs(sum) < abs(minSum))
        {
            minSum = sum;
            ans1 = liquid[left];
            ans2 = liquid[right];
        }
        if(sum <0)
        {
            left++;
        }else
        {
            right--;
        }
    }
    cout << ans1 << " " << ans2 << endl;
    return 0;

}
