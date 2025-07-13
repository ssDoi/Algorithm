#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
bool isValidStructure(set<pair<int, int>>& pillars , set<pair<int, int>>& beams) {
    for (const auto& [x, y] : pillars) {
        if (y == 0) continue;
        if (pillars.count({x, y - 1}) || beams.count({x - 1, y}) || beams.count({x, y}))
            continue;
        return false;
    }

    for (const auto& [x, y] : beams) {
        if (pillars.count({x, y - 1}) || pillars.count({x + 1, y - 1}))
            continue;
        if (beams.count({x - 1, y}) && beams.count({x + 1, y}))
            continue;
        return false;
    }

    return true;
}

bool cmp(vector<int>& a, vector<int>& b) {
    if (a[0] != b[0]) return a[0] < b[0];
    if (a[1] != b[1]) return a[1] < b[1];
    return a[2] < b[2];
}
vector<vector<int>> solution(int n, vector<vector<int>> build_frame) {
    vector<vector<int>> answer;
    set<pair<int, int>> pillars, beams;
    for (const vector<int>& order : build_frame) {
        
        int x = order[0];
        int y = order[1];
        int a = order[2];
        int b = order[3];

        set<pair<int, int>> former_pillars = pillars;
        set<pair<int, int>> former_beams = beams;

        if (a == 0) {
            if (b == 1)
                pillars.insert({x, y});
            else
                pillars.erase({x, y});
        } else {
            if (b == 1)
                beams.insert({x, y});
            else
                beams.erase({x, y});
        }

        if (!isValidStructure(pillars, beams)) {
            pillars = former_pillars;
            beams = former_beams;
        }
    }

    for(auto [x,y] : pillars)
    {
        answer.push_back({x,y,0});
    }
    for(auto [x,y] : beams)
    {
        answer.push_back({x,y,1});
    }
        
    sort(answer.begin(),answer.end(),cmp);
    return answer;
}