#include <string>
#include <vector>
#include <bits/stdc++.h>
using namespace std;
vector<vector<int>> rotate(vector<vector<int>> key )
{
    int n=key.size();
    vector<vector<int>> temp(n,vector<int>(n,0));

    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            temp[j][n-1-i] = key[i][j];
    
    return temp;
   
}
bool cango(vector<vector<int>>& key, vector<vector<int>>& or_tile,int n, int m)
{
    
    for(int i=0;i<=or_tile.size()-key.size();i++)
    {
        for(int j=0;j<=or_tile[0].size()-key.size();j++)
        {
            bool match = true;
            vector<vector<int>> tile = or_tile;
            
            for(int c=0; c<key.size() && match; c++)
            {
                for(int r=0; r<key.size() && match; r++)
                {
                    if (key[c][r] == 1) {
                        tile[i + c][j + r] += 1;
                        if (tile[i + c][j + r] > 1) match = false;
                    }
                }
            }
            
            for(int k=m-1;k<m-1+n && match;k++)
            {
                for(int o=m-1;o<m-1+n&& match;o++)
                {
                    if(tile[k][o]!=1) match=false;
                }
            }
            
            if(match) return true;

        }
    }
    return false;
    
}
bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    bool answer = false;
    vector<vector<int>> temp = key;
    int m = key.size();
    int n = lock.size();
    vector<vector<int>> tile((m-1)*2 +n, vector<int>((m-1)*2 +n,0));
    for(int i=m-1;i<n+m-1;i++)
    {
        for(int j=m-1;j<n+m-1;j++)
        {
            tile[i][j] = lock[i-(m-1)][j-(m-1)];
        }
    }
    
    for(int i=0;i<4;i++)
    {
        if(cango(temp,tile,n,m))
        {
            return true;
        }
        
        temp=rotate(temp);
    }
    return false;
}