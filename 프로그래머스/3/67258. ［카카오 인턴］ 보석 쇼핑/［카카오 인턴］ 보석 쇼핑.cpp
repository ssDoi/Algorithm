#include <string>
#include <vector>
#include <unordered_map>
#include <set>
using namespace std;

vector<int> solution(vector<string> gems) {
    int n = gems.size();
    set<string> gem_types(gems.begin(), gems.end());
    int total_types = gem_types.size();
    
    unordered_map<string, int> count;
    int left = 0, right = 0;
    int min_len = n + 1;
    int answer_left = 0, answer_right = 0;
    
    count[gems[0]] = 1;
    
    while (left <= right && right < n) {
        if (count.size() == total_types) {
            if ((right - left + 1) < min_len) {
                min_len = right - left + 1;
                answer_left = left;
                answer_right = right;
            }
            count[gems[left]]--;
            if (count[gems[left]] == 0) {
                count.erase(gems[left]);
            }
            left++;
        } else {
            right++;
            if (right < n)
                count[gems[right]]++;
        }
    }
    
    return {answer_left + 1, answer_right + 1};
}
