#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    vector<vector<int>> diff(board.size()+1, vector<int>(board[0].size()+1,0));
    int n=diff.size();
    int m= diff[0].size();
    //skill
    for(const vector<int>& s : skill)
    {
        int type=s[0];
        int r1=s[1];
        int c1=s[2];
        int r2=s[3];
        int c2=s[4];
        int degree=s[5];
        if(type==1) degree= -degree;
        diff[r1][c1] +=degree;
        diff[r2+1][c1] -=degree;
        diff[r1][c2+1] -=degree;
        diff[r2+1][c2+1] +=degree;
    }
    //가로 누적합
    for(int i=0;i<n;i++)
    {
        for(int j=1;j<m;j++)
        {
            diff[i][j]+=diff[i][j-1];
        }
    }
    //세로 누적합
    for(int j=0;j<m;j++)
    {
        for(int i=1;i<n;i++)
        {
            diff[i][j]+=diff[i-1][j];
        }
    }
    //결산
    for(int i=0;i<n-1;i++)
    {
        for(int j=0;j<m-1;j++)
        {
            board[i][j] +=diff[i][j];
            if(board[i][j]>0) answer++;
        }
    }
    return answer;
}