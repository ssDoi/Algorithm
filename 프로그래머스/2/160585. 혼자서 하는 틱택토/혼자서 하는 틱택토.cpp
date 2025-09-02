#include <string>
#include <vector>

using namespace std;
bool isOver(vector<string>& board, char mark)
{
    bool over = true;
    //가로
    for(int i=0;i<3;i++)
    {
        over = true;
        for(int j=0;j<3;j++)
        {
            if(board[i][j]!=mark)
            {
                over=false;
                break;
            }
        }
        if(over) return true;
    }
    //세로
    for(int i=0;i<3;i++)
    {
        over = true;
        for(int j=0;j<3;j++)
        {
            if(board[j][i]!=mark)
            {
                over=false;
                break;
            }
        }
        if(over) return true;
    }
    //대각선
    if(board[0][0] == mark && board[1][1]==mark && board[2][2] ==mark) return true;
    if(board[0][2] == mark && board[1][1]==mark && board[2][0] ==mark) return true;
    
    return false;
}
int solution(vector<string> board) {
    int o = 0, x = 0;
    for(string b : board)
        for(char c : b) {
            if(c == 'O') o++;
            if(c == 'X') x++;
        }
    
    // 1. 기본 규칙: X ≤ O ≤ X+1
    if(!(x == o || x+1 == o)) return 0;
    
    bool oWin = isOver(board,'O');
    bool xWin = isOver(board,'X');
    
    // 2. 동시에 승리할 수 없음
    if(oWin && xWin) return 0;
    
    // 3. O가 이긴 경우 → O가 반드시 한 수 더 많아야 함
    if(oWin && o != x+1) return 0;
    
    // 4. X가 이긴 경우 → O와 X의 개수가 같아야 함
    if(xWin && o != x) return 0;
    
    // 5. 그 외는 모두 정상
    return 1;
}
