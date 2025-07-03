#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

string toHHMMSS(int t) {
    char buf[9];
    sprintf(buf, "%02d:%02d:%02d", t / 3600, (t % 3600) / 60, t % 60);
    return string(buf);
}

int toSec(const string& t) {
    return stoi(t.substr(0, 2)) * 3600 + stoi(t.substr(3, 2)) * 60 + stoi(t.substr(6, 2));
}

string solution(string play_time, string adv_time, vector<string> logs) {
    int playSec = toSec(play_time);
    int advSec = toSec(adv_time);
    vector<long long> timeline(playSec + 2, 0);

    for (const string& log : logs) {
        int start = toSec(log.substr(0, 8));
        int end = toSec(log.substr(9));
        timeline[start]++;
        timeline[end]--;
    }

    for (int i = 1; i <= playSec; ++i) timeline[i] += timeline[i - 1];
    for (int i = 1; i <= playSec; ++i) timeline[i] += timeline[i - 1];

    long long maxTime = timeline[advSec - 1];
    int maxStart = 0;
    for (int i = advSec; i <= playSec; ++i) {
        long long total = timeline[i] - timeline[i - advSec];
        if (total > maxTime) {
            maxTime = total;
            maxStart = i - advSec + 1;
        }
    }

    return toHHMMSS(maxStart);
}
