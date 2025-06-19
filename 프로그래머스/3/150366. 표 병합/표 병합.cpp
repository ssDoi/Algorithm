#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>
#include <sstream>
using namespace std;

const int SIZE = 51;
int parent[SIZE * SIZE];
string value[SIZE * SIZE];

int getID(int r, int c) {
    return r * SIZE + c;
}

int find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
}
void merge(int a, int b) {
    int pa = find(a);
    int pb = find(b);
    if (pa == pb) return;

    string valA = value[pa];
    string valB = value[pb];

    // 무조건 pa가 대표가 되도록
    parent[pb] = pa;

    // 값 정리
    if (valA != "") value[pa] = valA;
    else value[pa] = valB;

    value[pb] = ""; // 병합됐으니 pb의 값은 없애줌
}


void unmerge(int id) {
    int root = find(id);
    string val = value[root];

    vector<int> group;
    for (int i = 0; i < SIZE * SIZE; ++i) {
        if (find(i) == root) {
            group.push_back(i);
        }
    }

    for (int i : group) {
        parent[i] = i;
        value[i] = "";
    }

    value[id] = val;
}


vector<string> solution(vector<string> commands) {
    vector<string> answer;

    // 초기화
    for (int i = 0; i < SIZE * SIZE; ++i) {
        parent[i] = i;
        value[i] = "";
    }

    for (const string& cmd : commands) {
        istringstream ss(cmd);
        string token;
        vector<string> parts;
        while (ss >> token) parts.push_back(token);

        if (parts[0] == "UPDATE" && parts.size() == 4) {
            
            int r = stoi(parts[1]);
            int c = stoi(parts[2]);
            int id = getID(r, c);
            value[find(id)] = parts[3];
            
        } 
        else if (parts[0] == "UPDATE") {
                string v1 = parts[1];
                string v2 = parts[2];
                for (int i = 0; i < SIZE * SIZE; ++i) {
                    int root = find(i);
                    if (value[root] == v1) {
                         value[root] = v2;
                    }
                }
            }
             else if (parts[0] == "MERGE") {
            int r1 = stoi(parts[1]), c1 = stoi(parts[2]);
            int r2 = stoi(parts[3]), c2 = stoi(parts[4]);
            int id1 = getID(r1, c1), id2 = getID(r2, c2);
            merge(id1, id2);
        } else if (parts[0] == "UNMERGE") {
            int r = stoi(parts[1]), c = stoi(parts[2]);
            int id = getID(r, c);
            unmerge(id);
        } else if (parts[0] == "PRINT") {
            int r = stoi(parts[1]), c = stoi(parts[2]);
            int id = getID(r, c);
            string val = value[find(id)];
            if (val == "") answer.push_back("EMPTY");
            else answer.push_back(val);
        }
    }

    return answer;
}
