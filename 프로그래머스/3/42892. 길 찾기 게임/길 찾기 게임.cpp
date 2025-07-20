
#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;
struct Node {
    int x, y, idx;
    Node* left = nullptr;
    Node* right = nullptr;

    Node(int x_, int y_, int idx_) : x(x_), y(y_), idx(idx_) {}
};
void insert(Node* p, Node* i)
{
    if(p->x > i->x)
    {
        if(p->left==nullptr)
        {
            p->left=i;
        }else
        {
            insert(p->left,i);
        }
    }
    else
    {
        if(p->right==nullptr)
        {
            p->right=i;
        }else
        {
            insert(p->right,i);
        }
    }
}
void preorder(Node* cur, vector<int>& recode)
{
    if(!cur) return;
    //cout<< cur->idx << endl;
    recode.push_back(cur->idx);
    preorder(cur->left,recode);
    preorder(cur->right,recode);
    
}
void postorder(Node* cur, vector<int>& recode)
{
    if(!cur) return;
    postorder(cur->left,recode);
    postorder(cur->right,recode);
    recode.push_back(cur->idx);
    
}
vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    int n;
    vector<vector<int>> answer(2);
    vector<Node*> nodes;
    for(int i=0;i<nodeinfo.size();i++)
    {
        nodes.push_back(new Node(nodeinfo[i][0], nodeinfo[i][1],i+1));
    }
    sort(nodes.begin(),nodes.end(),[](Node* a, Node* b)
         {
             if(a->y==b->y)
             {
                 return a->x < b->x;
             }
             return a->y > b->y;
         });
    Node* root = nodes[0];
    
    for(int i=1;i<nodes.size();i++)
    {
        insert(root,nodes[i]);
    }
    
    preorder(root,answer[0]);
    postorder(root,answer[1]);
    
    return answer;
}