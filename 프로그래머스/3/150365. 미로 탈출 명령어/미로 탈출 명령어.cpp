#include <string>
#include <vector>
#include <cmath>
using namespace std;

int dx[] = {1, 0, 0, -1};           // d, l, r, u
int dy[] = {0, -1, 1, 0};
char dir[] = {'d', 'l', 'r', 'u'};
string answer = "impossible";
bool found = false;

void dfs(int x, int y, int r, int c, int k, string path, int n, int m) {
    if (found) return;

    int dist = abs(x - r) + abs(y - c);
    if (dist > k || (k - dist) % 2 != 0) return;

    if (k == 0) {
        if (x == r && y == c) {
            answer = path;
            found = true;
        }
        return;
    }

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 1 && ny >= 1 && nx <= n && ny <= m) {
            dfs(nx, ny, r, c, k - 1, path + dir[i], n, m);
            if (found) return;
        }
    }
}

string solution(int n, int m, int x, int y, int r, int c, int k) {
    int dist = abs(x - r) + abs(y - c);
    if (dist > k || (k - dist) % 2 != 0)
        return "impossible";
    dfs(x, y, r, c, k, "", n, m);
    return answer;
}
